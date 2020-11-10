package data.models.user;

import org.junit.jupiter.api.Test;

import static data.models.user.UserAccount.USER;
import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {

    @Test
    void getAccountName() {
        USER.setAccountName("test");
        assertEquals("test", USER.getAccountName());
    }

    @Test
    void getPassword() {
        USER.setPassword("p");
        assertEquals("p", USER.getPassword());
    }

    @Test
    void getName() {
        USER.setName("B");
        assertEquals("B", USER.getName());
    }

    @Test
    void getEmailAddress() {
        USER.setEmailAddress("@");
        assertEquals("@", USER.getEmailAddress());
    }
}