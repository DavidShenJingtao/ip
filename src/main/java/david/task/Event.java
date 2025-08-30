package david.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Event extends Task {
    private static final String type = "E";
    private String from;
    private String to;

    private boolean fromHasTime;
    private boolean fromIsValidDate;
    private LocalDate fromDate;
    private LocalDateTime fromDateTime;
    
    private boolean toHasTime;
    private boolean toIsValidDate;
    private LocalDate toDate;
    private LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        fromInit();
        toInit();
    }

    private void fromInit() {
        try {
            if (this.from.contains(" ")) { //time?
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.fromHasTime = true;
                this.fromIsValidDate = true;
                this.fromDateTime = LocalDateTime.parse(this.from, dtFormatter);
            } else {
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.fromHasTime = false;
                this.fromIsValidDate = true;
                this.fromDate = LocalDate.parse(this.from, dtFormatter);
            }
        } catch (DateTimeParseException e) {
            this.fromHasTime = false;
            this.fromIsValidDate = false;
        } 
    }

    private void toInit() {
        try {
            if (this.to.contains(" ")) { //time?
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.toHasTime = true;
                this.toIsValidDate = true;
                this.toDateTime = LocalDateTime.parse(this.to, dtFormatter);
            } else {
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.toHasTime = false;
                this.toIsValidDate = true;
                this.toDate = LocalDate.parse(this.to, dtFormatter);
            }
        } catch (DateTimeParseException e) {
            this.toHasTime = false;
            this.toIsValidDate = false;
        }
    }

    private String printFromDate() {
        if (!fromIsValidDate) {
            return this.from;
        }
        if (fromHasTime) {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy, h:mma", Locale.ENGLISH);
            return fromDateTime.format(outputFormatter);
        } else {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            return fromDate.format(outputFormatter);
        }
    }

    private String printToDate() {
        if (!toIsValidDate) {
            return this.to;
        }
        if (toHasTime) {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy, h:mma", Locale.ENGLISH);
            return toDateTime.format(outputFormatter);
        } else {
            DateTimeFormatter outputFormatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            return toDate.format(outputFormatter);
        }
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s - %s", type,
                            super.toString(), printFromDate(), printToDate());
    }
}