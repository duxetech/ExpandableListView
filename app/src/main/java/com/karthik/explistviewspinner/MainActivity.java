package com.karthik.explistviewspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    EditText input;
    ExpandableListView expLV;
    Spinner spinner;
    ExpandableLVAdapter exlvAdapter;
    Button btn_enter;
    String inputItem;
    int roomID, lastGroupPosition = -1;
    List<String> rooms = new ArrayList<>(asList("Select your room","Living Room","Bed Room","Kitchen","Wash Room"));
    ArrayAdapter<String> spAdapter;

    ArrayList<String> LivingRoom= new ArrayList<>();
    ArrayList<String> BedRoom = new ArrayList<>();
    ArrayList<String> Kitchen = new ArrayList<>();
    ArrayList<String> WashRoom = new ArrayList<>();
    HashMap<String,ArrayList<String>> home;
    Set<String> buffer = new LinkedHashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        input = findViewById(R.id.inputBox);
        expLV = findViewById(R.id.expLV);
        btn_enter = findViewById(R.id.btn_enter);

        spAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,rooms);
        spinner.setAdapter(spAdapter);

        home = new HashMap<>();
        buildHome();
        exlvAdapter = new ExpandableLVAdapter(MainActivity.this,rooms,home);
        expLV.setAdapter(exlvAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputItem = input.getText().toString();
                if (roomID>0) {
                    if (!inputItem.isEmpty()) {
                        switch (roomID)
                        {
                            case 1:
                                LivingRoom.add(inputItem);
                                removeDuplicate(LivingRoom);
                                break;
                            case 2:
                                BedRoom.add(inputItem);
                                removeDuplicate(BedRoom);
                                break;
                            case 3:
                                Kitchen.add(inputItem);
                                removeDuplicate(Kitchen);
                                break;
                            case 4:
                                WashRoom.add(inputItem);
                                removeDuplicate(WashRoom);
                                break;
                         }
                         buildHome();
                        exlvAdapter.notifyDataSetChanged();
                        input.setText("");
                    } else
                        Toast.makeText(MainActivity.this, "Enter some items", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Pls select your room first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        expLV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastGroupPosition>-1&&groupPosition!=lastGroupPosition){
                    expLV.collapseGroup(lastGroupPosition);
                }
                lastGroupPosition = groupPosition;
            }
        });
    }
    void buildHome(){
        home.put(rooms.get(1),LivingRoom);
        home.put(rooms.get(2),BedRoom);
        home.put(rooms.get(3),Kitchen);
        home.put(rooms.get(4),WashRoom);
    }
    void removeDuplicate(ArrayList<String>list){
        buffer.addAll(list);
        list.clear();
        list.addAll(buffer);
        buffer.clear();
    }
}
