package com.arnaud.mareu.ui;

import static com.arnaud.mareu.service.CollaboratorGenerator.FAKE_COLLABORATORS;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arnaud.mareu.R;
import com.arnaud.mareu.di.DIMeeting;
import com.arnaud.mareu.model.Meeting;
import com.arnaud.mareu.model.Room;
import com.arnaud.mareu.service.IntMeetingApiService;
import com.arnaud.mareu.service.RoomGenerator;
import com.arnaud.mareu.ui.customSpinner.SpinnerRoomAdapter;
import com.arnaud.mareu.ui.customizableDialog.DateTimeDialog;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements DateTimeDialog.DateTimeDialogListener {


    private Spinner spinner;
    private DialogFragment dateTimeDialog;
    private MultiAutoCompleteTextView collaborators;
    private Button createMeeting;
    private TextInputEditText topicMeeting;
    private TextView selectedDate;
    private IntMeetingApiService mApiService;
    private  Date meetingDate=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mApiService = DIMeeting.getMeetingApiService();


        // recuperation de topic meeting et selectedDate
        topicMeeting = findViewById(R.id.topic_Meeting);
        selectedDate = findViewById(R.id.selectedDate);

        // liste des salles
        this.spinner = (Spinner) this.findViewById(R.id.spinner_room);
        SpinnerRoomAdapter adapter = new SpinnerRoomAdapter(this,
                R.layout.room_layout,
                R.id.color,
                R.id.name,
                RoomGenerator.FAKE_DI_ROOMS);
        this.spinner.setAdapter(adapter);

        // collaborators
        collaborators =(MultiAutoCompleteTextView)findViewById(R.id.collaborators_picker);
        ArrayAdapter adaptercollaborators
                = new ArrayAdapter(this,android.R.layout.simple_list_item_1,FAKE_COLLABORATORS);
        collaborators.setAdapter(adaptercollaborators);
        collaborators.setThreshold(1);
        collaborators.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        // meeting date
        Button select_date_time = this.findViewById(R.id.select_date_time);
        select_date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // instantiation du dialog
                dateTimeDialog = new DateTimeDialog();
                FragmentManager fragmentManager = AddMeetingActivity.this.getSupportFragmentManager();
                // Show:
                dateTimeDialog.show(fragmentManager, "Dialog");
            }
        });

        // button create meeting
        createMeeting = (Button) findViewById(R.id.create);
        createMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // collecter les objets
                String topic = topicMeeting.getText().toString();

                Room meetingRoom = (Room) spinner.getSelectedItem();
                String textCollaborateurs = collaborators.getText().toString();
               String [] resultat = textCollaborateurs.split(",");
                List<String> collaborators= Arrays.asList(resultat) ;
                // controler les zones

                if(meetingDate != null && topic.trim() != "" && meetingRoom != null && collaborators.size()>=2) {
                    // remplir meeting object
                    Meeting meetingToAdd = new Meeting(meetingRoom,meetingDate,collaborators, topic);
                    // ajouter meeting dans la liste generators
                    mApiService.addMeeting(meetingToAdd);
                    finish();
                }
                else {
                    // afficher un toast pour verifier le contenu saisi
                    Toast toast =  Toast.makeText(AddMeetingActivity.this, "Merci de vérifier les champs à renseigner", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 20, 30);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof DateTimeDialog) {
            DateTimeDialog dialog = (DateTimeDialog) fragment;
            dialog.setListener(this);
        }
    }

    @Override
    public void onSelectedDateTime(DateTimeDialog dateTimeDialog) {

        DatePicker datePicker = dateTimeDialog.getDialog().findViewById(R.id.datePicker);
        TimePicker timePicker = dateTimeDialog.getDialog().findViewById(R.id.timePicker);

        String strDate = datePicker.getDayOfMonth() +"/"+(datePicker.getMonth()+1) +"/"+ datePicker.getYear() + " " + timePicker.getHour() + ":"+timePicker.getMinute()+":00";
        selectedDate.setText(strDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            meetingDate= sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            meetingDate= null;
        }

    }


}
