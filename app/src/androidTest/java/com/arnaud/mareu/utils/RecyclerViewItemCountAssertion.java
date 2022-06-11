package com.arnaud.mareu.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

public class RecyclerViewItemCountAssertion implements ViewAssertion {

    public final Matcher<Integer> matcher;


    public static ViewAssertion withItemCount(int expectedCount){
        return withItemCount(is(expectedCount));
    }

    public static ViewAssertion withItemCount(Matcher<Integer> matcher){
        return new RecyclerViewItemCountAssertion(matcher);
    }

    private RecyclerViewItemCountAssertion( Matcher<Integer> matcher){
        this.matcher = matcher;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {

        if(noViewFoundException != null){
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), matcher);
    }
}
