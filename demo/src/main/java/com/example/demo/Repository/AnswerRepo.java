package com.example.demo.Repository;

import com.example.demo.Entity.Answer;
import com.example.demo.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {

    public List<Answer> findByQuestionQuestionId(Long questionId);
    @Modifying
    public Integer deleteByQuestionQuestionId(Long questionId);
}
