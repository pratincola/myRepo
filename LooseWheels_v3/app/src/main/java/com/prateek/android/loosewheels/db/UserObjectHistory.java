package com.prateek.android.loosewheels.db;

import android.location.Location;

import com.google.common.collect.EvictingQueue;

/**
 * Created by prateek on 11/30/14.
 */
public class UserObjectHistory {
    private static final UserObjectHistory instance = new UserObjectHistory();
    private UserObjectHistory(){}

    public static UserObjectHistory getInstance(){
        return instance;
    }


    private EvictingQueue<UserObject> list = EvictingQueue.create(2);
    private EvictingQueue<Location> locationList = EvictingQueue.create(2);


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



    public void enqueueLoc(Location item) {
        locationList.add(item);
    }
    public Location dequeueLoc() {
        return locationList.poll();
    }
    public boolean hasItemsLoc() {
        return !locationList.isEmpty();
    }
    public int sizeLoc() {
        return locationList.size();
    }
    public boolean containsLoc(Location item) {
        return locationList.contains(item);
    }
    public Location peekLoc() {
        return locationList.peek();
    }
}
