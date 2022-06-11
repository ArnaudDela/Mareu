package com.arnaud.mareu.model;

import java.util.Date;
import java.util.List;


public class Meeting {
    private Room mRoom;
    private Date date;
    private List<String> collaborators;
    private String topic;

    public Meeting(Room Room, Date date, List<String> collaborators, String topic) {

        this.mRoom = Room;
        this.date = date;
        this.collaborators = collaborators;
        this.topic = topic;
    }


    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room Room) {
        this.mRoom = Room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public List<String> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<String> collaborators) {
        this.collaborators = collaborators;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


}