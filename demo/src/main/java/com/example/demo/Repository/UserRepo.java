package com.example.demo.Repository;

import com.example.demo.Entity.RegisteredUser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<RegisteredUser, Long> {

    public int deleteByUsername(String username);
    public Optional<RegisteredUser> findByUsername(String username);

    @Query("select u from users u where u.username like %?1%")
    List<RegisteredUser> findByUsernameLike(String username);



//    @Query(name = "select user from users user where (:name is null or user.username like :name)")
//    List<RegisteredUser> filter(@Param("name") String name);
}
