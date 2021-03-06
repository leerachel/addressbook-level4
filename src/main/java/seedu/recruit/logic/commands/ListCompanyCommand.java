package seedu.recruit.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recruit.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static seedu.recruit.model.Model.PREDICATE_SHOW_ALL_JOBOFFERS;

import seedu.recruit.commons.core.EventsCenter;
import seedu.recruit.commons.events.ui.FocusOnCompanyBookRequestEvent;
import seedu.recruit.commons.events.ui.ShowUpdatedCompanyJobListRequestEvent;
import seedu.recruit.logic.CommandHistory;
import seedu.recruit.model.Model;
import seedu.recruit.model.UserPrefs;

/**
 * Lists all companies, as well as all job offers, in the recruit book to the user.
 * If the user is viewing Candidate Book, it will switch to Company Book.
 */
public class ListCompanyCommand extends Command {

    public static final String COMMAND_WORD = "listC";

    public static final String MESSAGE_SUCCESS = "Listed all companies and all job offers.\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all companies and all job offers.\n";

    @Override
    public CommandResult execute(Model model, CommandHistory history, UserPrefs userPrefs) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        model.updateFilteredCompanyJobList(PREDICATE_SHOW_ALL_JOBOFFERS);
        //First, update the total number of jobs on the CompanyJobDetailsPanel even if user is not on Company Book.
        EventsCenter.getInstance().post(new ShowUpdatedCompanyJobListRequestEvent(
                model.getFilteredCompanyJobList().size()));
        //If user is not on Company Book, this switches the view.
        EventsCenter.getInstance().post(new FocusOnCompanyBookRequestEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
