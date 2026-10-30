package com.example.demo.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;




    public RegisteredUser findByIdWithError(Long id){
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }
    public Optional<RegisteredUser> findById(Long id){
        return userRepo.findById(id);
    }
    public RegisteredUser findByUsernameWithError(String username){
        return userRepo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }
    public Optional<RegisteredUser> findByUsername(String username){
        return userRepo.findByUsername(username);
    }
    public List<RegisteredUser> findByUsernameLike(String username){
        return userRepo.findByUsernameLike(username);
    }

    public RegisteredUser insert(UserDTO user){
        RegisteredUser user_ = new RegisteredUser();
        if(user.getBio()!=null)
            user_.setBio(user.getBio());
        if(user.getPassword()!=null)
            user_.setPassword(user.getPassword());
        if(user.getUsername()!=null)
            user_.setUsername(user.getUsername());
        if(user.getProfilePicLocation()!=null)
            user_.setProfilePicLocation(user.getProfilePicLocation());
        if(user.getAllowAnonymousQuestions()!=null)
            user_.setAllowAnonymousQuestions(user.getAllowAnonymousQuestions());
        return insert(user_);
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
    public List<RegisteredUser> findAll(Pageable pageable){
        return userRepo.findAll(pageable).getContent();
    }
    public int deleteByUsername(String username){
        return userRepo.deleteByUsername(username);
    }

    public void follow(RegisteredUser user){
    }
//    public List<RegisteredUser> filter (String name){
//        if(name.isBlank()){
//            name = null;
//        }
//        return userRepo.filter(name);
//
//    }



    public boolean existsById(Long userId){
        return userRepo.existsById(userId);
    }
  /*  public List<Question> findReceivedQuestions(Long userId){
        return questionService.findReceivedQuestions(userId);
    }

    public List<Question> findSentQuestions(Long userId){
        return questionService.findAskedQuestions(userId);
    }

   */


}
