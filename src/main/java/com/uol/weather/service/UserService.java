package com.uol.weather.service;


import com.uol.weather.exception.UserNotFoundException;
import com.uol.weather.model.Location;
import com.uol.weather.model.User;
import com.uol.weather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationService locationService;


    public User findUserById(Integer userId){
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found.");
        }
        return user.get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user) {

        User foundUser = findUserById(user.getId());

        if(user.getNome() !=null){
            foundUser.setNome(user.getNome());
        }

        if(user.getIdade() !=null){
            foundUser.setIdade(user.getIdade());
        }

        if(locationService.isValid(user.getLocation())){
            Location location = locationService.updateLocation(user.getLocation(), foundUser.getLocation().getId());
            foundUser.setLocation(location);
        }
        return userRepository.save(foundUser);
    }

    public void deleteUser(Integer userId) {
        findUserById(userId);
        userRepository.deleteById(userId);
    }
}
