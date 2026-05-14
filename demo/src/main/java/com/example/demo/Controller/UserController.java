package com.example.demo.Controller;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Service.QuestionService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public ResponseEntity<List<RegisteredUser>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{Id}")
    public ResponseEntity<RegisteredUser> findById(@PathVariable Long Id) {
        return ResponseEntity.ok(userService.findById(Id));
    }
    @PostMapping()
    public ResponseEntity<RegisteredUser> addUser(@RequestBody RegisteredUser user) {
        return ResponseEntity.ok(userService.insert(user));
    }
    @PutMapping()
    public ResponseEntity<RegisteredUser> updateUser(@RequestBody RegisteredUser user) {
        return ResponseEntity.ok(userService.update(user));
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Integer> deleteUserById(@PathVariable String userName) {
        return ResponseEntity.ok(userService.deleteByUsername(userName));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(1);
    }

    /// ///////////Questions
    @PostMapping("/{userId}/questions")
    public ResponseEntity<Question> addQuestionForUser(@PathVariable Long userId, @RequestBody String questionContent,
                                                       @RequestBody Long receiverId){
        return ResponseEntity.ok(questionService.addQuestion(questionContent,userId, receiverId));
    }
    @GetMapping("/{userId}/questions/asked")
    public ResponseEntity<List<Question>> findAskedQuestion(@PathVariable Long userId){
        return ResponseEntity.ok(questionService.findAskedQuestions(userId));
    }

    @GetMapping("/{userId}/questions/received")
    public ResponseEntity<List<Question>> findReceivedQuestion(@PathVariable Long userId){
        return ResponseEntity.ok(questionService.findReceivedQuestions(userId));
    }






}
