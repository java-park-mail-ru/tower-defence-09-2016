package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.agrage.project.Models.RatingModel;
import ru.agrage.project.Models.UserModel;
import ru.agrage.project.Service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dmitry on 1/6/17.
 */

@RestController
public class UserController {
    private final UserService accountService;

    @Autowired
    public UserController(UserService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/api/admin/list/", method = RequestMethod.POST)
    public ResponseEntity<List<UserModel>> getAll() {
        return new ResponseEntity<List<UserModel>>(accountService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/api/user/rating/", method = RequestMethod.POST)
    public ResponseEntity<List<RatingModel>> getRating(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<List<RatingModel>>(accountService.getRating(), HttpStatus.OK);
    }
}
