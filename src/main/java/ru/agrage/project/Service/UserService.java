package ru.agrage.project.Service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import ru.agrage.project.Interface.UserInterface;
import ru.agrage.project.Models.SignupModel;
import ru.agrage.project.Models.UserModel;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource(name = "userInterface")
    private UserInterface userInterface;

    private ConcurrentHashMap<String,  UserModel> userNameToUser = new ConcurrentHashMap<>();

    public UserModel addUser(SignupModel object)  {
        UserModel newUser = new UserModel(object.getLogin(), object.getPassword(), object.getEmail());
        userInterface.addUser(newUser);
        return newUser;
    }
    public UserModel getLogin(String login) {
        return userNameToUser.get(login);
    }

}
