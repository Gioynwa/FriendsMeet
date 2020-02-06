package com.example.friendsmeet;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class NewCustomAdapter extends ArrayAdapter<Meeting> {

    private Activity context;
    private List<Meeting> meetingList;

    public NewCustomAdapter(Activity context, List<Meeting> meetingList) {
        super(context, R.layout.new_sample_layout, meetingList);
        this.context = context;
        this.meetingList = meetingList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_sample_layout, null, true);

        Meeting meeting = meetingList.get(position);

        TextView t1 = view.findViewById(R.id.nameTextViewId);
        TextView t2 = view.findViewById(R.id.emailTextViewId);
        TextView t3 = view.findViewById(R.id.phoneTextViewId);

        System.out.println(meeting.getName());

        t1.setText(meeting.getName());
        t2.setText(meeting.getMembers());
        t3.setText(meeting.getDest());

        return view;
    }

}
