package Planner;

import java.util.Set;

/**
 * Created by Мария on 29.05.2016.
 */

public interface Task {
    void execute();
    Set<Task> dependencies();
}
