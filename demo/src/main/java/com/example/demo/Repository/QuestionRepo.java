package com.example.demo.Repository;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

   public List<Question> findBySenderUserId(Long id);

   public List<Question> findByReceiverUserId(Long id);

   @Modifying
   public Integer deleteByQuestionId(Long questionId);

}
