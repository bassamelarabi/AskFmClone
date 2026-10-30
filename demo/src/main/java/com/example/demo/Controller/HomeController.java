package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Service.FollowService;
import com.example.demo.Service.QuestionService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;


    @Autowired
    private FollowService followService;

    @PostMapping("/register")
    public ResponseEntity<RegisteredUser> addNewUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.insert(user));
    }
    @GetMapping("/follow")
    public ResponseEntity<?> findAllfollowRelation(){
        return ResponseEntity.ok(followService.findAll());
    }

    //log in method
//    @PutMapping()
//    public ResponseEntity<RegisteredUser> updateUser(@RequestBody RegisteredUser user) {
//        return ResponseEntity.ok(userService.update(user));
//    }
//    @DeleteMapping("/{name}")
//    public ResponseEntity<Integer> deleteUserById(@PathVariable String userName) {
//        return ResponseEntity.ok(userService.deleteByUsername(userName));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Integer> deleteUserById(@PathVariable Long id) {
//        userService.deleteById(id);
//        return ResponseEntity.ok(1);
//    }







}
