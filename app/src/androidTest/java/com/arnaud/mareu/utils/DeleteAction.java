package com.arnaud.mareu.utils;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.arnaud.mareu.R;

import org.hamcrest.Matcher;

public class DeleteAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete);
        // Maybe check for null
        button.performClick();
    }
}



