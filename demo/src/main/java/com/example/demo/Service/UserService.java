package com.example.demo.Service;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public Optional<RegisteredUser> findById(Long id){
        return userRepo.findById(id);
    }

    public RegisteredUser insert(RegisteredUser user){
        return userRepo.save(user);
    }
    public RegisteredUser update(RegisteredUser user){
        RegisteredUser userFound = userRepo.findById(user.getUserId()).orElseThrow();
        if(user.getBio()!=null)
            userFound.setBio(user.getBio());
        if(user.getPassword()!=null)
            userFound.setPassword(user.getPassword());
        if(user.getUsername()!=null)
            userFound.setUsername(user.getUsername());
        if(user.getProfilePicLocation()!=null)
            userFound.setProfilePicLocation(user.getProfilePicLocation());
        if(user.getQuestionsSent()!=null)
            userFound.setQuestionsSent(user.getQuestionsSent());
        if(user.getQuestionsReceived()!=null)
            userFound.setQuestionsReceived(user.getQuestionsReceived());


        return userRepo.save(userFound);
    }
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }
    public List<RegisteredUser> findAll(){
        return userRepo.findAll();
    }
    public int deleteByUsername(String username){
        return userRepo.deleteByusername(username);
    }
  /*  public List<Question> findReceivedQuestions(Long userId){
        return questionService.findReceivedQuestions(userId);
    }

    public List<Question> findSentQuestions(Long userId){
        return questionService.findAskedQuestions(userId);
    }

   */


}
