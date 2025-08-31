package david.ui;

import david.task.Deadline;
import david.task.Event;
import david.task.Task;
import david.task.ToDo;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores all tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Overloads the constructor, returns an empty list.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Add new tasks to the list.
     *
     * @param t A task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Delete tasks from the list.
     *
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    /**
     * Prints the string representation of all tasks from the list.
     */
    public void printList() {
        String start = Formatter.NEWLINE + "\n Here are the tasks in your list: ";
        System.out.print(start.indent(4));
        for (int i = 0; i < list.size(); i++) {
            String msg = String.format(" %d. %s", i + 1, list.get(i));
            System.out.print(msg.indent(4));
        }
        System.out.print(Formatter.NEWLINE.indent(4));
        System.out.println();
    }

    /**
     * Prints the string representation of all matching tasks from the find command.
     */
    public void printMatchList() {
        String start = Formatter.NEWLINE + "\n Here are the matching tasks in your list: ";
        System.out.print(start.indent(4));
        for (int i = 0; i < list.size(); i++) {
            String msg = String.format(" %d. %s", i + 1, list.get(i));
            System.out.print(msg.indent(4));
        }
        System.out.print(Formatter.NEWLINE.indent(4));
        System.out.println();
    }

    /**
     * Gets the task given an index.
     *
     * @param index The index of a task.
     * @return The desired task.
     */
    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * @return The size of task list.
     */
    public int size() {
        return list.size();
    }
}