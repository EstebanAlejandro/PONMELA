package com.estebanposada.ponmela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectProfile extends AppCompatActivity {

    Button sel1,sel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile);

        sel1 = (Button) findViewById(R.id.sel1);
        sel2 = (Button) findViewById(R.id.sel2);

        sel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dj.class));
            }
        });
        sel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), User.class));
            }
        });
    }
}
