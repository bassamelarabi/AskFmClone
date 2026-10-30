package com.example.demo.Service;

import com.example.demo.DTO.QuestionDTO;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Repository.QuestionRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private UserService userService;

//    public List<Question> findAllOrdered(){
//        return questionRepo.findAllOrderByPublishedDate();
//    }
    public List<Question> findAll(){
        return questionRepo.findAll();
    }

    public Question findById(Long id){
        return questionRepo.findById(id).orElseThrow();
    }

    public Question addQuestion(QuestionDTO question_, String receiverUsername){
        Question question = new Question();
        question.setContent(question_.getQuestionContent());
        question.setPublishedDate(LocalDate.now());
        RegisteredUser sender = userService.findByIdWithError(question_.getSenderId());
        RegisteredUser receiver = userService.findByUsernameWithError(receiverUsername);
        question.setSender(sender);
        question.setReceiver(receiver);
        return questionRepo.save(question);
    }
    public Question addQuestion(String questionContent , Long senderId, Long receiverId){
        //handle if the question length is more than 300 characters

        Question question = new Question();
        question.setContent(questionContent);
        question.setPublishedDate(LocalDate.now());
        RegisteredUser sender = userService.findByIdWithError(senderId);
        RegisteredUser receiver = userService.findByIdWithError(receiverId);
        question.setSender(sender);
        question.setReceiver(receiver);
        return questionRepo.save(question);
    }

    public Question updateQuestion(Long questionId ,String questionContent_, Long userId){
        if(Objects.equals(userId, findById(questionId).getSender().getUserId())) {
            Question question = questionRepo.findById(questionId).get();
            if (questionContent_ != null)
                question.setContent(questionContent_);
            return questionRepo.save(question);
        }
        else throw new IllegalArgumentException("user can only edit his questions");
    }

    public Integer deleteQuestion(Long questionId, Long userId){
        if(Objects.equals(userId, findById(questionId).getSender().getUserId()))
            return questionRepo.deleteByQuestionId(questionId);
        else throw new IllegalArgumentException("user can only delete his questions");
    }


    public List<Question> findAskedQuestions(String username,Pageable pageable){
        Long senderId = userService.findByUsernameWithError(username).getUserId();
            return questionRepo.findBySenderUserId(senderId,pageable).getContent();
    }
    public List<Question> findReceivedQuestions(String username, Pageable pageable){
        Long receiverId = userService.findByUsernameWithError(username).getUserId();
        return questionRepo.findByReceiverUserId(receiverId,pageable).getContent();
    }
    public List<Question> findAskedQuestions(Long senderId,Pageable pageable){
        return questionRepo.findBySenderUserId(senderId,pageable).getContent();
    }
    public List<Question> findReceivedQuestions(Long receiverId,Pageable pageable){
        return questionRepo.findByReceiverUserId(receiverId,pageable).getContent();
    }


}
