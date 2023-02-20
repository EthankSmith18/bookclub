package com.ethan.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ethan.bookclub.models.LoginUser;
import com.ethan.bookclub.models.User;
import com.ethan.bookclub.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepo;

  // TO-DO: Write register and login methods!
  public User register(User newUser, BindingResult result) {
    // TO-DO - Reject values or register if no errors:

    // Reject if email is taken (present in database)
    Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    if (potentialUser.isPresent()) {
      result.rejectValue("email", "emailExists", "This email is already registered.");
    }

    // Reject if password doesn't match confirmation
    if (!newUser.getPassword().equals(newUser.getConfirm())) {
      result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    }
    if (result.hasErrors())
      return null;
    // Hash and set password, save user to database
    else {
      String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
      newUser.setPassword(hashed);
      return userRepo.save(newUser);

    }

  }

  public User login(LoginUser newLoginObject, BindingResult result) {
    Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    if (potentialUser.isEmpty()) {
      result.rejectValue("email", "notfound", "This email is not registered..");
    } else {
      User user = potentialUser.get();
      if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
        result.rejectValue("password", "Matches", "Invalid Password!");
      }
    }

    if (result.hasErrors())
      return null;
    else {
      User user = potentialUser.get();
      return user;
    }
  }

  public User getById(Long id) {
    Optional<User> potentialUser = userRepo.findById(id);
    if (potentialUser.isEmpty()) {
      return null;
    } else {
      return potentialUser.get();
    }
  }

  public User createUser(User user) {
    return userRepo.save(user);
  }

  public List<User> allUsers() {
    return userRepo.findAll();
  }

  public User getUser(Long id) {
    Optional<User> optionalUser = userRepo.findById(id);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    } else {
      return null;
    }

  }

}
