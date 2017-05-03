package com.testproj.coderepubliq.testprojcoderepubliq.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.testproj.coderepubliq.testprojcoderepubliq.Adapters.EntryListAdapter;
import com.testproj.coderepubliq.testprojcoderepubliq.Models.DBHandler;
import com.testproj.coderepubliq.testprojcoderepubliq.Models.Entry;
import com.testproj.coderepubliq.testprojcoderepubliq.R;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.list_entry)
    ListView list_entry;
    List<Entry> entries;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        ButterKnife.bind(this);
        dbHandler = new DBHandler(this);
        entries = new ArrayList<>();
        entries = dbHandler.getEntries();
        list_entry.setItemsCanFocus(false);
        list_entry.setAdapter(new EntryListAdapter(entries,this));
        list_entry.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra("id", entries.get(i).getId());
        intent.putExtra("firstName", entries.get(i).getFirstName());
        intent.putExtra("lastName", entries.get(i).getLastName());
        startActivity(intent);
        Log.e("ITEM NO.",i+"");
    }
}
