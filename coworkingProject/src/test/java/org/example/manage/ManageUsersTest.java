package org.example.manage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ManageUsersTest {
    private ManageUsers manageUsers;

    @Before
    public void setUp() {
        manageUsers = new ManageUsers();
    }

    @Test
    public void useradd_userDoesNotExist() {
        // Проверяем успешное добавление нового пользователя
        manageUsers.useradd("user1", "User One", "password1");
        assertNotNull(manageUsers.getUser("user1"));
        assertEquals("User One", manageUsers.getUser("user1").getName());
    }

    @Test
    public void useradd_userAlreadyExists() {
        // Попытка добавить существующего пользователя
        manageUsers.useradd("user1", "User One", "password1");
        manageUsers.useradd("user1", "User two", "password1");
        assertEquals("User One", manageUsers.getUser("user1").getName());

    }
}