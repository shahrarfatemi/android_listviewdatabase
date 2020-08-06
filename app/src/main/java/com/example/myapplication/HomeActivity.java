package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.list);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = databaseHelper.displayAll();
        final ArrayList<String> arrayList = new ArrayList<>();

        if (cursor.getCount() == 0) {
            Toast.makeText(HomeActivity.this,"Nothing to show",Toast.LENGTH_SHORT).show();
        } else {
            String results = "";



            while (cursor.moveToNext()) {
                results += "Id : " + cursor.getString(0) + " ";
                results += "Name : " + cursor.getString(1) + " ";
                results += "Dept : " + cursor.getString(2) + " ";
                results += "Age : " + cursor.getString(3) + " ";
                arrayList.add(results);
                results = "";
            }



            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HomeActivity.this,R.layout.sample,R.id.text,arrayList);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String value = arrayList.get(position);
                    Toast.makeText(HomeActivity.this,value,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
