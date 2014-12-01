package com.prateek.android.loosewheels.db;

import com.google.common.collect.EvictingQueue;
import com.prateek.android.loosewheels.integration.UserObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by prateek on 11/30/14.
 */
public class UserObjectHistory {
    private static final UserObjectHistory instance = new UserObjectHistory();
    private UserObjectHistory(){}

    public static UserObjectHistory getInstance(){
        return instance;
    }


    private EvictingQueue<UserObject> list = EvictingQueue.create(10);

    public void enqueue(UserObject item) {
        list.add(item);
    }
    public UserObject dequeue() {
        return list.poll();
    }



    public boolean hasItems() {
        return !list.isEmpty();
    }
    public int size() {
        return list.size();
    }
    public boolean contains(UserObject item) {
        return list.contains(item);
    }
    public UserObject peek() {
        return list.peek();
    }
}
