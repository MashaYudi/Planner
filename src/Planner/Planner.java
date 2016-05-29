package Planner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Мария on 29.05.2016.
 */
public class Planner implements Runnable{

    private final Object lock = new Object();
    private Thread thread;

    private final HashSet<RealTask> tasks;
    private volatile boolean isTrue;

    Planner(Set<RealTask> tasks){
        this.tasks = new HashSet<>();
        this.tasks.addAll(tasks);
    }

    public Task NextTask() {
        synchronized (lock) {
            if (tasks.isEmpty())
                return null;
            for (RealTask task : tasks) {
                if (task.dependencies().isEmpty())
                    return task;
            }
        }
        throw new IllegalStateException();
    }

    public void reloadTasks(Task endTask) {
        if (endTask == null)
            throw new IllegalArgumentException("Empty task");
        if (endTask.dependencies() == null || !endTask.dependencies().isEmpty())
            throw new IllegalArgumentException("Planner.Task can not be executed before its dependencies");
        synchronized (lock) {
            for (RealTask task : tasks) {
                task.dependencies().remove(endTask);
            }
            tasks.remove(endTask);
        }
    }

    @Override
    public void run() {
        RealTask currentTask;
        try {
            while (isTrue) {
                currentTask = (RealTask) NextTask();
                if (currentTask == null)
                    return;
                currentTask.execute();
                reloadTasks(currentTask);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addTasks(Set<RealTask> tasks) {
        synchronized (lock) {
                this.tasks.addAll(tasks);
        }
    }

    public void start() {
        isTrue = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        isTrue = false;
    }
}
