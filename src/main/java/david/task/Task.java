package david.task;

import david.exception.DavidException;
import david.exception.EmptyDescriptionException;
import david.exception.FormatException;
import david.exception.InvalidTypeException;

/**
 * A parent class for all tasks.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Shows whether the task is done.
     *
     * @return 1 if the task is done, 0 otherwise.
     */
    public int getDone() {
        return (isDone ? 1 : 0);
    }

    public String getDoneString() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Creates a task from the command line.
     *
     * @param s The entire command.
     * @return A task given by the command.
     * @throws DavidException If parsing the task fails.
     */
    public static Task of(String s) throws DavidException {
        String[] strArr = s.split(" ", 2);
        assert strArr.length > 0 : "String array should never be empty";
        TaskType type = TaskType.of(strArr[0]);
        if (strArr.length <= 1) {
            throw new EmptyDescriptionException(strArr[0]);
        }

        String description;
        switch (type) {
        case TODO:
            description = strArr[1];
            return new ToDo(description);

        case DEADLINE:
            String[] by = strArr[1].split(" /by ", 2);
            if (by.length < 2) {
                String m = "the command format of deadline should be: "
                        + "deadline [task name] /by [time].";
                throw new FormatException(m);
            }
            description = by[0];
            String end = by[1];
            return new Deadline(description, end);

        case EVENT:
            String m = "the command format of event should be: "
                    + "event [task name] /from [start time] /to [end time].";
            String[] from = strArr[1].split(" /from ", 2);
            if (from.length < 2) {
                throw new FormatException(m);
            }
            String[] to = from[1].split(" /to ", 2);
            if (to.length < 2) {
                throw new FormatException(m);
            }
            description = from[0];
            String startTime = to[0];
            String endTime = to[1];
            return new Event(description, startTime, endTime);

        default:
            throw new InvalidTypeException(strArr[0]);
        }
    }

    /**
     * Creates a task from the input file.
     *
     * @param line A line from the input file.
     * @return A task given by the input line.
     * @throws DavidException If parsing the task fails.
     */
    public static Task create(String line) throws DavidException {
        String[] strArr = line.split("\\s*\\|\\s*");
        assert strArr.length > 0 : "String array should never be empty";
        String type = strArr[0];
        if (!type.equals("T") && !type.equals("D") && !type.equals("E")) {
            throw new InvalidTypeException(type);
        }
        String status = "wrong status format (1 for done, 0 for undone) in the input line.";
        if (strArr.length <= 1 || (!strArr[1].equals("0") && !strArr[1].equals("1"))) {
            throw new FormatException(status);
        }

        boolean flag = strArr[1].equals("1"); //true if is done
        String description;
        Task task;
        String todo = "the input format of todo should be: T | 0/1 | [description].";
        String ddl = "the input format of deadline should be: "
                + "D | 0/1 | [description] | [end time].";
        String event = "the input format for event should be: "
                + "E | 0/1 | [description] | [start time] - [end time].";

        switch (type) {
        case "T":
            if (strArr.length <= 2) {
                throw new FormatException(todo);
            }
            description = strArr[2];
            task = new ToDo(description);
            if (flag) {
                task.markAsDone();
            }
            return task;

        case "D":
            if (strArr.length <= 3) {
                throw new FormatException(ddl);
            }
            description = strArr[2];
            String by = strArr[3];
            task = new Deadline(description, by);
            if (flag) {
                task.markAsDone();
            }
            return task;

        case "E":
            if (strArr.length <= 3) {
                throw new FormatException(event);
            }
            description = strArr[2];
            String[] timeParts = strArr[3].split("\\s*-\\s*");
            if (timeParts.length != 2) {
                throw new FormatException(event);
            }
            String from = timeParts[0];
            String to = timeParts[1];
            task = new Event(description, from, to);
            if (flag) {
                task.markAsDone();
            }
            return task;

        default:
            throw new InvalidTypeException(type);
        }
    }


    @Override
    public String toString() {
        return String.format("[%s] %s", this.getDoneString(), this.description);
    }

    public String serialize() {
        return String.format("%d | %s", this.getDone(), this.description);
    }
}