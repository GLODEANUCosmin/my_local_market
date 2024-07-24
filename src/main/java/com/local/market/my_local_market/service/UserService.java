package com.local.market.my_local_market.service;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.UserDao;
import com.local.market.my_local_market.util.UserUtil;
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


    public void patchUser(Integer id, Map<String, String> partialUser) {
        User user = userRepository.getUserById(id);

        userUtil.patchUser(user, partialUser);

        userRepository.updateUser(user.getName(), user.getPassword(), user.getWallet(), id);
    }


    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }


}