package br.ufsm.csi.budgetmanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufsm.csi.budgetmanagerapi.model.Role;
import br.ufsm.csi.budgetmanagerapi.model.User;
import br.ufsm.csi.budgetmanagerapi.model.dto.UserDTO;
import br.ufsm.csi.budgetmanagerapi.model.form.RegisterForm;
import br.ufsm.csi.budgetmanagerapi.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public UserDTO registerUser(RegisterForm user) {
        return new UserDTO(userRepository.save(
            new User(
                user.getName(),
                user.getEmail(),
                new BCryptPasswordEncoder().encode(user.getPassword()),
                true,
                Role.USER
            )
        ));
    }

    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setActive(user.getActive());
        userToUpdate.setRole(user.getRole());
        return userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
