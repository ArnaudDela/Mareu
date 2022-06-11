package com.arnaud.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollaboratorGenerator {

    public static final List<String> FAKE_COLLABORATORS = Arrays.asList(
            "arnaud@lamzone.com",
            "mario@lamzone.com",
            "luigi@lamzone.com",
            "roberto@lamzone.com",
            "paloma@lamzone.com",
            "antonella@lamzone.com",
            "yasuo@lamzone.com",
            "sheppard@lamzone.com",
            "sarra@lamzone.com",
            "tilk@lamzone.com",
            "viktor@lamzone.com",
            "obiwan@lamzone.com"
    );

    static List<String> generateFakeCollaborators() {
        return new ArrayList<>(FAKE_COLLABORATORS);
    }


}