package com.arnaud.mareu.service;


import com.arnaud.mareu.model.Meeting;
import com.arnaud.mareu.model.Room;

import java.util.Date;
import java.util.List;

public interface IntMeetingApiService {


    List<Meeting> getMeetingList();


    void addMeeting(Meeting meeting);


    void deleteMeeting(Meeting meeting);

    List<Meeting> filterMeetingListByDate(Date selectedDate);

    List<Meeting> filterMeetingListByRoom(Room selectedRoom);

}