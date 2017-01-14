package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import ru.agrage.project.Models.SignupModel;
import ru.agrage.project.Models.UserModel;
import ru.agrage.project.Service.Impl.UserServiceImpl;
import ru.agrage.project.Service.UserService;
import ru.agrage.project.Service.ValidatorService;

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
    public ResponseEntity<UserModel> registration(@Valid @RequestBody final SignupModel Object, HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<UserModel> (accountService.create(Object), HttpStatus.OK);
    }
}
