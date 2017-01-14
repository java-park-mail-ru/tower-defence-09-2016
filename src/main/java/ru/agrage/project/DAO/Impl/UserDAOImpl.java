package ru.agrage.project.DAO.Impl;

import javassist.runtime.Desc;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.serializer.Serializer;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import ru.agrage.project.DAO.UserDAO;
import ru.agrage.project.DAO.UserProfileDAO;
import ru.agrage.project.Models.RatingModel;
import ru.agrage.project.Models.SignModel;
import ru.agrage.project.Models.UserModel;
import ru.agrage.project.Models.UserProfile;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by dmitry on 1/4/17.
 */

@Repository("userDAO")
public class UserDAOImpl extends AbstractDAOImpl<Integer, UserModel> implements UserDAO {
    @Resource(name = "userDAOProfile")
    private UserProfileDAO userProfileDAO;

    @Override
    public void create (UserModel userModel) throws HibernateException {
        UserProfile userProfile = new UserProfile();
        userModel.setProfile(userProfile);
        userProfile.setUserModel(userModel);
        getSession().save(userModel);
        getSession().flush();
    }

    @Override
    public boolean delete (int id) {
        UserModel userModel = new UserModel();
        userModel = getSession().get(UserModel.class, id);
        if (userModel == null) {
            return false;
        }
        getSession().delete(userModel);
        getSession().flush();
        return true;
    }

    @Override
    public UserModel getByUsername (String username) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<UserModel> criteriaQuery = criteriaBuilder.createQuery(UserModel.class);
        Root<UserModel> root = criteriaQuery.from(UserModel.class);
        Predicate condition = criteriaBuilder.equal(root.get("username"), username);
        criteriaQuery.where(condition);
        List<UserModel> user = getSession().createQuery(criteriaQuery).getResultList();
        UserModel object = user.get(0);
        return object;
    }

    @Override
    public boolean usernameExists (String username) {
        @SuppressWarnings("unchecked")
        Criteria criteria = getSession().createCriteria(UserModel.class).add(Restrictions.eq("username", username));
        if (criteria.list().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean emailExists (String email) {
        @SuppressWarnings("unchecked")
        Criteria criteria = getSession().createCriteria(UserModel.class).add(Restrictions.eq("email", email));
        if (criteria.list().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean passwordMatch (SignModel object) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<UserModel> criteriaQuery = criteriaBuilder.createQuery(UserModel.class);
        Root<UserModel> root = criteriaQuery.from(UserModel.class);
        Predicate condition = criteriaBuilder.equal(root.get("username"), object.getLogin());
        criteriaQuery.where(condition);
        List<UserModel> password = getSession().createQuery(criteriaQuery).getResultList();
        UserModel obj = password.get(0);
        if (!BCrypt.checkpw(object.getPassword(), obj.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public List<UserModel> getAll() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<UserModel> criteriaQuery = criteriaBuilder.createQuery(UserModel.class);
        Root<UserModel> root = criteriaQuery.from(UserModel.class);
        criteriaQuery.select(root);
        return getSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<RatingModel> getRating() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<RatingModel> criteriaQuery = criteriaBuilder.createQuery(RatingModel.class);
        Root<UserModel> root = criteriaQuery.from(UserModel.class);
        Join<UserModel, UserProfile> join = root.join("userProfile", JoinType.LEFT);
        CompoundSelection<RatingModel> cSelect = criteriaBuilder.construct(RatingModel.class, join.get("id"), root.get("username"), join.get("rating"));
        criteriaQuery.select(cSelect);
        Expression <String> expression = join.get("rating");
        criteriaQuery.orderBy(criteriaBuilder.desc(expression));
        List <RatingModel> getResult = getSession().createQuery(criteriaQuery).getResultList();
        return getResult;
    }

    @Override
    public UserModel getById (int id) {
        return getSession().get(UserModel.class, id);
    }

    @Override
    public int getIdByUsername (String username) {
        return 0;
    }
}
