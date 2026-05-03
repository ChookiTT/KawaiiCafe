package cz.osu.swi_cafe;

import cz.osu.swi_cafe.repos.UserRepository;
import cz.osu.swi_cafe.services.UserService;
import cz.osu.swi_cafe.tables.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private final String rawPassword = "mojeHeslo123";

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("kavarnik");
        testUser.setPassword(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
    }

    @Test
    void login_ShouldReturnUser_WhenCredentialsAreCorrect() {
        when(userRepository.findByUsername("kavarnik")).thenReturn(Optional.of(testUser));

        User result = userService.login("kavarnik", rawPassword);

        assertNotNull(result);
        assertEquals("kavarnik", result.getUsername());
        verify(userRepository, times(1)).findByUsername("kavarnik");
    }

    @Test
    void login_ShouldThrowException_WhenPasswordIsWrong() {

        when(userRepository.findByUsername("kavarnik")).thenReturn(Optional.of(testUser));

        assertThrows(RuntimeException.class, () -> {
            userService.login("kavarnik", "spatneHeslo");
        });
    }

    @Test
    void login_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findByUsername("neexistuje")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.login("neexistuje", "heslo");
        });
    }
    @Test
    void updateUser_ShouldReturnUser_WhenCredentialsAreCorrect() {
        when(userRepository.findByUsername("kavarnik")).thenReturn(Optional.of(testUser));

    }
}