package com.elgindy.loginandregisterwithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.elgindy.loginandregisterwithroom.Model.User;

public class HomeActivity extends AppCompatActivity {
    private TextView tvUser;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User) getIntent().getSerializableExtra("User");

        tvUser = findViewById(R.id.tvUser);

        if (user != null) {
            tvUser.setText("WELCOME " + user.getUserName());
        }
    }

    public void busend(View view) {
        SmsManager sendsms = SmsManager.getDefault();
        EditText  txtbody=(EditText)findViewById(R.id.editText);
        EditText textphone = (EditText)findViewById(R.id.editText2);
        sendsms.sendTextMessage(txtbody.getText().toString(),null,textphone.getText().toString(),null,null);

    }
}
