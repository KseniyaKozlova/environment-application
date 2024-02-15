package by.itacademy.environment.controllers;

import by.itacademy.environment.dto.response.ErrorResponse;
import by.itacademy.environment.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static by.itacademy.environment.util.Constants.*;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CompanyNotFoundException.class)
    public ErrorResponse handleCompanyServiceExceptions(final CompanyNotFoundException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(MISSING_COMPANY_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_COMPANY_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CouponNotFoundException.class)
    public ErrorResponse handleCouponServiceExceptions(final CouponNotFoundException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(MISSING_COUPON_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_COUPON_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TareNotFoundException.class)
    public ErrorResponse handleTareServiceExceptions(final TareNotFoundException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(MISSING_TARE_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_TARE_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserServiceExceptions(final UserNotFoundException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(MISSING_USER_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_USER_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InsufficientBonusesException.class)
    public ErrorResponse handleInsufficientBonusesException(final InsufficientBonusesException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(INSUFFICIENT_BONUSES_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_USER_COUPON_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DisabledCouponException.class)
    public ErrorResponse handleDisabledCouponException(final DisabledCouponException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(DISABLED_COUPON_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_BONUSES_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleDataIntegrityViolationException(final DataIntegrityViolationException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserByLoginNotFoundException.class)
    public ErrorResponse handleUserByLoginNotFoundException(final UserByLoginNotFoundException exception) {
        return ErrorResponse.builder()
                .message(messageSource.getMessage(WRONG_LOGIN_MESSAGE, new Object[]{exception.getMessage()},
                        DEFAULT_LOGIN_EXCEPTION_MESSAGE, LocaleContextHolder.getLocale()))
                .error(exception.getClass().getSimpleName())
                .time(now())
                .httpStatus(BAD_REQUEST)
                .build();
    }
}
