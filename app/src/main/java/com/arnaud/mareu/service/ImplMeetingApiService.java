package com.arnaud.mareu.service;

import com.arnaud.mareu.model.Meeting;
import com.arnaud.mareu.model.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImplMeetingApiService implements IntMeetingApiService {

    public final List<Meeting> meetingList = MeetingGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingList.remove(meeting);
    }

    @Override
    public List<Meeting> filterMeetingListByDate(Date selectedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Meeting> result = new ArrayList<>();

        for (Meeting m: meetingList) {

            if(sdf.format(m.getDate()).equals(sdf.format(selectedDate))){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Meeting> filterMeetingListByRoom(Room selectedRoom) {
        List<Meeting> result = new ArrayList<>();

        for (Meeting m: meetingList) {
            if(m.getRoom().equals(selectedRoom)){
                result.add(m);
            }
        }
        return result;

    }


}