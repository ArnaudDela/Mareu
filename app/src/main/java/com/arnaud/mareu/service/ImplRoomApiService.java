package com.arnaud.mareu.service;

import com.arnaud.mareu.model.Room;

import java.util.List;

public class ImplRoomApiService implements IntRoomApiService {

    public final List<Room> mRoomList = RoomGenerator.generateRooms();

    @Override
    public List<Room> getRoomList() {
        return mRoomList;
    }
}

