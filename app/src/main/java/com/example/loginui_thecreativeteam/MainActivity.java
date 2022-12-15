package com.example.loginui_thecreativeteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    EditText edEmail, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);

        if(ParseUser.getCurrentUser()!=null){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login (View view){
        //Validamos si el input del email esta vacio
        if (TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError("Email requerido");
        }else if (TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError("Password requerido");
        }else {
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading ...");
            progress.show();
            ParseUser.logInInBackground(edEmail.getText().toString(), edPassword.getText().toString(), (parseUser, e) -> {
                if (parseUser != null) {
                    Toast.makeText(MainActivity.this, "Welcome back",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progress.dismiss();
                    ParseUser.logOut();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void signup(View view) {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(MainActivity.this, ResetPassword.class);
        startActivity(intent);
    }
}