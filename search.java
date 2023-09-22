package com.example.pharma_det;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class search extends AppCompatActivity {
SearchView containersearchView;
ListView containerSearchListView;

ArrayList<String> containersearchlist;
ArrayAdapter<String> searchadapter;
    RecyclerView namesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        containersearchView = (SearchView) findViewById(R.id.containersearchView);
        containerSearchListView = (ListView) findViewById(R.id.containerSearchList);

        //adding elements
        containersearchlist = new ArrayList<>();
        containersearchlist.add("ibuprofen");
        containersearchlist.add("aspirin");
        containersearchlist.add("naproxen");
        containersearchlist.add("aceclofenac");
        containersearchlist.add("activated charcoal");
        containersearchlist.add("bisacodyl");
        containersearchlist.add("cetirizine");
        containersearchlist.add("diclofenac");
        containersearchlist.add("glycerol");
        containersearchlist.add("hyoscine");
        containersearchlist.add("zolmitriptan");
        containersearchlist.add("paracetamol");
        containersearchlist.add("vitamin c");
        containersearchlist.add("fabricoal");
        containersearchlist.add("antibiotics");
        containersearchlist.add("gabapentin");
        containersearchlist.add("levothyroxine");
        containersearchlist.add("atorvastatin");
        containersearchlist.add("omeprazole");
        containersearchlist.add("albuterol");
        containersearchlist.add("losartan");

        searchadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, containersearchlist);
        containerSearchListView.setAdapter(searchadapter);

        containersearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (containersearchlist.contains(query)){
                    searchadapter.getFilter().filter(query);
                }
                else {

                    Toast.makeText(search.this, "No match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchadapter.getFilter().filter(newText);
                return false;
            }
       });
    }

}