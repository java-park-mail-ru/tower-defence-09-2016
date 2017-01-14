package ru.agrage.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Service.UserService;
import ru.agrage.project.Service.ValidatorService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginController {

    private final UserService accountService;

    @Autowired
    private RedisTemplate< String, Object > template;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final String id = "id";

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ValidatorService(accountService));
    }

    @Autowired
    public LoginController(UserService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(path = "/api/user/login/", method = RequestMethod.POST)
    public ResponseEntity<SignModel> login(@Valid @RequestBody final SignModel UserObject, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        template.keys("*");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(UserObject.getLogin(), UserObject.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        if (auth.isAuthenticated()) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(86400);
            return new ResponseEntity<SignModel>(UserObject, HttpStatus.OK);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
            return new ResponseEntity<SignModel>(UserObject, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/api/user/logout/", method = RequestMethod.POST)
    public ResponseEntity logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }
}
