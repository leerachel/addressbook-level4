package seedu.recruit.logic.commands.emailcommand;

import static java.util.Objects.requireNonNull;

import seedu.recruit.commons.core.EventsCenter;
import seedu.recruit.commons.events.logic.ChangeLogicStateEvent;
import seedu.recruit.commons.events.ui.ShowCandidateBookRequestEvent;
import seedu.recruit.commons.events.ui.ShowCompanyBookRequestEvent;
import seedu.recruit.commons.util.EmailUtil;
import seedu.recruit.logic.CommandHistory;
import seedu.recruit.logic.commands.CommandResult;
import seedu.recruit.model.Model;
import seedu.recruit.model.UserPrefs;

/**
 * This class handles the back sub command for email contents phase
 */
public class EmailContentsBackCommand extends EmailContentsCommand {
    @Override
    public CommandResult execute(Model model, CommandHistory history, UserPrefs userPrefs) {
        requireNonNull(model);
        EmailUtil emailUtil = model.getEmailUtil();

        changeBook(emailUtil);
        EventsCenter.getInstance().post(new ChangeLogicStateEvent(EmailRecipientsCommand.COMMAND_LOGIC_STATE));
        return new CommandResult(EmailRecipientsCommand.MESSAGE_USAGE);
    }

    /**
     * Changes the book when returning to add recipients step
     * @param emailUtil
     */
    public void changeBook(EmailUtil emailUtil) {
        if (emailUtil.getAreRecipientsCandidates()) {
            EventsCenter.getInstance().post(new ShowCandidateBookRequestEvent());
        } else {
            EventsCenter.getInstance().post(new ShowCompanyBookRequestEvent());
        }
    }
}
