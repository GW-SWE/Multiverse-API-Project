package swe.gw.apiproject;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AuthenticationServiceTest {
    String username = "username";
    String password = "password";
    @Test
    public void ifNoUserNameOrPasswordDoNotStore(){
        AuthenticationService authenticationService = new AuthenticationService();
        boolean isEncryptedNoUsername = authenticationService.encryptAndStore(null,password);
        boolean isEncryptedNoPassword = authenticationService.encryptAndStore(username, null);
        assertFalse(isEncryptedNoUsername);
        assertFalse(isEncryptedNoPassword);
    }
    @Test
    public void givenUserNameAndPasswordShouldBeAbleToEncryptAndStore(){
        AuthenticationService authenticationService = new AuthenticationService();
        boolean isEncrypted = authenticationService.encryptAndStore(username, password);
        boolean isVerified = authenticationService.verifyPassword(username,password);
        assertTrue(isEncrypted);
        assertTrue(isVerified);
    }
    @Test
    public void refusesLoginWithIncorrectPassword(){
        AuthenticationService authenticationService = new AuthenticationService();
        authenticationService.encryptAndStore(username, password);
        boolean isNotVerified = authenticationService.verifyPassword(username,"password2");
        assertFalse(isNotVerified);
    }
    @Test
    public void refusesLoginWithIncorrectUsername(){
        AuthenticationService authenticationService = new AuthenticationService();
        authenticationService.encryptAndStore(username, password);
        boolean isNotFound = authenticationService.verifyPassword("bob", password);
        assertFalse(isNotFound);
    }
}
