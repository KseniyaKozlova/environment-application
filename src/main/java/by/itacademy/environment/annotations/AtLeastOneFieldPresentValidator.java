package by.itacademy.environment.annotations;

import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.request.UpdateUserRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneFieldPresentValidator implements ConstraintValidator<AtLeastOneFieldPresent, Object> {

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return false;
        }

        if (object instanceof UpdateUserRequestDto userRequestDto) {
            return userRequestDto.getLogin() != null || userRequestDto.getPassword() != null
                    || userRequestDto.getName() != null || userRequestDto.getBonuses() != null
                    || userRequestDto.getRole() != null;
        } else if (object instanceof UpdateCompanyRequestDto companyRequestDto) {
            return companyRequestDto.getCompanyName() != null || companyRequestDto.getDetails() != null;
        } else {
            return false;
        }
    }
}
