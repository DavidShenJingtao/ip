public enum TaskType {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private String type;

    TaskType(String type) {
        this.type = type;
    }

    public static TaskType of(String s) throws InvalidTypeException {
        for (TaskType t : TaskType.values()) {
            if (s.equals(t.type)) {
                return t;
            }
        }
        throw new InvalidTypeException(s);
    }
}