package com.example.a.testfinal.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.testfinal.R;
import com.example.a.testfinal.model.Member;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter {
    Context context;
    ArrayList<Member> arrayList;

    public MemberAdapter(Context context, ArrayList<Member> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();

        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.custom_cell, null);
            viewHolder.txt_name = view.findViewById(R.id.text_name);
            viewHolder.txt_roles = view.findViewById(R.id.text_roles);
            viewHolder.img = view.findViewById(R.id.image_view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String roles="";
        viewHolder.txt_name.setText(arrayList.get(i).getUsername());
        if(arrayList.get(i).getAcctype()==0){
            roles="admin";
        }else{
            roles="member";
        }
        viewHolder.txt_roles.setText(roles);
        int id_image = context.getResources().getIdentifier(context.getPackageName()+":drawable/" + arrayList.get(i).getImage(),
                null, null);
        viewHolder.img.setImageResource(id_image);
        return view;
    }
    class ViewHolder {
        TextView txt_name;
        TextView txt_roles;
        ImageView img;

    }
}


