package ru.agrage.project.DAO;

import ru.agrage.project.Models.RatingModel;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Models.UserModel;

import javax.persistence.Tuple;
import java.util.List;


/**
 * Created by dmitry on 1/4/17.
 */
public interface UserDAO extends AbstractDAO<Integer, UserModel> {
    void create(UserModel userModel);
    boolean delete(int id);
    boolean usernameExists(String username);
    boolean emailExists(String email);
    boolean passwordMatch(SignModel object);
    List<UserModel> getAll();
    List<RatingModel> getRating();
    UserModel getByUsername (String username);
    UserModel getById (int id);
    int getIdByUsername (String username);
}
