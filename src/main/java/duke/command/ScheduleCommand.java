package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A class which encapsulates the command of
 * viewing the schedule for a certain date.
 */
public class ScheduleCommand extends Command {

    /**
     * The date of the schedule.
     */
    private LocalDate scheduleDate;

    /**
     * A public constructor to intialize the command
     * to the given one.
     */
    public ScheduleCommand(String command, LocalDate scheduleDate) {
        super(command);
        this.scheduleDate = scheduleDate;
    }

    public String taskInSchedule(Task task) {
        assert !(task instanceof Todo): "task is todo";
        if(task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            if (deadline.getBy().equals(scheduleDate)) {
                return deadline.toString() + System.lineSeparator();
            }
        } else if(task instanceof Event) {
            Event event = (Event) task;
            LocalDate taskDate = event.getAt().toLocalDate();
            if(taskDate.equals(scheduleDate)) {
                return event.toString() + System.lineSeparator();
            }
        } else {
            assert false;
        }
        return "";
    }

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks stored so far.
     * @param ui      The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String taskSchedule = "You are sooo busy!! :\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task checkTask = tasks.get(i);
            if(!(checkTask instanceof Todo)) {
                taskSchedule += taskInSchedule(checkTask);
            }
        } return taskSchedule;
    }

}
