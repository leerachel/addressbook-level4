package seedu.recruit.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.recruit.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of the company offering the job in the CompanyBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */

public class CompanyName {
    public static final String COMPANY_VALIDATION_REGEX = ".+";

    public static final String MESSAGE_COMPANY_CONSTRAINTS =
            "Company name should not be blank";

    public final String value;

    public CompanyName(String companyInput) {
        requireNonNull(companyInput);
        checkArgument(isValidCompanyName(companyInput), MESSAGE_COMPANY_CONSTRAINTS);
        value = companyInput;
    }

    /**
     * Returns true if a given string is a valid company name.
     */
    public static boolean isValidCompanyName(String test) {
        return test.matches(COMPANY_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyName // instanceof handles nulls
                && value.equals(((CompanyName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
