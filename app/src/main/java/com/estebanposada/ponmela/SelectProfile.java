package com.estebanposada.ponmela;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectProfile extends AppCompatActivity  {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.action_logout){
            /*
            prefencias compartidas
            para guardar el nombre de usuario.
             */
            SharedPreferences prefs= getApplication().getSharedPreferences("PONMELA",0);
            prefs.edit().putInt("flag_logout",1).commit();
            //Toast.makeText(SelectProfile.this, "flag=1", Toast.LENGTH_SHORT).show();
            finish();

            /*
            fin preferencias compartidas
             */
        }

        return super.onOptionsItemSelected(item);
    }
}
