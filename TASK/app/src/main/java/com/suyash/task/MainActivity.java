package com.suyash.task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.Inflater;

import ADAPTER.recyclerviewAdapter;
import Database.database;
import Model.Item;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Item> itemlist;
    AlertDialog.Builder builder;
    AlertDialog alert;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(MainActivity.this);
                final EditText newitem ;
                final EditText newdescription;
                final Button newsubmit;

                View newitemview = getLayoutInflater().inflate(R.layout.new_row,null);
                builder.setView(newitemview);
                alert = builder.create();
                alert.show();
                newitem = newitemview.findViewById(R.id.newtask);
                newdescription = newitemview.findViewById(R.id.newDescription);
                newsubmit = newitemview.findViewById(R.id.newsubmit);

                newsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(!newitem.getText().toString().isEmpty())
                        {
                            Item item = new Item(newitem.getText().toString(),newdescription.getText().toString());
                            itemlist.add(item);
                            alert.dismiss();
                            db = new database(MainActivity.this);
                            db.add(item);

                        }
                    }
                });


            }
        });

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemlist = new ArrayList<Item>();
        db = new database(MainActivity.this);
        itemlist = db.getlist();

        //itemlist.add(new Item("FUCK","FUCK IT HARD"));
        //itemlist.add(new Item("FUCK","FUCK IT HARD"));


        adapter = new recyclerviewAdapter(this,itemlist);
        recyclerView.setAdapter(adapter);
        //Toast.makeText(this,"WELCOME",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            database db = new database(this);
            db.deleteall();
           // itemlist.clear();
            adapter.notifyItemRangeRemoved(0,itemlist.size());
            itemlist.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
