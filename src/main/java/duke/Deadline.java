package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String changeDateFormat() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String taskIndicator() {
        return "D";
    }


    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by:" + changeDateFormat() + ")";
    }
}