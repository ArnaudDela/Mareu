package com.arnaud.mareu.ui.customSpinner;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arnaud.mareu.R;
import com.arnaud.mareu.model.Room;

import java.util.List;

public class SpinnerRoomAdapter extends BaseAdapter {

    private LayoutInflater flater;
    private List<Room> list;
    private int roomLayout;
    private int colorId;
    private int nameId;
    private Context context;


    public SpinnerRoomAdapter(Activity context, int room_layout,
                              int colorId, int nameId,
                              List<Room> list) {
        this.context = context;
        this.roomLayout = room_layout;
        this.colorId = colorId;
        this.nameId = nameId;
        this.list = list;
        this.flater = context.getLayoutInflater();

    }

    @Override
    public int getCount() {
        if(this.list == null)  {
            return 0;
        }
        return this.list.size();
    }

    @Override
    public Room getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
       Room selectedRoom= this.getItem(position);
        return selectedRoom.getId();
        // return position; (Return position if you need).
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Room room = (Room) getItem(position);


        View rowView = this.flater.inflate(this.roomLayout, null,true);

        ImageView color = (ImageView) rowView.findViewById(this.colorId);
        color.getDrawable().setTint(Color.parseColor(room.getColor()));

        TextView name = (TextView) rowView.findViewById(this.nameId);
        name.setText(room.getName());

        return rowView;
    }
}