package com.testproj.coderepubliq.testprojcoderepubliq.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.testproj.coderepubliq.testprojcoderepubliq.Models.DBHandler;
import com.testproj.coderepubliq.testprojcoderepubliq.Models.Entry;
import com.testproj.coderepubliq.testprojcoderepubliq.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etFirstName) EditText etFirstName;
    @BindView(R.id.etLastName) EditText etlastName;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dbHandler = new DBHandler(this);
    }
    @OnClick(R.id.btnAdd)
    public void OnButtonClickedAdd(View view) {
        if(validateFields()){
            Intent intent = new Intent(this,EntriesActivity.class);
            startActivity(intent);
        }
    }
    public boolean validateFields(){
        if(etFirstName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Firstname is required", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else if(etlastName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Lastname is required", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else {
            Entry entry = new Entry();
            entry.setFirstName(etFirstName.getText().toString());
            entry.setLastName(etlastName.getText().toString());
            dbHandler.addSubject(entry);
            return  true;
        }

    }

}
