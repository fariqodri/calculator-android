package com.kuwait.ristekcalculator;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences data;
    AutoCompleteTextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernameFill);
        final Button signIn = findViewById(R.id.signIn);
        TextView signUpRequest = findViewById(R.id.signUpRequest);
        final CheckBox show = findViewById(R.id.showPassword);
        final EditText password = findViewById(R.id.passwordFill);
        data = this.getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor write = data.edit();
        new Asynchronous().execute(  );
        Log.d("LIST ON CREATE", data.getStringSet("input\'10001", null).toString());
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

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String passwordData = data.getString(uname, null);
                Set<String> namesIn = data.getStringSet( "input\'10001", null );
                if(namesIn == null) {
                    namesIn = new HashSet<>(  );
                }
                namesIn.add( uname );
                write.putStringSet( "input\'10001", namesIn );
                write.apply();
                new Asynchronous().execute(  );
                Log.d("LIST ON CLICK", data.getStringSet("input\'10001", null).toString());
                if (passwordData == null) {
                    Toast.makeText(LoginActivity.this, "There's no account with username: " + uname + ". Please sign up first", Toast.LENGTH_LONG).show();
                } else if (!passwordData.equals(password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Password you just entered is wrong", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(LoginActivity.this, CalculatorActivity.class);
                    startActivity(i);
                    username.setText("");
                    password.setText("");
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

    class Asynchronous extends AsyncTask<Void, Void, ArrayAdapter<String>> {
        @Override
        protected ArrayAdapter<String> doInBackground(Void... pref) {
            Set<String> names = data.getStringSet( "input\'10001", null );
            if(names == null) names = new HashSet<>(  );
            ArrayAdapter<String> adapter = new ArrayAdapter<>( LoginActivity.this, android.R.layout.simple_list_item_1, names.toArray( new String[names.size()] ) );
            return adapter;
        }

        @Override
        protected void onPostExecute(ArrayAdapter<String> aVoid) {
            super.onPostExecute( aVoid );
            username.setAdapter( aVoid );
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences data = this.getPreferences(Context.MODE_PRIVATE);
        Log.d("LIST ON DESTTOY", data.getStringSet("input\'10001", null).toString());
    }
}
