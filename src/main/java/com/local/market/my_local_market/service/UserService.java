package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.UserDao;
import com.local.market.my_local_market.util.UserUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserDao userRepository;
    private final UserUtil userUtil;

    @Autowired
    public UserService(UserDao userRepository, UserUtil userUtil) {
        this.userRepository = userRepository;
        this.userUtil = userUtil;
    }


    public void registerUser(User user) {
        userRepository.createUser(user.getName(), user.getWallet(), user.getPassword());
    }


    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }


    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    public void updateUser(Integer id, User user) {
        userRepository.updateUser(user.getName(), user.getPassword(), user.getWallet(), id);
    }

    @Transactional
    public void patchUser(Integer id, Map<String, String> partialUser) {
        User user = new User();


        userUtil.patchUser(user, partialUser);
        if (user.getName()!=null){ userRepository.updateUserName(user.getName(),id); }
        if (user.getWallet()!=null){ userRepository.updateUserWallet(user.getWallet(),id); }
        if (user.getPassword()!=null){ userRepository.updateUserPassword(user.getPassword(),id); }

    }


    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }


}