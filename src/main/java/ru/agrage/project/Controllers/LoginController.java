package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.agrage.project.Interface.UserInterface;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Service.UserService;
import ru.agrage.project.Service.ValidatorService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

//    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @RequestMapping(path = "/api/user/login/", method = RequestMethod.POST)
    public ResponseEntity<SignModel> login (@Valid @RequestBody final SignModel UserObject, HttpSession session) {
        return new ResponseEntity<SignModel> (UserObject, HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @RequestMapping(path = "/api/user/logout/", method = RequestMethod.POST)
    public ResponseEntity logout (HttpSession session) {
        session.invalidate();
        return new ResponseEntity (HttpStatus.OK);
    }
}
