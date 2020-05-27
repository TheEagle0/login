package com.elgindy.loginandregisterwithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elgindy.loginandregisterwithroom.Data.UserDao;
import com.elgindy.loginandregisterwithroom.Data.UserDataBase;
import com.elgindy.loginandregisterwithroom.Model.User;
public class MainActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView textViewRegister;
    UserDao db;
    UserDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        textViewRegister = findViewById(R.id.textViewRegister);

        dataBase = Room.databaseBuilder(this, UserDataBase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                User user = db.getUser(email, password);
                if (user != null) {
                    navigateToHome(user);
                } else {
                    Toast.makeText(MainActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToHome(User user) {
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        i.putExtra("User", user);
        startActivity(i);
        finish();
    }


}
