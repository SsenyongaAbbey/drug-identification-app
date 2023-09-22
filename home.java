package com.example.pharma_det;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class home extends AppCompatActivity {
    CardView cardHome;
    CardView cardindex;
   CardView cardSearch1;
    CardView cardPic;
    CardView cardAlarm;
    CardView cardHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardHome = findViewById(R.id.cardHome);
        cardindex = findViewById(R.id.cardInde);
       cardSearch1 = findViewById(R.id.cardSearch1);
        cardPic = findViewById(R.id.cardPic);
        cardAlarm = findViewById(R.id.cardAlarm);
        cardHelp = findViewById(R.id.cardHelp);

        cardSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, search.class);
                        startActivity(intent);
            }
        });

        cardAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1 = new Intent(home.this,setAlarm.class);
                    startActivity(intent1);
            }
        });
        cardindex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(home.this, drugIndex.class);
                startActivity(intent2);
            }
        });

        cardPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(home.this, takepic.class);
                startActivity(intent3);
            }
        });

        cardHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(home.this, help.class);
                startActivity(intent5);
            }
        });
    }
}
