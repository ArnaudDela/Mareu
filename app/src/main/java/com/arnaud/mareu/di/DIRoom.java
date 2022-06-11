package com.arnaud.mareu.di;

import com.arnaud.mareu.service.ImplRoomApiService;
import com.arnaud.mareu.service.IntRoomApiService;

public class DIRoom {
    private static final IntRoomApiService service = new ImplRoomApiService();


    public static IntRoomApiService getRoomApiService() {
        return service;
    }


    public static IntRoomApiService getNewInstanceRoomApiService() {
        return new ImplRoomApiService();
    }


}

