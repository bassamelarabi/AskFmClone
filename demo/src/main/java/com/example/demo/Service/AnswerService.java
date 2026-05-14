package com.example.demo.Service;

import com.example.demo.Entity.Answer;
import com.example.demo.Repository.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuestionService questionService;



    public Answer insert(String answerContent, Long questionId){
        Answer ans = new Answer();
        ans.setContent(answerContent);
        ans.setQuestion(questionService.findById(questionId));
        ans.setPublishedDate(LocalDate.now());
        return  answerRepo.save(ans);

    }
    public Integer deleteQuestionAnswers(Long questionId){
        return answerRepo.deleteByQuestionQuestionId(questionId);
    }

    public Answer findById(Long answerId){
        return answerRepo.findById(answerId).orElseThrow();
    }
    public List<Answer> findQuestionAnswers(Long questionId){
        return answerRepo.findByQuestionQuestionId(questionId);
    }

}
