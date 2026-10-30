package com.example.demo.Service;


import com.example.demo.Entity.Follow;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Projection.FollowerListProjection;
import com.example.demo.Repository.FollowRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserService userService;

    /**
     * Follow another user.
     */
    @Transactional
    public void follow(Long followerId, String followingUsername) {
        RegisteredUser following = userService.findByUsername(followingUsername).
                orElseThrow (()->new EntityNotFoundException("No user with this username was found"));
        Long followingId = following.getUserId();
        follow(followerId,followingId);

    }

    @Transactional
    public void follow(Long followerId, Long followingId) {
        if (followerId.equals(followingId)) {
            throw new IllegalArgumentException("Users cannot follow themselves.");
        }

        RegisteredUser follower = userService.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("Follower not found."));

        RegisteredUser following = userService.findById(followingId)
                .orElseThrow(() -> new EntityNotFoundException("User to follow not found."));

        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            return; // idempotent operation
        }

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);
    }
    @Transactional
    public void unfollow(Long followerId, String followingUsername) {
        RegisteredUser following = userService.findByUsername(followingUsername).
                orElseThrow (()->new EntityNotFoundException("No user with this username was found"));
        Long followingId = following.getUserId();
        unfollow(followerId,followingId);

    }

    @Transactional
    public void unfollow(Long followerId, Long followingId) {
        RegisteredUser follower = userService.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("Follower not found."));

        RegisteredUser following = userService.findById(followingId)
                .orElseThrow(() -> new EntityNotFoundException("User to unfollow not found."));

        followRepository.deleteByFollowerAndFollowing(follower, following);
    }

    /**
     * Check whether one user follows another.
     */
    public boolean isFollowing(Long followerId, Long followingId) {
        RegisteredUser follower = userService.findById(followerId)
                .orElseThrow(() -> new EntityNotFoundException("Follower not found."));

        RegisteredUser following = userService.findById(followingId)
                .orElseThrow(() -> new EntityNotFoundException("Target user not found."));

        return followRepository.existsByFollowerAndFollowing(follower, following);
    }

    /**
     * Number of followers a user has.
     */
    public long getFollowersCount(Long userId) {
        RegisteredUser user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return followRepository.countByFollowing(user);
    }

    /**
     * Number of users this user follows.
     */
    public long getFollowingCount(Long userId) {
        RegisteredUser user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return followRepository.countByFollower(user);
    }

    /**
     * Paginated followers list.
     */
    public List<Follow> findAll(){
        return followRepository.findAll();
    }
    public List<RegisteredUser> getFollowers(Long userId,Pageable pageable){
        List<Follow> followers = followRepository.findByFollowingUserId(userId,pageable).getContent();
        List<RegisteredUser> followersList = new ArrayList<>();
        int n = followers.size();
        for(int i=0;i<n;i++)
            followersList.add(followers.get(i).getFollower());
        return followersList;
    }
    public List<RegisteredUser> getFollowing(Long userId,Pageable pageable){
        List<Follow> following = followRepository.findByFollowerUserId(userId,pageable).getContent();
        List<RegisteredUser> followingList = new ArrayList<>();
        int n = following.size();
        for(int i=0;i<n;i++)
            followingList.add(following.get(i).getFollowing());
        return followingList;
    }

    /**
     * Paginated following list.
     */
//    public List<RegisteredUser> getFollowing(String username) {
//        RegisteredUser user = userService.findByUsernameWithError(username);
//        return followRepository.findByFollower(user);
//    }
//    public List<RegisteredUser> getFollowing(Long userId) {
//        RegisteredUser user = userService.findByIdWithError(userId);
//
//        return followRepository.findByFollower(user);
//    }

}
