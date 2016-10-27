package ru.agrage.project.Service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import ru.agrage.project.Models.SignupModel;
import ru.agrage.project.Models.UserModel;
/**
 * Created by Дмитрий on 04.10.2016.
 */
@Service
public class UserService {

    private ConcurrentHashMap<String,  UserModel> userNameToUser = new ConcurrentHashMap<>();

    public UserModel addUser(SignupModel object) {
        final UserModel newUser = new UserModel(object.getLogin(), object.getPassword(), object.getEmail());
        userNameToUser.putIfAbsent(newUser.getLogin(), newUser);
        return newUser;
    }
    public UserModel getLogin(String login) {
        return userNameToUser.get(login);
    }

}
