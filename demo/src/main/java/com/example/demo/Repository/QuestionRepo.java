package com.example.demo.Repository;

import com.example.demo.Entity.Question;
import com.example.demo.Entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

   public Page<Question> findBySenderUserId(Long id,Pageable pageable);

   public Page<Question> findByReceiverUserId(Long id, Pageable pageable);

   @Modifying
   public Integer deleteByQuestionId(Long questionId);

   //public List<Question> findAllOrderByPublishedDate();

}
