package com.engineering.journalApp.controller;

import com.engineering.journalApp.entity.User;
import com.engineering.journalApp.service.JournalEntryService;
import com.engineering.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping//http:localhost:8080/journal GET
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping//http:localhost:8080/journal Post
    public User createUser(@RequestBody User user){
        userService.saveEntry(user);
        return user;
    }

    @GetMapping("user/{userName}")
    public User getUserByName(@PathVariable String userName){
        return userService.findByUserName(userName);
    }

    @DeleteMapping("user/{userName}")
    public void deleteUser(@PathVariable ObjectId id){
        userService.deleteById(id);
    }

    @PutMapping("user/{userName}")
    public ResponseEntity<?> updateUserByName(@RequestBody User user,@PathVariable String userName){
    User oldUserName=userService.findByUserName(userName);
    if(oldUserName != null){
        oldUserName.setUserName(user.getUserName());
        oldUserName.setPassword(user.getPassword());
        userService.saveEntry(oldUserName);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
