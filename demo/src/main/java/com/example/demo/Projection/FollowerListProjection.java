package com.example.demo.Projection;

import com.example.demo.Entity.RegisteredUser;

import java.util.List;

public interface FollowerListProjection {

    List<RegisteredUser> getFollowers();
}
