//@@author tbhbhbh
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonContainsBirthdayPredicate;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedSearchCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(Arrays.asList("Alice", "friends")));
        assertParseSuccess(parser, "Alice friends", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t friends  \t", expectedSearchCommand);

        // single birthday month represented by 2 digits
        expectedSearchCommand = new FindCommand(new PersonContainsBirthdayPredicate("05"));
        assertParseSuccess(parser, "05", expectedSearchCommand);
    }
}
