package com.example.demo.Repository;

import com.example.demo.Entity.RegisteredUser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<RegisteredUser, Long> {

    public int deleteByusername(String username);
}
