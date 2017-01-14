package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;
import ru.agrage.project.Models.ParamModel;
import ru.agrage.project.Models.UserModel;
import ru.agrage.project.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by dmitry on 1/8/17.
 */
@RestController
public class SessionController {

    private final UserService accountService;

    @Autowired
    public SessionController(UserService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/api/user/session/", method = RequestMethod.POST)
    public ResponseEntity<Object> sessionCheck (HttpServletRequest request, HttpServletResponse response) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getDetails();
            return new ResponseEntity<Object> (authentication.getPrincipal(), HttpStatus.OK);
        }
        return new ResponseEntity<Object> (HttpStatus.UNAUTHORIZED);
    }
}
