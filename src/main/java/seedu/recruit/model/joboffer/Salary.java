package seedu.recruit.model.joboffer;

import static java.util.Objects.requireNonNull;
import static seedu.recruit.commons.util.AppUtil.checkArgument;

/**
 * Represents a Candidate's desired salary in the recruit book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */

public class Salary {
    public static final String SALARY_VALIDATION_REGEX = "[\\d]+";

    public static final String MESSAGE_SALARY_CONSTRAINTS =
            "Desired salary should contain numbers greater than 0 and it should not be blank ";

    public final String value;

    public Salary(String salaryInput) {
        requireNonNull(salaryInput);
        checkArgument(isValidSalary(salaryInput), MESSAGE_SALARY_CONSTRAINTS);
        value = salaryInput;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return ((test.matches(SALARY_VALIDATION_REGEX)) && (Integer.parseInt(test) > 0));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary // instanceof handles nulls
                && value.equals(((Salary) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
