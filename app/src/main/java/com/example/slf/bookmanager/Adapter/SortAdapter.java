package com.example.slf.bookmanager.Adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.slf.bookmanager.R;
import com.example.slf.bookmanager.Bean.Sort;

import java.util.List;

/**
 * Created by draft on 2015/7/28.
 */
public class SortAdapter extends ArrayAdapter<Sort> {

    private int resourceId;


    public SortAdapter(Context context, int textViewResourceId, List<Sort> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;

    }

    @Override
    public View getView( final int position,View convertView,ViewGroup parent){
        Sort sort=getItem(position);
        Log.d("test", "repairRecord" + sort);
        View view;
        ViewHolder viewHolder;
        Log.d("test", "convertView" + convertView);
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        viewHolder=new ViewHolder();
        viewHolder.name=(TextView)view.findViewById(R.id.name);
        view.setTag(viewHolder);

        viewHolder.name.setText(sort.getName());

        return view;
    }


    class ViewHolder{
        TextView name;
    }

}