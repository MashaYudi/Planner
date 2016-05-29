package Planner;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Мария on 29.05.2016.
 */
public class RealTask implements Task{

    //public String name;
    private final Object lock = new Object();
    private final HashSet<Task> dependencies;

    public RealTask(Set<Task> dependencies) {
        this.dependencies = new HashSet<>();
        if (dependencies != null && !dependencies.isEmpty())
        this.dependencies.addAll(dependencies);
    }

    @Override
    public void execute(){
        if (!dependencies.isEmpty()) {
            throw new IllegalStateException("Task can not be executed before its dependencies");
        }
        synchronized (lock) {
            System.out.println("Planner.Task was done");
        }
    }

    @Override
    public Set<Task> dependencies() {
        return dependencies;
    }
}
