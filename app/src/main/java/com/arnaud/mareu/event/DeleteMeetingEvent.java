package com.arnaud.mareu.event;

import com.arnaud.mareu.model.Meeting;

public class DeleteMeetingEvent {
    public final Meeting meeting;


    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }


}
