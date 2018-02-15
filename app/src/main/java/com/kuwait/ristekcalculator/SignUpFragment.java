package com.kuwait.ristekcalculator;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Fari on 2/14/2018.
 */

public class SignUpFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Referensi: https://developer.android.com/guide/topics/ui/dialogs.html?hl=id
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.activity_sign_up, null);
        final CheckBox show = view.findViewById( R.id.showPassword );
//      final ArrayList<String> usernames = new ArrayList<>(  );
        final EditText password = view.findViewById( R.id.passwordFill );
        final SharedPreferences data = getActivity().getPreferences( Context.MODE_PRIVATE );
        final AutoCompleteTextView username = view.findViewById( R.id.usernameFill );

        show.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    password.setTransformationMethod( PasswordTransformationMethod.getInstance() );
                }
                else {
                    password.setTransformationMethod( HideReturnsTransformationMethod.getInstance() );
                }
            }
        } );

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.registerButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        final SharedPreferences.Editor write = data.edit();
                        String signedUpName = username.getText().toString();
                        String signedUpPassword = password.getText().toString();
                        if(signedUpName.equals("") || signedUpPassword.equals("")) {
                            Toast.makeText(getActivity(), "Your username or password can't be empty", Toast.LENGTH_LONG).show();
                        }
                        else if(data.getAll().containsKey(signedUpName)) {
                            Toast.makeText(getActivity(), "Sorry, username: " + signedUpName + " has been taken", Toast.LENGTH_LONG).show();
                        }
                        else{
                            write.putString( signedUpName, signedUpPassword);
                            write.apply();
                            username.setText( "" );
                            password.setText( "" );
                            show.setChecked(false);
                            Toast.makeText(getActivity(), "You have been registered, please login with account" +
                                    " you just registered", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SignUpFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
