package com.arnaud.mareu.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.arnaud.mareu.R;
import com.arnaud.mareu.databinding.ActivityMainBinding;
import com.arnaud.mareu.di.DIMeeting;
import com.arnaud.mareu.event.DeleteMeetingEvent;
import com.arnaud.mareu.model.Meeting;
import com.arnaud.mareu.model.Room;
import com.arnaud.mareu.service.IntMeetingApiService;
import com.arnaud.mareu.ui.customSpinner.SpinnerRoomAdapter;
import com.arnaud.mareu.ui.customizableDialog.DateTimeDialog;
import com.arnaud.mareu.ui.customizableDialog.RoomSpinnerDialog;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements DateTimeDialog.DateTimeDialogListener, RoomSpinnerDialog.RoomSpinnerDialogListener {


    FloatingActionButton addMeeting;


    private ActivityMainBinding binding;
    private IntMeetingApiService mApiService;
    private List<Meeting> mMeetingList;

    private DialogFragment dateTimeDialog;
    private DialogFragment roomSpinnerDialog;

   public RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DIMeeting.getMeetingApiService();



        mRecyclerView = (RecyclerView) findViewById(R.id.list_meetings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        addMeeting = (FloatingActionButton) findViewById(R.id.add_button);
        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initList() {
        mMeetingList = mApiService.getMeetingList();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetingList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dateFilter) {
            // instantiation du dialog
            dateTimeDialog = new DateTimeDialog();
            FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
            // Show:
            dateTimeDialog.show(fragmentManager, "Dialog");

            return true;
        }
        if (id == R.id.roomFilter) {
            // instantiation du dialog
            roomSpinnerDialog = new RoomSpinnerDialog();
            FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
            // Show:
            roomSpinnerDialog.show(fragmentManager, "Dialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {

        mApiService.deleteMeeting(event.meeting);
        initList();
    }
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof DateTimeDialog) {
            DateTimeDialog dialog = (DateTimeDialog) fragment;
            dialog.setListener(this);
        }
        if (fragment instanceof RoomSpinnerDialog) {
            RoomSpinnerDialog dialog = (RoomSpinnerDialog) fragment;
            dialog.setListener(this);
        }
    }

    @Override
    public void onSelectedDateTime(DateTimeDialog dateTimeDialog) {

        DatePicker datePicker = dateTimeDialog.getDialog().findViewById(R.id.datePicker);

        String strDate = datePicker.getDayOfMonth() +"/"+(datePicker.getMonth()+1) +"/"+ datePicker.getYear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            mMeetingList = mApiService.filterMeetingListByDate(sdf.parse(strDate));
            mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetingList));

        } catch (ParseException e) {
            e.printStackTrace();

        }

    }


    @Override
    public void onSelectedRoom(RoomSpinnerDialog roomSpinnerDialog) {

        Spinner spinner = (Spinner) roomSpinnerDialog.getDialog().findViewById(R.id.spinner_room);
        Room selectedRoom = (Room) spinner.getSelectedItem();
        mMeetingList = mApiService.filterMeetingListByRoom(selectedRoom);
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetingList));

    }
}