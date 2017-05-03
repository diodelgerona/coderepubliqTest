package com.testproj.coderepubliq.testprojcoderepubliq.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.testproj.coderepubliq.testprojcoderepubliq.Models.DBHandler;
import com.testproj.coderepubliq.testprojcoderepubliq.Models.Entry;
import com.testproj.coderepubliq.testprojcoderepubliq.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity {
    @BindView(R.id.etEditFirstName)
    EditText etEditFirstName;
    int Id = 0;
    @BindView(R.id.etEditLastName) EditText etEditLastName;

    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        dbHandler = new DBHandler(this);
        Intent getData =getIntent();
        Id = getData.getIntExtra("id",0);
        etEditFirstName.setText(getData.getStringExtra("firstName"));
        etEditLastName.setText(getData.getStringExtra("lastName"));
    }
    @OnClick(R.id.btnSave)
    public void OnButtonClickedSave(View view) {
        update();
        Intent intent = new Intent(this,EntriesActivity.class);
        startActivity(intent);

    }
    public void update(){
        Entry entry = new Entry();
        entry.setFirstName(etEditFirstName.getText().toString());
        entry.setLastName(etEditLastName.getText().toString());
        dbHandler.updateEntry(Id,entry);
    }
}
