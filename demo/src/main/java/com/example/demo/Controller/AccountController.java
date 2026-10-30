package com.example.demo.Controller;

import com.example.demo.Entity.Question;
import com.example.demo.Service.QuestionService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/feed") // get all questions from users on the app
    public ResponseEntity<List<Question>> findAllQuestions() {
        return ResponseEntity.ok(questionService.findAll());
    }
    @GetMapping("/questions") // get all questions sent for a specific user
    public ResponseEntity<List<Question>> findAllReceivedQuestionsForUser(@RequestParam Long userId,
                                                                          @RequestParam(required = false, defaultValue ="1")int pageNo,
                                                                          @RequestParam(required = false, defaultValue = "5")int pageSize,
                                                                          @RequestParam(required = false, defaultValue = "publishedDate") String sortBy,
                                                                          @RequestParam(required = false, defaultValue = "desc") String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc"))
            sort = Sort.by(sortBy).descending();
        else sort = Sort.by(sortBy).ascending();
        return ResponseEntity.ok(questionService.findReceivedQuestions(userId, PageRequest.of(pageNo,pageSize)));
    }

    @PostMapping("/questions")
    public ResponseEntity<Question> addQuestionForUser(@RequestBody Long userId, @RequestBody String questionContent,
                                                       @RequestBody Long receiverId){
        return ResponseEntity.ok(questionService.addQuestion(questionContent,userId, receiverId));
    }
    @DeleteMapping("/questions")
    public ResponseEntity<Integer> deleteQuestionForUser(@RequestParam Long userId,
                                                       @RequestBody Long questionId){
        return ResponseEntity.ok(questionService.deleteQuestion(questionId, userId));
    }
//    @GetMapping("/{userId}/questions/asked")
//    public ResponseEntity<List<Question>> findAskedQuestion(@PathVariable Long userId){
//        return ResponseEntity.ok(questionService.findAskedQuestions(userId));
//    }







}
