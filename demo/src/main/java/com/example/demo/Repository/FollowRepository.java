package com.example.demo.Repository;

import com.example.demo.Entity.Follow;
import com.example.demo.Entity.RegisteredUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Ref;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {


    /**
     * Check whether follower already follows following.
     */
    boolean existsByFollowerAndFollowing(RegisteredUser follower, RegisteredUser following);

    /**
     * Retrieve a specific follow relationship.
     */
    Optional<Follow> findByFollowerAndFollowing(RegisteredUser follower, RegisteredUser following);

    /**
     * Remove a follow relationship.
     */
    void deleteByFollowerAndFollowing(RegisteredUser follower,RegisteredUser following);

    /**
     * Count how many users a person follows.
     */
    long countByFollower(RegisteredUser follower);

    /**
     * Count how many followers a person has.
     */
    long countByFollowing(RegisteredUser following);

    /**
     * Paginated list of followers of a given user.
     */
    Page<Follow> findByFollowing(RegisteredUser following, RegisteredUser pageable);

    /**
     * Paginated list of users that a given user follows.
     */
    Page<Follow> findByFollower(RegisteredUser follower, Pageable pageable);
}

