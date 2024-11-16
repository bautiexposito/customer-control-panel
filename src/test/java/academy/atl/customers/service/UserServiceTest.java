package academy.atl.customers.service;

import academy.atl.customers.dto.UserDto;
import academy.atl.customers.exception.UserNotFoundException;
import academy.atl.customers.model.User;
import academy.atl.customers.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDao repository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        mockUsers.add(user1);

        when(repository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(repository).findAll();
    }

    @Test
    void testGetUserByID() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.com");

        when(repository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserByID(1);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        verify(repository).findById(1);
    }

    @Test
    void testGetUserByIDNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        User result = userService.getUserByID(1);

        assertNull(result);
        verify(repository).findById(1);
    }

    @Test
    void testSearchUser() {
        List<User> mockUsers = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setAddress("123 Test St");
        mockUsers.add(user);

        when(repository.findByEmailOrAddress("test@example.com", "123 Test St")).thenReturn(mockUsers);

        List<User> result = userService.searchUser("test@example.com", "123 Test St");

        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getEmail());
        verify(repository).findByEmailOrAddress("test@example.com", "123 Test St");
    }

    @Test
    void testAddUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setAddress("123 Test St");
        userDto.setPassword("password123");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        userService.addUser(userDto);

        verify(repository).save(captor.capture());
        User capturedUser = captor.getValue();
        assertEquals("John", capturedUser.getFirstName());
        assertNotNull(capturedUser.getPassword()); // The hashed password should exist
    }

    @Test
    void testUpdateUser() throws UserNotFoundException {
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setFirstName("Jane");
        existingUser.setLastName("Doe");
        existingUser.setEmail("jane.doe@example.com");

        UserDto userDto = new UserDto();
        userDto.setFirstName("Updated");
        userDto.setLastName("Name");
        userDto.setEmail("updated@example.com");
        userDto.setAddress("Updated Address");

        when(repository.findById(1)).thenReturn(Optional.of(existingUser));

        userService.updateUser(1, userDto);

        verify(repository).save(existingUser);
        assertEquals("Updated", existingUser.getFirstName());
        assertEquals("Name", existingUser.getLastName());
        assertEquals("updated@example.com", existingUser.getEmail());
    }

    @Test
    void testUpdateUserNotFound() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Test");
        userDto.setLastName("User");

        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1, userDto));
    }

    @Test
    void testRemoveUser() throws UserNotFoundException {
        User user = new User();
        user.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(user));

        userService.removeUser(1);

        verify(repository).delete(user);
    }

    @Test
    void testRemoveUserNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.removeUser(1));
    }
}
