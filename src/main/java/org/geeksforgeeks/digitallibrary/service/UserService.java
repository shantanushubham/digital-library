package org.geeksforgeeks.digitallibrary.service;

import org.geeksforgeeks.digitallibrary.exceptions.ResourceNotFoundException;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.geeksforgeeks.digitallibrary.model.UserPrincipal;
import org.geeksforgeeks.digitallibrary.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(UserModel userModel) {
        return this.userRepository.addUser(userModel);
    }

    public UserModel getUserByEmail(String email) {
        return this.userRepository.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserModel userModel = this.getUserByEmail(username);
            return new UserPrincipal(userModel);
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
