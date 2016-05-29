package Planner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Мария on 30.05.2016.
 */
public class main {

    public static void main(String[] args) {
        HashSet<Task> tasks = new HashSet<>();
        while (tasks.size() < 10)
            tasks.add(new RealTask(null));

        Planner planner = new Planner((Set) tasks);
        planner.start();
    }

}