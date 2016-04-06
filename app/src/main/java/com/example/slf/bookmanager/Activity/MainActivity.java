package com.example.slf.bookmanager.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.slf.bookmanager.Adapter.BookAdapter;
import com.example.slf.bookmanager.Bean.Book;
import com.example.slf.bookmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Book> bookList;
    private ListView listView;
    private BookAdapter adapter;
    private int book_number=0;
    private String touchedTag="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
        bookList=new ArrayList<Book>();
        listView= (ListView)findViewById(R.id.listview_book);
        initList();
        adapter=new BookAdapter(MainActivity.this,R.layout.book_item,bookList);
        Log.d("test", "1");
        listView.setAdapter(adapter);
        Log.d("test", "2");
        //Log.d("test", "exapmple" + example);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                Log.d("test", "tag!!!!!!!!!!"+view.getTag().toString());
                touchedTag=view.getTag().toString();
                showThisDialog();

            }
        });
    }

    private void initList() {

        SharedPreferences pref=getSharedPreferences(""+1, MODE_PRIVATE);
        int i=pref.getInt("total_number",0);
        book_number=i;
        for (;i>0;i--){
            SharedPreferences pref_part=getSharedPreferences(""+i, MODE_PRIVATE);
            String name=pref_part.getString("name", "");
            String time=pref_part.getString("time", "");
            String price=pref_part.getString("price", "");
            int tag=pref_part.getInt("tag", 0);
            String concise=pref_part.getString("concise", "");
            if (concise.length()>=34){
                concise=concise.substring(0,34)+"......";
            }
            String type=pref_part.getString("type","");
            Book book=new Book(name,time,"¥ "+price,"类别："+type,concise,tag);
            bookList.add(book);

        }

    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search_book) {
            Intent intent =new Intent(MainActivity.this,SearchActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.sort_book) {
            Log.d("test","");
            Intent intent =new Intent(MainActivity.this,SortActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                showMyDialog();
                break;
            default:
                break;
        }
    }

    private void showMyDialog() {
        Log.d("test","这里");
        //弹出框
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);

        final EditText editName = (EditText)view.findViewById(R.id.edit_name);
        final EditText editPrice = (EditText)view.findViewById(R.id.edit_price);
        final EditText editConcise = (EditText)view.findViewById(R.id.edit_concise);
        final EditText editType = (EditText)view.findViewById(R.id.edit_type);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date=sdf.format(new java.util.Date());
                SharedPreferences.Editor editor=getSharedPreferences(++book_number+"", Context.MODE_PRIVATE).edit();
                editor.putString("name",editName.getText().toString());
                editor.putString("time",date);
                editor.putString("price", editPrice.getText().toString());
                editor.putString("concise", editConcise.getText().toString());
                editor.putString("type", editType.getText().toString());
                editor.putInt("tag", book_number);
                editor.commit();

                SharedPreferences.Editor editor_first=getSharedPreferences(1+"", Context.MODE_PRIVATE).edit();
                editor_first.putInt("total_number", book_number);
                editor_first.commit();
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.show();
    }

    private void showThisDialog() {
        Log.d("test","这里");
        //弹出框
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);
        TextView title= (TextView) view.findViewById(R.id.text_title);
        title.setText("书籍详情");
        final EditText editName = (EditText)view.findViewById(R.id.edit_name);
        final EditText editPrice = (EditText)view.findViewById(R.id.edit_price);
        final EditText editConcise = (EditText)view.findViewById(R.id.edit_concise);
        final EditText editType = (EditText)view.findViewById(R.id.edit_type);

        SharedPreferences pref_part=getSharedPreferences(touchedTag, MODE_PRIVATE);
        String name=pref_part.getString("name", "");
        String price=pref_part.getString("price", "");
        String concise=pref_part.getString("concise", "");
        String type=pref_part.getString("type","");
        editName.setText(name);
        editPrice.setText(price);
        editConcise.setText(concise);
        editType.setText(type);
        builder.setPositiveButton("修改", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                SharedPreferences.Editor editor=getSharedPreferences(touchedTag, Context.MODE_PRIVATE).edit();
                editor.putString("name",editName.getText().toString());
                editor.putString("price", editPrice.getText().toString());
                editor.putString("concise", editConcise.getText().toString());
                editor.putString("type", editType.getText().toString());
                editor.commit();
                //重新加载list
                initList();
                adapter=new BookAdapter(MainActivity.this,R.layout.book_item,bookList);
                Log.d("test", "1");
                listView.setAdapter(adapter);
                Log.d("test", "2");
                //Log.d("test", "exapmple" + example);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                        Log.d("test", "tag!!!!!!!!!!" + view.getTag().toString());
                        touchedTag = view.getTag().toString();
                        showThisDialog();

                    }
                });
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.show();
    }

}
