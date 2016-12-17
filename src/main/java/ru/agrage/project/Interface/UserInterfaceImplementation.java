package ru.agrage.project.Interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agrage.project.DAO.UserDAO;
import ru.agrage.project.Models.UserModel;

/**
 * Created by dmitry on 12/14/16.
 */

@Service("userInterface")
@Transactional
public class UserInterfaceImplementation implements UserInterface {
    @Autowired
    private UserDAO dao;
    public void addUser(UserModel user) {
        dao.addUser(user);
    }
}
