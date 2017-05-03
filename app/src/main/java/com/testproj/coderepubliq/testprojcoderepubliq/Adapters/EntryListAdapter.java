package com.testproj.coderepubliq.testprojcoderepubliq.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.testproj.coderepubliq.testprojcoderepubliq.Activities.EntriesActivity;
import com.testproj.coderepubliq.testprojcoderepubliq.Models.DBHandler;
import com.testproj.coderepubliq.testprojcoderepubliq.Models.Entry;
import com.testproj.coderepubliq.testprojcoderepubliq.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by diodelgerona on 03/05/2017.
 */

public class EntryListAdapter extends BaseAdapter {
    DBHandler dbHandler;
    private List<Entry> list;
    private Context context;

    public EntryListAdapter(List<Entry> list, Context context){
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }
        else{
            convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.row_entry,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        final Entry entry = (Entry)getItem(position);
        viewHolder.tvFirstName.setText(entry.getFirstName().toString());
        viewHolder.tvLastName.setText(entry.getLastName().toString());
        viewHolder.tvDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position);
                dbHandler = new DBHandler(context);
                dbHandler.deleteEntry(entry.getId());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public static class ViewHolder{
        @BindView(R.id.tvFirstName) TextView tvFirstName;
        @BindView(R.id.tvLastName) TextView tvLastName;
        @BindView(R.id.tvDelete) TextView tvDelete;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

    }
}

