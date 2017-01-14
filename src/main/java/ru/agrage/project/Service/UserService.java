package ru.agrage.project.Service;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.agrage.project.Models.RatingModel;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Models.SignupModel;
import ru.agrage.project.Models.UserModel;

import javax.persistence.Tuple;
import java.util.List;

public interface UserService {
    UserModel create (SignupModel user);
    boolean delete (int id);
    boolean usernameExists (String username);
    boolean emailExists (String email);
    boolean passwordMatch(SignModel object);
    List<UserModel> getAll();
    List<RatingModel> getRating();
    UserModel getByUsername(String username);
    UserModel getById(int id);
    int getIdByUsername(String username);
}
