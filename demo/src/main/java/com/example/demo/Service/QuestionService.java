package com.example.demo.Service;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private UserService userService;

    public List<Question> findAll(){
        return questionRepo.findAll();
    }

    public Question findById(Long id){
        return questionRepo.findById(id).orElseThrow();
    }

    public Question addQuestion(String questionContent , Long senderId, Long receiverId){
        Question question = new Question();
        question.setContent(questionContent);
        question.setPublishedDate(LocalDate.now());
        RegisteredUser sender = userService.findById(senderId);
        RegisteredUser receiver = userService.findById(receiverId);
        question.setSender(sender);
        question.setReceiver(receiver);
        return questionRepo.save(question);
    }

    public Question updateQuestion(Long questionId ,String questionContent_){
        Question question = questionRepo.findById(questionId).get();
        if(questionContent_!=null)
            question.setContent(questionContent_);
        return questionRepo.save(question);
    }

    public Integer deleteQuestion(Long questionId){
        return questionRepo.deleteByQuestionId(questionId);

    }


    public List<Question> findAskedQuestions(Long senderId){
            return questionRepo.findBySenderUserId(senderId);
    }
    public List<Question> findReceivedQuestions(Long receiverId){
        return questionRepo.findByReceiverUserId(receiverId);
    }


}
