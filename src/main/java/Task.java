public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public int getDone() {
        return (isDone ? 1 : 0);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    //create tasks from command
    public static Task of(String s) throws DavidException {
        String[] strarr = s.split(" ", 2);
        TaskType type = TaskType.of(strarr[0]);
        if (strarr.length <= 1) {
            throw new EmptyDescriptionException(strarr[0]);
        }

        String description;
        switch (type) {
            case TODO:
                description = strarr[1];
                return new ToDo(description);

            case DEADLINE:
                String[] by = strarr[1].split(" /by ", 2);
                if (by.length < 2) {
                    String m = "the command format of deadline should be: " +
                            "deadline [task name] /by [time].";
                    throw new FormatException(m);
                }
                description = by[0];
                //description = by[0] + " (by: " + by[1] + ")";
                String by_t = by[1];
                return new Deadline(description, by_t);

            case EVENT:
                String m = "the command format of event should be: " +
                        "event [task name] /from [start time] /to [end time].";
                String[] from = strarr[1].split(" /from ", 2);
                if (from.length < 2) {
                    throw new FormatException(m);
                }
                String[] to = from[1].split(" /to ", 2);
                if (to.length < 2) {
                    throw new FormatException(m);
                }
                /*description = from[0] + " (from: "
                        + to[0] + " to: " + to[1] + ")";*/
                description = from[0];
                String from_t = to[0];
                String to_t = to[1];
                return new Event(description, from_t, to_t);

            default:
                throw new InvalidTypeException(strarr[0]);
        }
    }

    //create tasks from input file
    public static Task create(String line) throws DavidException {
        String[] strarr = line.split("\\s*\\|\\s*");
        String type = strarr[0];
        if (!type.equals("T") && !type.equals("D") && !type.equals("E")) {
            throw new InvalidTypeException(type);
        }
        String status = "wrong status format (1 for done, 0 for undone) in the input line.";
        if (strarr.length <= 1 || (!strarr[1].equals("0") && !strarr[1].equals("1"))) {
            throw new FormatException(status);
        }

        boolean flag = strarr[1].equals("1"); //true if is done
        String description;
        Task task;
        String todo = "the input format of todo should be: T | 0/1 | [description].";
        String ddl = "the input format of deadline should be: "
                     + "D | 0/1 | [description] | [end time].";
        String event = "the input format for event should be: "
                     + "E | 0/1 | [description] | [start time] - [end time].";

        switch (type) {
            case "T":
                if (strarr.length <= 2) {
                    throw new FormatException(todo);
                }
                description = strarr[2];
                task = new ToDo(description);
                if (flag) task.markAsDone();
                return task;

            case "D":
                if (strarr.length <= 3) {
                    throw new FormatException(ddl);
                }
                description = strarr[2];
                String by = strarr[3];
                task = new Deadline(description, by);
                if (flag) task.markAsDone();
                return task;

            case "E":
                if (strarr.length <= 3) {
                    throw new FormatException(event);
                }
                description = strarr[2];
                String[] timeParts = strarr[3].split("\\s*-\\s*");
                if (timeParts.length != 2) {
                    throw new FormatException(event);
                }
                String from = timeParts[0];
                String to = timeParts[1];
                task = new Event(description, from, to);
                if (flag) task.markAsDone();
                return task;

            default:
                throw new InvalidTypeException(type);
        }
    }


    @Override
    public String toString() {
        return String.format("%d | %s", this.getDone(), this.description);
    }
}