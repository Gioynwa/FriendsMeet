package com.example.friendsmeet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<User> {

    private Activity context;
    private List<User> contactList;

    public CustomAdapter(Activity context, List<User> contactList) {
        super(context, R.layout.sample_layout, contactList);
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);

        User user = contactList.get(position);

        TextView t1 = view.findViewById(R.id.nameTextViewId);
        TextView t2 = view.findViewById(R.id.emailTextViewId);
        TextView t3 = view.findViewById(R.id.phoneTextViewId);

        System.out.println(user.getUsername());

        t1.setText(user.getUsername());
        t2.setText(user.getEmail());
        t3.setText(user.getPhone());


        return view;
    }
}
