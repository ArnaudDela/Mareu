package com.arnaud.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



import com.arnaud.mareu.di.DIMeeting;
import com.arnaud.mareu.di.DIRoom;
import com.arnaud.mareu.model.Meeting;
import com.arnaud.mareu.service.IntMeetingApiService;
import com.arnaud.mareu.service.IntRoomApiService;
import com.arnaud.mareu.service.RoomGenerator;

import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class MeetingUnitTest {

    private IntMeetingApiService mApiService;
    private final IntRoomApiService mRoomApiService = DIRoom.getNewInstanceRoomApiService();

    private final List<String> mCollaboratorList = Arrays.asList("unittest@lamzone.com", "unittest2@lamzone.com");
    private final Meeting mMeetingTest = new Meeting(RoomGenerator.FAKE_DI_ROOMS.get(5),new Date (),mCollaboratorList,  "test");

    @Before
    public void setup() {
        mApiService = DIMeeting.getNewInstanceMeetingApiService();
    }



    @Test
    public void getMeetingWithSuccess() {

        List<Meeting> meetingList = mApiService.getMeetingList();
        assertFalse(meetingList.isEmpty());

    }


    @Test
    public void createMeetingWithSuccess() {
        mApiService.addMeeting(mMeetingTest);
        assertTrue(mApiService.getMeetingList().contains(mMeetingTest));
    }



    @Test
    public void deleteMeetingWithSuccess() {
        mApiService.addMeeting(mMeetingTest);
        assertTrue(mApiService.getMeetingList().contains(mMeetingTest));
        mApiService.deleteMeeting(mMeetingTest);
        assertFalse(mApiService.getMeetingList().contains(mMeetingTest));
    }


   @Test
    public void filterMeetingsByDateWithSuccess() {

        mApiService.addMeeting(mMeetingTest);
        assertTrue(mApiService.getMeetingList().contains(mMeetingTest));
       List<Meeting> result = mApiService.filterMeetingListByDate(new Date());
        assertTrue(result.contains(mMeetingTest));


    }

    @Test
    public void filterMeetingsByRoomWithSuccess() {

        mApiService.addMeeting(mMeetingTest);
        assertTrue(mApiService.getMeetingList().contains(mMeetingTest));
        List<Meeting> result = mApiService.filterMeetingListByRoom(RoomGenerator.FAKE_DI_ROOMS.get(5));
        assertTrue(result.contains(mMeetingTest));


    }


}
