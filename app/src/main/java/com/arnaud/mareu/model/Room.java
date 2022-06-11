package com.arnaud.mareu.model;

import java.util.Objects;

public class Room {
    private String name;
    private int id;
    public String color;

    public Room(String name, int id, String color) {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room Room = (Room) o;
        return id == Room.id && Objects.equals(name, Room.name) && Objects.equals(color, Room.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, color);
    }


}