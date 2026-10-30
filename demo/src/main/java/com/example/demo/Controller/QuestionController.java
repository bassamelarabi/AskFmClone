package com.example.demo.Controller;

import com.example.demo.Entity.Answer;
import com.example.demo.Entity.Question;
import com.example.demo.Service.AnswerService;
import com.example.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping()
    public ResponseEntity<List<Question>> findAllQuestions(){
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> findQuestionById(@RequestParam Long questionId){
        return ResponseEntity.ok(questionService.findById(questionId));
    }
    @PutMapping("/{questionId}")
    public Question updateQuestion(@RequestParam Long questionId, @RequestParam String questionContent,
                                   @RequestParam Long userId){
        return questionService.updateQuestion(questionId,questionContent,userId);
    }
    @PostMapping()
    public ResponseEntity<Question> addQuestion(@RequestBody String questionContent, @RequestBody Long senderId, @RequestParam Long receiverId){
        return ResponseEntity.ok(questionService.addQuestion(questionContent, senderId, receiverId));
    }
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Integer> deleteQuestion(@RequestParam Long questionId, @RequestParam Long userId){
        return ResponseEntity.ok(questionService.deleteQuestion(questionId, userId));
    }

    ////////////////////////////////////////answers/////////////////////////////////////////


    @PostMapping("/{questionId}/answers")
    public ResponseEntity<Answer> addQuestionAnswer(@RequestParam String answer, @RequestParam Long questionId){
        return ResponseEntity.ok(answerService.answerQuestion(answer, questionId));
    }
    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<Answer>> getQuestionAnswers(@RequestParam Long questionId ){
        return ResponseEntity.ok(answerService.findQuestionAnswers(questionId));
    }
    @DeleteMapping("/{questionId}/answers")
    public ResponseEntity<Integer> deleteQuestionAnswers(@RequestParam Long questionId){
        return ResponseEntity.ok(answerService.deleteQuestionAnswers(questionId));
    }
    @GetMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<Answer> getAnswer(@RequestParam Long answerId ){
        return ResponseEntity.ok(answerService.findById(answerId));
    }
    //Update an asnwer
    //Delete an answer
    //delete all question answers




}
