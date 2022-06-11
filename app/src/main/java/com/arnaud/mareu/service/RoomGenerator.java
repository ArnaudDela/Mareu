package com.arnaud.mareu.service;

import com.arnaud.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomGenerator {
    public static final List<Room> FAKE_DI_ROOMS = Arrays.asList(
            new Room("Salle noxus",1, "#990000" ),
            new Room("Salle piltover",2, "#00EE66" ),
            new Room("Salle madagascar",3,"#FF6600" ),
            new Room("Salle andromeda",4,"#3300EE" ),
            new Room("Salle alistar",5, "#CC6600" ),
            new Room("Salle shepard",6, "#3300FF" ),
            new Room("Salle swain",7,"#FF0000" ),
            new Room("Salle galactus",8,"#000000" ),
            new Room("Salle ender",9,"#FF3399" ),
            new Room("Salle trixion",10,"#99CCFF" )
    );

    static List<Room> generateRooms() {
        return new ArrayList<>(FAKE_DI_ROOMS);
    }


}