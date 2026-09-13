package com.example.demo.Service;


import com.example.demo.Entity.Follow;
import com.example.demo.Entity.RegisteredUser;
import com.example.demo.Repository.FollowRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
//    public Page<Follow> getFollowers(Long userId, Pageable pageable) {
//        RegisteredUser user = userService.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found."));
//
//        return followRepository.findByFollowing(user, pageable);
//    }

    /**
     * Paginated following list.
     */
    public Page<Follow> getFollowing(Long userId, Pageable pageable) {
        RegisteredUser user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return followRepository.findByFollower(user, pageable);
    }

}
