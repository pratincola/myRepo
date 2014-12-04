package com.prateek.android.loosewheels.db;

import com.google.common.collect.EvictingQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by prateek on 12/1/14.
 */
public class UserSession {
    private static final UserSession instance = new UserSession();
    private UserSession(){}

    public static UserSession getInstance(){
        return instance;
    }

    Double originLat;
    Double originLng;

    Double currLat;
    Double currLng;

    Double destLat;
    Double destLng;

    Double curSpeed;
    Double distToDest;


    public Hashtable<String, UserObject> friends = new Hashtable<String, UserObject>();

    public Double getOriginLat() {
        return originLat;
    }

    public void setOriginLat(Double originLat) {
        this.originLat = originLat;
    }

    public Double getOriginLng() {
        return originLng;
    }

    public void setOriginLng(Double originLng) {
        this.originLng = originLng;
    }

    public Double getCurrLat() {
        return currLat;
    }

    public void setCurrLat(Double currLat) {
        this.currLat = currLat;
    }

    public Double getCurrLng() {
        return currLng;
    }

    public void setCurrLng(Double currLng) {
        this.currLng = currLng;
    }

    public Double getDestLat() {
        return destLat;
    }

    public void setDestLat(Double destLat) {
        this.destLat = destLat;
    }

    public Double getDestLng() {
        return destLng;
    }

    public void setDestLng(Double destLng) {
        this.destLng = destLng;
    }

    public Double getCurSpeed() {
        return curSpeed;
    }

    public void setCurSpeed(Double curSpeed) {
        this.curSpeed = curSpeed;
    }

    public Double getDistToDest() {
        return distToDest;
    }

    public void setDistToDest(Double distToDest) {
        this.distToDest = distToDest;
    }

    public boolean contains(UserObject item) {
        return friends.containsKey(item.getUsername());
    }

    public boolean isEmpty(){
        return friends.isEmpty();
    }

    public void put(UserObject u){
        friends.put(u.getUsername(), u);
    }

    public UserObject get(UserObject u){
        return friends.get(u.getUsername());
    }

}
