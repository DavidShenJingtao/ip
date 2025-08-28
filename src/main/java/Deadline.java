import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Deadline extends Task {
    private static final String type = "D";
    private String by;
    private boolean hasTime;
    private boolean isValidDate;
    private LocalDate date;
    private LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            if (this.by.contains(" ")) { //time?
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.hasTime = true;
                this.isValidDate = true;
                this.dateTime = LocalDateTime.parse(this.by, dtFormatter);
            } else {
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                hasTime = false;
                isValidDate = true;
                this.date = LocalDate.parse(this.by, dtFormatter);
            }
        } catch (DateTimeParseException e) {
            hasTime = false;
            isValidDate = false;
        }
    }

    public String printDate() {
        if (!isValidDate) {
            return this.by;
        }
        if (hasTime) {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy, h:mma", Locale.ENGLISH);
            return dateTime.format(outputFormatter);
        } else {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            return date.format(outputFormatter);
        }
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", type,
                                super.toString(), printDate());
    }
}