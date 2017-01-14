package ru.agrage.project.DAO.Impl;

import org.springframework.stereotype.Repository;
import ru.agrage.project.DAO.UserProfileDAO;
import ru.agrage.project.Models.UserProfile;

import javax.annotation.Resource;

/**
 * Created by dmitry on 1/5/17.
 */
@Repository("userDAOProfile")
public class UserProfileDAOImpl extends AbstractDAOImpl<Integer, UserProfile> implements UserProfileDAO {
}
