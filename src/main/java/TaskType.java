public enum TaskType {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private String name;

    TaskType(String name) {
        this.name = name;
    }

    public static TaskType of(String s) throws InvalidTypeException {
        for (TaskType t : TaskType.values()) {
            if (s.equals(t.name)) {
                return t;
            }
        }
        throw new InvalidTypeException(s);
    }
}