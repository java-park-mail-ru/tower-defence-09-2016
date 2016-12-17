package ru.agrage.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Models.SignupModel;

public class ValidatorService implements Validator {

    private final UserService accountService;

    @Autowired
    public ValidatorService(UserService accountService) {
        this.accountService = accountService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return SignupModel.class.equals(clazz) || SignModel.class.equals(clazz);
}
    @Override
    public void validate(Object target, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "login", "login.empty");
        ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
        if (target instanceof SignupModel) {
            ValidationUtils.rejectIfEmpty(e, "email", "email.empty");
            SignupModel obj = (SignupModel) target;
            if (accountService.getLogin(obj.getLogin())!= null) {
                e.rejectValue("login", "Login exists!");
            }
        }
        if (target instanceof SignModel) {
            SignModel obj = (SignModel) target;
            if ((accountService.getLogin(obj.getLogin()) == null) || (!accountService.getLogin(obj.getLogin()).getPassword().
                    equals(obj.getPassword()))) {
                e.rejectValue("login", "Autorization Failed!");
            }
        }
    }
}
