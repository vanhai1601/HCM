package vn.com.mbbank.utils.validates;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberFormatValidator implements ConstraintValidator<NumberFormat, String> {
    @Override
    public void initialize(NumberFormat numberFormat) {

    }

    @Override
    public boolean isValid(String data, ConstraintValidatorContext constraintValidatorContext) {
            return StringUtils.isEmpty(data) || data.matches("[0-9]+");
    }
}
