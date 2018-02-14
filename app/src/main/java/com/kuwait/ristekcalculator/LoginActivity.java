package com.kuwait.ristekcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        final AutoCompleteTextView username = findViewById( R.id.usernameFill );
        final Button signIn = findViewById( R.id.signIn );
        Button signUp = findViewById( R.id.signUp );
        CheckBox show = findViewById( R.id.showPassword );
        final ArrayList<String> usernames = new ArrayList<>(  );
        final EditText password = findViewById( R.id.passwordFill );
        final SharedPreferences data = this.getPreferences( Context.MODE_PRIVATE );
        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, usernames);
        username.setAdapter( adapter );
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
        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences.Editor write = data.edit();
                write.putString( signIn.getText().toString(), password.getText().toString() );
                write.commit();
                username.setText( "" );
                password.setText( "" );
                Toast.makeText(LoginActivity.this, "You have been registered, please login with account" +
                        "you just registered", Toast.LENGTH_LONG).show();
            }
        } );
        signIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                usernames.add( uname );
                String passwordData = data.getString( uname, null );
                if(password == null) {
                    Toast.makeText(LoginActivity.this, "There's no account with username: " + uname + ". Please sign up first", Toast.LENGTH_LONG).show();
                }
                else if(!passwordData.equals( password.getText().toString() )){
                    Toast.makeText(LoginActivity.this, "Password you just entered is wrong", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent i = new Intent( LoginActivity.this, CalculatorActivity.class );
                    startActivity( i );
                    finish();
                }
            }
        } );
    }


}
