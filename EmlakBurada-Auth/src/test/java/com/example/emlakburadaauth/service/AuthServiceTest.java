package com.example.emlakburadaauth.service;

import com.example.emlakburadaauth.dto.AuthRequest;
import com.example.emlakburadaauth.dto.AuthResponse;
import com.example.emlakburadaauth.entity.User;
import com.example.emlakburadaauth.repository.AuthDao;
import com.example.emlakburadaauth.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthDao authDao;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    void getToken() throws Exception {

        AuthRequest request = prepareAuthRequest("215215");
        String token = "token1234";
        User user = prepareUser("yunus@gmail.com");

        Mockito
                .when(authDao.findByEmail(request.getEmail()))
                .thenReturn(user);

        Mockito
                .when(jwtUtil.generateToken(user))
                .thenReturn(token);

        assertDoesNotThrow(() -> {

            AuthResponse response = authService.getToken(request);
            assertNotNull(response);
            assertEquals("token1234", response.getToken());

        });

    }

    @Test
    void getTokenUserIsNull() throws Exception {

        AuthRequest request = prepareAuthRequest("215215");
        String token = "token1234";
        User user = prepareUser("yunus@gmail.com");

        Mockito
                .when(authDao.findByEmail(request.getEmail()))
                .thenReturn(null);

        Mockito
                .when(jwtUtil.generateToken(user))
                .thenReturn(token);

        assertThrows(Exception.class, () -> {
            AuthResponse response = authService.getToken(request);


        }, "User is null");


    }

    @Test
    void getTokenPasswordIsIncorrect() throws Exception {

        AuthRequest request = prepareAuthRequest("215216");
        String token = "token1234";
        User user = prepareUser("yunus@gmail.com");

        Mockito
                .when(authDao.findByEmail(request.getEmail()))
                .thenReturn(user);

        Mockito
                .when(jwtUtil.generateToken(user))
                .thenReturn(token);

        assertThrows(Exception.class, () -> {
            AuthResponse response = authService.getToken(request);
            assertNotEquals(request.getPassword(), user.getPassword());
        }, "Password is incorrect");


    }

    private User prepareUser(String email) {

        return new User().builder().id(1).email(email).password("215215").build();

    }
//    private User prepareUser(String email) {
//        User user = new User();
//        user.setEmail(email);
//        user.setId(1);
//        user.setPassword("215215");
//        return  user;
//}


    private AuthRequest prepareAuthRequest(String password) {

        return new AuthRequest("yunus@gmail.com", password);
    }
}
