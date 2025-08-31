package david.ui;

import david.task.Deadline;
import david.task.Event;
import david.task.Task;
import david.task.ToDo;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

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

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int size() {
        return list.size();
    }
}