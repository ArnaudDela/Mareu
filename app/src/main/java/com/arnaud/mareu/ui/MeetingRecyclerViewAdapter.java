package com.arnaud.mareu.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arnaud.mareu.R;
import com.arnaud.mareu.event.DeleteMeetingEvent;
import com.arnaud.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter  extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
        public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);

        holder.color.getDrawable().setTint(Color.parseColor(meeting.getRoom().getColor()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time = sdf.format(meeting.getDate());
        time = time.substring(10, 16).replace(":", "h");
        holder.topic.setText(meeting.getTopic()+" - "+ time +" - "+ meeting.getRoom().getName());

        String collaborateurs ="";
        for ( String str: meeting.getCollaborators() ) {
            collaborateurs += str +" , ";
        }
        holder.collaborators.setText(collaborateurs);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.color)
        public ImageView color;
        @BindView(R.id.topic)
        public TextView topic;
        @BindView(R.id.collaborators)
        public TextView collaborators;
        @BindView(R.id.delete)
        public ImageView delete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

