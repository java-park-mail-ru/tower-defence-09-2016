package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Service.UserService;
import ru.agrage.project.Service.ValidatorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Дмитрий on 04.10.2016.
 */
@RestController
public class LoginController {
    private final UserService accountService;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ValidatorService(accountService));
    }
    @Autowired
    public LoginController(UserService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/api/user/login/", method = RequestMethod.POST)
    public ResponseEntity<SignModel> login (@Valid @RequestBody final SignModel UserObject, HttpSession session) {
        return new ResponseEntity<SignModel> (UserObject, HttpStatus.OK);
    }
}
