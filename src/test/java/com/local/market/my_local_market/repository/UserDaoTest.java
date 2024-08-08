package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.exceptions.UserNotFoundException;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.mappers.MarketRowMapper;
import com.local.market.my_local_market.repository.mappers.UserRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplateMock;
    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsersTest() {
        User user = new User();
        user.setName("Name");
        user.setPassword("Password");
        user.setWallet(100.34F);
        user.setId(1);

        when(jdbcTemplateMock.query(anyString(), any(UserRowMapper.class)))
                .thenReturn(new ArrayList<User>(Collections.singleton(user)));


        List<User> Users = userDao.getAllUsers();

        assertNotNull(Users);
        assertEquals(1, Users.size());
        assertEquals("Name",Users.get(0).getName());
        assertEquals("Password",Users.get(0).getPassword());
        assertEquals(100.34F,Users.get(0).getWallet());
        assertEquals(1,Users.get(0).getId());


    }

    @Test
    void createUserTest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyFloat(), anyString())).thenReturn(1);


        int rowsAffected = userDao.createUser("New User", 1.33F, "Password");


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateUserTest() {

        when(jdbcTemplateMock.update(anyString(), anyString(), anyFloat(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = userDao.updateUser("Updated Name", "Updated Password", 12.34F, 1);


        assertEquals(1, rowsAffected);

    }

    @Test
    void updateUserNameTest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = userDao.updateUserName("Updated Name", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateUserWalletTest() {
        when(jdbcTemplateMock.update(anyString(), anyFloat(), anyInt())).thenReturn(1);


        int rowsAffected = userDao.updateUserWallet(12.3F, 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void updateUserPasswordTest() {
        when(jdbcTemplateMock.update(anyString(), anyString(), anyInt())).thenReturn(1);


        int rowsAffected = userDao.updateUserPassword("Updated Password", 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void deleteUserTest() {
        when(jdbcTemplateMock.update(anyString(), anyInt())).thenReturn(1);


        int rowsAffected = userDao.deleteUser( 1);


        assertEquals(1, rowsAffected);
    }

    @Test
    void getUserByIdTest() {
        User user = new User();
        user.setName("Name");
        user.setPassword("Password");
        user.setWallet(100.34F);
        user.setId(1);

        when(jdbcTemplateMock.queryForObject(anyString(), any(UserRowMapper.class), anyInt()))
                .thenReturn(user);

        User result=userDao.getUserById(1);

        assertNotNull(result);
        assertEquals("Name",result.getName());
    }

    @Test
    void getUserByIdNotFoundTest() {
        when(jdbcTemplateMock.queryForObject(anyString(), any(UserRowMapper.class), anyInt()))
                .thenThrow(new EmptyResultDataAccessException(1));


        assertThrows(UserNotFoundException.class, () -> userDao.getUserById(1));
    }
}