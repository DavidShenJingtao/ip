package david.ui;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import david.task.Task;

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
        System.out.print(buildString("\n Here are the tasks in your list: ").indent(4));
        System.out.println();
    }

    /**
     * Executes list command from GUI.
     *
     * @return The string representation of all tasks from the list.
     */
    public String printListString() {
        return buildStringGui("\n Here are the tasks in your list: ");
    }

    /**
     * Prints the string representation of all matching tasks from the find command.
     */
    public void printMatchList() {
        System.out.print(buildString("\n Here are the matching tasks in your list: ")
                                                                                    .indent(4));
        System.out.println();
    }

    /**
     * Executes find command from GUI.
     *
     * @return The string representation of all matching tasks from the find command.
     */
    public String printMatchListString() {
        return buildStringGui("\n Here are the matching tasks in your list: ");
    }

    private String buildString(String header) {
        String tasks = IntStream.range(0, list.size())
                .mapToObj(i -> String.format(" %d. %s", i + 1, list.get(i)))
                .collect(Collectors.joining("\n"));
        return Formatter.NEWLINE + header + "\n" + tasks + "\n" + Formatter.NEWLINE;
    }

    private String buildStringGui(String header) {
        return header + IntStream.range(0, list.size())
                .mapToObj(i -> String.format("\n %d. %s", i + 1, list.get(i)))
                .reduce("", String::concat);
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
