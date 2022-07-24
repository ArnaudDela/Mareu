package com.arnaud.mareu.ui;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.arnaud.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.arnaud.mareu.R;
import com.arnaud.mareu.utils.DeleteAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class AddMeetingInstrumentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);


    // This is fixed
    private static int ITEMS_COUNT = 2;

    @Test
    public void addMeetingInstrumentTest2() {

        // cliquer sur le bouton d'ajout meeting
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        // Ajouter le text du subject meeting
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.topic_Meeting),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Test"), closeSoftKeyboard());

        // selection de la salle de réunion
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_room),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_add_meeting),
                                        1),
                                2)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction constraintLayout = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        constraintLayout.perform(click());

        // ajout des collaborateurs

        ViewInteraction appCompatMultiAutoCompleteTextView = onView(
                allOf(withId(R.id.collaborators_picker),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_meeting),
                                        childAtPosition(
                                                withId(R.id.activity_add_meeting),
                                                0)),
                                3)));
        appCompatMultiAutoCompleteTextView.perform(replaceText("arnaud@lamzone.com, sarra@lamzone.com"));

        // selection de la date et de l'heure
        ViewInteraction appCompatButton = onView(
                allOf( withText("Date et heure"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Save Meeting"),
                        isDisplayed()));
        appCompatButton3.perform(click());

        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT+1));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
