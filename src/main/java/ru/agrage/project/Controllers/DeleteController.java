package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.agrage.project.Models.ParamModel;
import ru.agrage.project.Models.SignupModel;
import ru.agrage.project.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry on 1/5/17.
 */

@RestController
public class DeleteController {

    private final UserService accountService;

    @Autowired
    public DeleteController(UserService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/api/admin/delete/", method = RequestMethod.POST)
    public ResponseEntity<String> registration(@RequestBody final ParamModel Object, HttpServletRequest request, HttpServletResponse response) {
        if (accountService.delete(Object.getId())) {
            return new ResponseEntity<String>("User has been deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("User has not been deleted.", HttpStatus.OK);
    }
}
