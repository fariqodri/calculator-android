package com.kuwait.ristekcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final AutoCompleteTextView username = findViewById(R.id.usernameFill);
        final Button signIn = findViewById(R.id.signIn);
        TextView signUpRequest = findViewById(R.id.signUpRequest);
        final CheckBox show = findViewById(R.id.showPassword);
        final EditText password = findViewById(R.id.passwordFill);
        final SharedPreferences data = this.getPreferences(Context.MODE_PRIVATE);
        Set<String> names = data.getAll().keySet();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names.toArray(new String[names.size()]));
        username.setAdapter(adapter);
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
//
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String passwordData = data.getString(uname, null);
                final SharedPreferences.Editor write = data.edit();
                if (passwordData == null) {
                    Toast.makeText(LoginActivity.this, "There's no account with username: " + uname + ". Please sign up first", Toast.LENGTH_LONG).show();
                } else if (!passwordData.equals(password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Password you just entered is wrong", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(LoginActivity.this, CalculatorActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    signIn.performClick();
                    return true;
                }
                return false;
            }
        });

        signUpRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment fragment = new SignUpFragment();
                fragment.show(getSupportFragmentManager(), "SIGNUP");
            }
        });

    }
}