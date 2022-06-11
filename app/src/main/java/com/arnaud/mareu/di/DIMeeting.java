package com.arnaud.mareu.di;

import com.arnaud.mareu.service.IntMeetingApiService;
import com.arnaud.mareu.service.ImplMeetingApiService;

public class DIMeeting {
    private static final IntMeetingApiService service = new ImplMeetingApiService();


    public static IntMeetingApiService getMeetingApiService() {
        return service;
    }


    public static IntMeetingApiService getNewInstanceMeetingApiService() {
        return new ImplMeetingApiService();


    }

}

