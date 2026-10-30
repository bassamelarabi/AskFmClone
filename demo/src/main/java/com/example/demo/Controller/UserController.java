package com.example.demo.Controller;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Service.FollowService;
import com.example.demo.Service.QuestionService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    FollowService followService;

    @GetMapping()
    public ResponseEntity<List<RegisteredUser>> findAllUsers(@RequestParam(required = false, defaultValue ="1")int pageNo,
                                                             @RequestParam(required = false, defaultValue = "5")int pageSize,
                                                             @RequestParam(required = false, defaultValue = "userId") String sortBy,
                                                             @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort = Sort.by(sortBy).ascending();
        else sort = Sort.by(sortBy).descending();
        return ResponseEntity.ok(userService.findAll(PageRequest.of(pageNo-1, pageSize,sort)));
    }


    @GetMapping("/search")
    public ResponseEntity<RegisteredUser> findById(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.findByIdWithError(userId));
    }
    @GetMapping("/search/name")
    public ResponseEntity<List<RegisteredUser>> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsernameLike(username));
    }
    @GetMapping("search/name/exact")
    public ResponseEntity<RegisteredUser> findByUsernameExact(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsernameWithError(username));
    }
    /// /////////for a specific user

    @GetMapping("/{username}")
    public ResponseEntity<List<Question>> findAllUserReceivedQuestion(@RequestParam String username,
                                                                      @RequestParam(required = false, defaultValue ="1")int pageNo,
                                                                      @RequestParam(required = false, defaultValue = "5")int pageSize,
                                                                      @RequestParam(required = false, defaultValue = "publishedDate") String sortBy,
                                                                      @RequestParam(required = false, defaultValue = "desc") String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc"))
            sort = Sort.by(sortBy).descending();
        else sort = Sort.by(sortBy).ascending();

        return ResponseEntity.ok(questionService.findReceivedQuestions(username, PageRequest.of(pageNo-1, pageSize,sort)));
    }

    @PostMapping("/{username}")
    public ResponseEntity<Question> addQuestionForUser(@RequestBody QuestionDTO question ,
                                                       @RequestParam String username){
        return ResponseEntity.ok(questionService.addQuestion(question, username));
    }
    @GetMapping("/accounts/{username}/questions/asked")
    public ResponseEntity<List<Question>> findAskedQuestion(@RequestParam String username,
                                                            @RequestParam(required = false, defaultValue ="1")int pageNo,
                                                            @RequestParam(required = false, defaultValue = "5")int pageSize,
                                                            @RequestParam(required = false, defaultValue = "publishedDate") String sortBy,
                                                            @RequestParam(required = false, defaultValue = "desc") String sortDir){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc"))
            sort = Sort.by(sortBy).descending();
        else sort = Sort.by(sortBy).ascending();
        return ResponseEntity.ok(questionService.findAskedQuestions(username,PageRequest.of(pageNo-1,pageSize,sort)));
    }

    @GetMapping("/accounts/{username}/questions/received")
    public ResponseEntity<List<Question>> findReceivedQuestion(@RequestParam String username,
                                                               @RequestParam(required = false, defaultValue ="1")int pageNo,
                                                               @RequestParam(required = false, defaultValue = "5")int pageSize,
                                                               @RequestParam(required = false, defaultValue = "publishedDate") String sortBy,
                                                               @RequestParam(required = false, defaultValue = "desc") String sortDir){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("desc"))
            sort = Sort.by(sortBy).descending();
        else sort = Sort.by(sortBy).ascending();
        return ResponseEntity.ok(questionService.findReceivedQuestions(username,PageRequest.of(pageNo-1,pageSize,sort)));
    }

    @PostMapping("/{username}/follow") // works
    public ResponseEntity<Long> followUser(@RequestBody Long senderId, @RequestBody String followingUsername){
        followService.follow(senderId, followingUsername);
        return ResponseEntity.ok(1L);
    }
    @PostMapping("/{username}/unfollow")  //BUG//////////
    public ResponseEntity<Long> unfollowUser(@RequestBody Long senderId, @RequestBody String followingUsername){
        followService.unfollow(senderId, followingUsername);
        return ResponseEntity.ok(1L);
    }
    @GetMapping("/{username}/follow/followingList")
    public ResponseEntity<List<RegisteredUser>> getFollowingList(@RequestParam Long userId,@RequestParam(required = false, defaultValue ="1")int pageNo,
                                                                 @RequestParam(required = false, defaultValue = "5")int pageSize){

        return ResponseEntity.ok(followService.getFollowing(userId,PageRequest.of(pageNo-1,pageSize)));
    }
    @GetMapping("/{username}/follow/followersList")
    public ResponseEntity<?> getFollowersList(@RequestParam Long userId,
                                              @RequestParam(required = false, defaultValue ="1")int pageNo,
                                              @RequestParam(required = false, defaultValue = "5")int pageSize){
        return ResponseEntity.ok(followService.getFollowers(userId,PageRequest.of(pageNo-1,pageSize)));
    }




}
