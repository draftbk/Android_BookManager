package com.example.slf.bookmanager.Adapter;

/**
 * Created by slf on 16/4/5.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import com.example.slf.bookmanager.Bean.Book;
import com.example.slf.bookmanager.R;


/**
 * Created by slf on 16/4/1.
 */
public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private int resource_id;
    public BookAdapter(Context context, int resource,List<Book> objects) {
        super(context, resource,objects);
        this.context=context;
        this.resource_id=resource;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        Book book=getItem(position);
        View view;
        ViewHolder viewHolder=new ViewHolder();
        view= LayoutInflater.from(getContext()).inflate(resource_id,null);
        view.setTag(book.getTag());
        viewHolder.description= (TextView) view.findViewById(R.id.text_description);
        viewHolder.book=(ImageView)view.findViewById(R.id.image_book);
        viewHolder.time= (TextView) view.findViewById(R.id.text_date);
        viewHolder.name= (TextView) view.findViewById(R.id.text_name);
        viewHolder.price= (TextView) view.findViewById(R.id.text_price);
        viewHolder.sort= (TextView) view.findViewById(R.id.text_sort);
        viewHolder.time.setText(book.getTime().substring(0,10)+"\n"+book.getTime().substring(11,19));
        viewHolder.name.setText(book.getName());
        viewHolder.price.setText(book.getPrice());
        viewHolder.description.setText(book.getConcise());
        viewHolder.sort.setText(book.getType());

        return view;
    }

    class ViewHolder{
        ImageView book;
        TextView name,price,description,sort,time;
    }

}

