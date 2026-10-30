package com.example.demo.Repository;

import com.example.demo.Entity.Follow;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Projection.FollowerListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    long countByFollowingUserId(Long followingUserId);

    /**
     * Paginated list of followers of a given user.
     */
    //Page<Follow> findByFollowing(RegisteredUser following, RegisteredUser pageable);

    //this JPQL

    Page<Follow> findByFollowingUserId(Long userId,Pageable pageable);


    Page<Follow> findByFollowing(RegisteredUser followingUser,Pageable pageable);
    /**
     * Paginated list of users that a given user follows.

     */

    Page<Follow> findByFollowerUserId(Long userId, Pageable pageable);
    //Page<Follow> findByFollower(RegisteredUser follower, Pageable pageable);

    //@Query("")
    //List<FollowingListProjection> findByFollower(RegisteredUser follower);
}

