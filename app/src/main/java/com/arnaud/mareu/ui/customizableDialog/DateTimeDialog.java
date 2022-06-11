package com.arnaud.mareu.ui.customizableDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.Toast;

import com.arnaud.mareu.R;

import java.util.Date;

public class DateTimeDialog extends DialogFragment {

    public interface DateTimeDialogListener {

        void onSelectedDateTime(DateTimeDialog dateTimeDialog);
    }


    private DateTimeDialogListener listener;

    public void setListener(DateTimeDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.picker_datetime_dialog, null));

        builder.setMessage(" Veuillez selectionner la date et l'heure");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                         listener.onSelectedDateTime(DateTimeDialog.this);

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
