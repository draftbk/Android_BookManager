package com.example.slf.bookmanager.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.slf.bookmanager.Adapter.BookAdapter;
import com.example.slf.bookmanager.Bean.Book;
import com.example.slf.bookmanager.R;

import java.util.ArrayList;
import java.util.List;

public class SortActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTime,btnPrice;

    private List<Book> bookList;
    private ListView listView;
    private BookAdapter adapter;
    private int book_number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        init();
        bookList=new ArrayList<Book>();
        listView= (ListView)findViewById(R.id.listview_book);

    }

    private void init() {
        btnTime= (Button) findViewById(R.id.btn_time);
        btnPrice= (Button) findViewById(R.id.btn_price);
        btnPrice.setOnClickListener(this);
        btnTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_time:
                initList();
                adapter=new BookAdapter(SortActivity.this,R.layout.book_item,bookList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                        Log.d("test", "tag!!!!!!!!!!" + view.getTag().toString());

                    }
                });
                break;

            case R.id.btn_price:

                initListPrice();
                adapter=new BookAdapter(SortActivity.this,R.layout.book_item,bookList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                        Log.d("test", "tag!!!!!!!!!!" + view.getTag().toString());

                    }
                });
                break;
        }
    }

    private void initList() {
        bookList.clear();
        SharedPreferences pref=getSharedPreferences(""+1, MODE_PRIVATE);
        int i=pref.getInt("total_number",0);
        book_number=i;
        for (;i>0;i--){
            SharedPreferences pref_part=getSharedPreferences("" +(book_number-i+1), MODE_PRIVATE);
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

    private void initListPrice() {
        bookList.clear();
        SharedPreferences pref=getSharedPreferences(""+1, MODE_PRIVATE);
        int i=pref.getInt("total_number",0);
        book_number=i;
        for (;i>0;i--){
            SharedPreferences pref_part=getSharedPreferences("" +i, MODE_PRIVATE);
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

        Log.d("test",bookList.size()+"");
        for (int i2=1;i2<book_number;i2++){
            for (int i1=1;i1<book_number;i1++){
                Book book1=bookList.get(i1-1);
                Book book2=bookList.get(i1);
                double price1= Double.parseDouble(book1.getPrice().substring(2,book1.getPrice().length()-1));
                Log.d("test",book1.getPrice().substring(2,book1.getPrice().length()-1));
                double price2= Double.parseDouble(book2.getPrice().substring(2,book2.getPrice().length()-1));
                if (price1<price2){
                    bookList.remove(i1-1);
                    bookList.add(i1-1,book2);
                    bookList.remove(i1);
                    bookList.add(i1,book1);
                }
            }
        }


    }
}
