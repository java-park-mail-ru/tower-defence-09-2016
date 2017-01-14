package ru.agrage.project.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.agrage.project.DAO.UserDAO;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Models.SignupModel;

import javax.annotation.Resource;

@Service
@Transactional
public class ValidatorService implements Validator {

    private final UserService accountService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

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

        if (target instanceof SignupModel) {
            ValidationUtils.rejectIfEmpty(e, "login", "login.empty");
            ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
            ValidationUtils.rejectIfEmpty(e, "email", "email.empty");
            SignupModel obj = (SignupModel) target;
            if (accountService.usernameExists(obj.getLogin())) {
                e.rejectValue("login", "login.exists");
            }
            if (accountService.emailExists(obj.getEmail())) {
                e.rejectValue("email", "email.exists");
            }
        }

        if (target instanceof SignModel) {
            ValidationUtils.rejectIfEmpty(e, "login", "login.empty");
            ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
            SignModel obj = (SignModel) target;
            if (!accountService.usernameExists(obj.getLogin())) {
                e.rejectValue("login", "login.doesnt.exist");
            }
            else if (!accountService.passwordMatch(obj)) {
                e.rejectValue("login", "login.doesnt.match");
                e.rejectValue("password", "password.doesnt.match");
            }
        }


    }
}
