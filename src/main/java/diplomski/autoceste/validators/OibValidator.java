package diplomski.autoceste.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OibValidator implements ConstraintValidator<OibConstraints, String> {

    private static final int OIB_LENGTH = 11;
    private static final int STARTING_VALUE = 10;
    private static final int MULTIPLIER = 2;
    private OibConstraints constraints;

    @Override
    public void initialize(OibConstraints constraintAnnotation) {
        this.constraints = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || isOibValid(value);
    }

    /**
     * Algorithm description <href>https://regos.hr/app/uploads/2018/07/KONTROLA-OIB-a.pdf</href>.
     */
    public boolean isOibValid(String oib) {

        if (!(oib.chars().allMatch(Character::isDigit) && oib.length() == OIB_LENGTH)) {
            return false;
        }
        int result = STARTING_VALUE;

        for (int i = 0; i < OIB_LENGTH - 1; i++) {
            result += Character.getNumericValue(oib.charAt(i));

            result %= STARTING_VALUE;
            if (result == 0) {
                result = STARTING_VALUE;
            }
            result *= MULTIPLIER;
            result %= OIB_LENGTH;
        }
        result = (OIB_LENGTH - result) % STARTING_VALUE;

        return Character.getNumericValue(oib.charAt(STARTING_VALUE)) == result;
    }
}
