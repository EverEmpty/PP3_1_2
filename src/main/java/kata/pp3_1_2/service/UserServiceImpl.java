package kata.pp3_1_2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kata.pp3_1_2.dao.UserDao;
import kata.pp3_1_2.model.User;



import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> userList(){
        return userDao.userList();
    }

    @Transactional
    @Override
    public void saveUser(User user){
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(long ID) {
        userDao.deleteUser(ID);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long ID) {
        return userDao.getUserById(ID);
    }
}
