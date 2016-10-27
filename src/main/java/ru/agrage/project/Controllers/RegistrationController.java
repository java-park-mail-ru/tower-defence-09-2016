package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import ru.agrage.project.Models.SignupModel;
import ru.agrage.project.Models.UserModel;
import ru.agrage.project.Service.UserService;
import ru.agrage.project.Service.ValidatorService;

/**
 * Created by Дмитрий on 04.10.2016.
 */

@RestController
public class RegistrationController {

    private final UserService accountService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ValidatorService(accountService));
    }

    @Autowired
    public RegistrationController(UserService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/api/user/registration/", method = RequestMethod.POST)
    public ResponseEntity<UserModel> registration(@Valid @RequestBody final SignupModel Object) {
        return new ResponseEntity<UserModel> (accountService.addUser(Object), HttpStatus.OK);
    }
}
