package com.arnaud.mareu.service;

import com.arnaud.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MeetingGenerator {
    public static final List<Meeting> FAKEMEETING = Arrays.asList(
            new Meeting(RoomGenerator.FAKE_DI_ROOMS.get(3), new Date (),CollaboratorGenerator.generateFakeCollaborators() , "Reunion A" ),
            new Meeting(RoomGenerator.FAKE_DI_ROOMS.get(7), new Date (),CollaboratorGenerator.generateFakeCollaborators() , "Reunion B" )
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(FAKEMEETING);
    }

}