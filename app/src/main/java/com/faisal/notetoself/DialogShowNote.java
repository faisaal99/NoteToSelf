package com.faisal.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogShowNote extends DialogFragment {

    private Note mShoppingItem;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);

        txtTitle.setText(mShoppingItem.getTitle());
        txtDescription.setText(mShoppingItem.getDescription());

        TextView txtImportant = (TextView) dialogView.findViewById(R.id.textViewImportant);
        TextView txtTodo = (TextView) dialogView.findViewById(R.id.textViewTodo);
        TextView txtIdea = (TextView) dialogView.findViewById(R.id.textViewIdea);

        if (!mShoppingItem.isImportant()) {
            txtImportant.setVisibility(View.GONE);
        }
        if (!mShoppingItem.isTodo()) {
            txtTodo.setVisibility(View.GONE);
        }
        if (!mShoppingItem.isIdea()) {
            txtIdea.setVisibility(View.GONE);
        }

        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage(getResources().getString(R.string.your_note));

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

    public void sendNotesSelected(Note shoppingItemSelected) {
        mShoppingItem = shoppingItemSelected;
    }
}
