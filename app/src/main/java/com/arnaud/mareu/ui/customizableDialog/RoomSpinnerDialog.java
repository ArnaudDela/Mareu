package com.arnaud.mareu.ui.customizableDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import com.arnaud.mareu.R;
import com.arnaud.mareu.service.RoomGenerator;
import com.arnaud.mareu.ui.customSpinner.SpinnerRoomAdapter;

public class RoomSpinnerDialog extends DialogFragment {

    public interface RoomSpinnerDialogListener {

        void onSelectedRoom(RoomSpinnerDialog roomSpinnerDialog);
    }


    private RoomSpinnerDialog.RoomSpinnerDialogListener listener;

    public void setListener(RoomSpinnerDialogListener listener) {
        this.listener = listener;
    }

    private Spinner spinner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.room_spinner_dialog, null);


        // liste des salles
        this.spinner = (Spinner) v.findViewById(R.id.spinner_room);
        SpinnerRoomAdapter adapter = new SpinnerRoomAdapter(getActivity(),
                R.layout.room_layout,
                R.id.color,
                R.id.name,
                RoomGenerator.FAKE_DI_ROOMS);
        this.spinner.setAdapter(adapter);

        builder.setView(v);
        builder.setMessage(" Veuillez selectionner une salle");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                listener.onSelectedRoom(RoomSpinnerDialog.this);

            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //  Cancel
                dialog.cancel();
            }

        });

        return builder.create();
    }
}
