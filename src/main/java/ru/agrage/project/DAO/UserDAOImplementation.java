package ru.agrage.project.DAO;

import org.springframework.stereotype.Repository;
import ru.agrage.project.Models.UserModel;

/**
 * Created by dmitry on 12/14/16.
 */
@Repository("userDAO")
public class UserDAOImplementation extends AbstractDao<Integer, UserModel> implements UserDAO{
    public void addUser(UserModel user) {
        persist(user);
    }
}
