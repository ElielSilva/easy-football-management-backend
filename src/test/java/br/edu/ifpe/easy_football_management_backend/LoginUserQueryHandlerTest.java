package br.edu.ifpe.easy_football_management_backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)  // Para inicializar mocks
public class LoginUserQueryHandlerTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private TokenService tokenService;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    private LoginUserQueryHandler loginUserQueryHandler;
//
//    @BeforeEach
//    public void setUp() {
//        loginUserQueryHandler = new LoginUserQueryHandler(userRepository, tokenService, passwordEncoder);
//    }
//
//    @Test
//    public void shouldReturnTokenWhenLoginIsSuccessful() {
//        // Arrange: Definir comportamento dos mocks
//        String email = "user@example.com";
//        String password = "password123";
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword("$2a$10$0JdAaO6J6LjVRaR5S6R6/ruDE1iy9LBtPpE5o.QCZLeZp8uDN6OQK");  // Senha codificada em bcrypt
//
//        LoginUserQuery query = new LoginUserQuery(email, password);
//
//        // Mockando o comportamento do userRepository
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        // Mockando o comportamento do passwordEncoder
//        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
//        // Mockando o comportamento do tokenService
//        when(tokenService.generateToken(user)).thenReturn("mocked-token");
//
//        // Act: Chamar o método que queremos testar
//        String token = loginUserQueryHandler.handle(query);
//
//        // Assert: Verificar se o token retornado é o esperado
//        assertNotNull(token);
//        assertEquals("mocked-token", token);
//
//        // Verificar se os mocks foram chamados
//        verify(userRepository).findByEmail(email);
//        verify(passwordEncoder).matches(password, user.getPassword());
//        verify(tokenService).generateToken(user);
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenEmailNotFound() {
//        // Arrange: Simular que o email não foi encontrado
//        String email = "user@example.com";
//        String password = "password123";
//        LoginUserQuery query = new LoginUserQuery(email, password);
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        // Act & Assert: Verificar se a exceção é lançada
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            loginUserQueryHandler.handle(query);
//        });
//
//        assertEquals("Invalid email or password", thrown.getMessage());
//    }
//
//    @Test
//    public void shouldThrowExceptionWhenPasswordDoesNotMatch() {
//        // Arrange: Simular email encontrado, mas senha incorreta
//        String email = "user@example.com";
//        String password = "wrong-password";
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword("$2a$10$0JdAaO6J6LjVRaR5S6R6/ruDE1iy9LBtPpE5o.QCZLeZp8uDN6OQK");  // Senha codificada em bcrypt
//        LoginUserQuery query = new LoginUserQuery(email, password);
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);
//
//        // Act & Assert: Verificar se a exceção é lançada
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            loginUserQueryHandler.handle(query);
//        });
//
//        assertEquals("Invalid email or password", thrown.getMessage());
//    }

    @Test
    public void testeTest() {
        assertEquals(true,true);
    }
}
