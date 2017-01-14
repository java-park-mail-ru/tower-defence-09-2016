package ru.agrage.project.Service.Impl;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agrage.project.DAO.UserDAO;
import ru.agrage.project.Models.*;
import ru.agrage.project.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.persistence.Tuple;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dmitry on 1/4/17.
 */

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name = "userDAO")
    private UserDAO userDAO;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserModel create(SignupModel object) {
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        UserModel newUser = new UserModel(object.getLogin(), object.getPassword(), object.getEmail(), date, date);
        userDAO.create(newUser);
        log.info(newUser.toString());
        return newUser;
    }

    public boolean delete(int id) {
        if (userDAO.delete(id)) {
            return true;
        }
        return false;
    }

    public UserModel getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    public boolean usernameExists(String username) {
        if (userDAO.usernameExists(username)) {
            return true;
        }
        return false;
    }

    public boolean emailExists(String username) {
        if (userDAO.emailExists(username)) {
            return true;
        }
        return false;
    }

    public boolean passwordMatch(SignModel object) {
        if (!userDAO.passwordMatch(object)) {
            return false;
        }
        return true;
    }

    public List<UserModel> getAll() {
        return userDAO.getAll();
    }

    public List<RatingModel> getRating() {
        return userDAO.getRating();
    }

    public UserModel getById(int id) {
        return userDAO.getById(id);
    }

    public int getIdByUsername(String username) {
        return userDAO.getIdByUsername(username);
    }
}