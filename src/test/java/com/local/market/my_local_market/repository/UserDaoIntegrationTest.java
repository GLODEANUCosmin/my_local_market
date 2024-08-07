package com.local.market.my_local_market.repository;

import com.local.market.my_local_market.exceptions.MarketNotFoundException;
import com.local.market.my_local_market.exceptions.UserNotFoundException;
import com.local.market.my_local_market.model.User;
import com.local.market.my_local_market.repository.mappers.UserRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

public class UserDaoIntegrationTest {

    DataSource dataSource;
    UserDao userDao;

    @BeforeEach
    public void setUp(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("reset.sql")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        userDao = new UserDao();
        userDao.setDataSource(dataSource);
    }

    @Test
    void getAllUsersTest() {


        List<User> Users = userDao.getAllUsers();

        assertNotNull(Users);
        assertEquals(4, Users.size());
        assertEquals("User1",Users.get(0).getName());
        assertEquals("Password1",Users.get(0).getPassword());
        assertEquals(1.45F,Users.get(0).getWallet());
        assertEquals(1,Users.get(0).getId());


    }

    @Test
    void createUserTest() {
        assertEquals(4, userDao.getAllUsers().size());

        userDao.createUser("New User", 1.33F, "Password");

        assertEquals(5, userDao.getAllUsers().size());
    }

    @Test
    void updateUserTest() {
        User usr;

        usr = userDao.getUserById(1);
        assertEquals("User1", usr.getName());
        assertEquals("Password1", usr.getPassword());
        assertEquals(1.45F, usr.getWallet());

        userDao.updateUser("Updated Name", "Updated Password", 12.34F, 1);

        usr = userDao.getUserById(1);
        assertEquals("Updated Name", usr.getName());
        assertEquals("Updated Password", usr.getPassword());
        assertEquals(12.34F, usr.getWallet());

    }

    @Test
    void updateUserNameTest() {
        User usr;

        usr = userDao.getUserById(1);
        assertEquals("User1", usr.getName());

        userDao.updateUserName("Updated Name", 1);

        usr = userDao.getUserById(1);
        assertEquals("Updated Name", usr.getName());
    }

    @Test
    void updateUserWalletTest() {
        User usr;

        usr = userDao.getUserById(1);
        assertEquals(1.45f, usr.getWallet());

        userDao.updateUserWallet(5.67f, 1);

        usr = userDao.getUserById(1);
        assertEquals(5.67f, usr.getWallet());
    }

    @Test
    void updateUserPasswordTest() {
        User usr;

        usr = userDao.getUserById(1);
        assertEquals("Password1", usr.getPassword());

        userDao.updateUserPassword("Updated Password", 1);

        usr = userDao.getUserById(1);
        assertEquals("Updated Password", usr.getPassword());
    }

    @Test
    void deleteUserTest() {
        assertNotNull(userDao.getUserById(3));

        userDao.deleteUser(3);

        assertThrows(UserNotFoundException.class, ()->userDao.getUserById(3));
    }

    @Test
    void getUserByIdTest() {
        User result=userDao.getUserById(1);

        assertNotNull(result);
        assertEquals("User1",result.getName());
    }

    @Test
    void getUserByIdNotFoundTest() {
        assertThrows(UserNotFoundException.class, () -> userDao.getUserById(10));
    }


}
