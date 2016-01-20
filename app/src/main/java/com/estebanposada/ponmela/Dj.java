package com.estebanposada.ponmela;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Dj extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener{

    private ViewPager myv;
private Firebase mRef;
    //private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dj);
        Firebase.setAndroidContext(this);
        mRef= new Firebase("https://boiling-fire-6064.firebaseio.com/");


        PagerAdapter Padap = new PagerAdapter(getSupportFragmentManager());
        myv = (ViewPager) findViewById(R.id.pagerdj);
        myv.setAdapter(Padap);

        myv.setOnPageChangeListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setText("Playlist").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Acepted").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Rejected").setTabListener(this);
        actionBar.addTab(tab);
/*
        ingresar=(Button)findViewById(R.id.b_dj_ingresar);
        song=(EditText)findViewById(R.id.et_dj_song);
        */

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        myv.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
/*
MENU
 */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dj,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_borrar_lista_aceptadas){

            Firebase Delete_Acepted=mRef.child("AceptedSongs");
            Delete_Acepted.removeValue(new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if(firebaseError != null){
                        Toast.makeText(Dj.this, "Lista NO borrada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Dj.this, "Lista Borrada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(id==R.id.action_borrar_lista_rechazadas){

            Firebase Delete_Rejected=mRef.child("RejectedSongs");
            Delete_Rejected.removeValue(new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if(firebaseError != null){
                        Toast.makeText(Dj.this, "Lista NO borrada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Dj.this, "Lista Borrada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(id==R.id.action_borrar_lista_todas){

            Firebase Delete_Acepted=mRef.child("AceptedSongs");
            Firebase Delete_Rejected=mRef.child("RejectedSongs");
            Firebase Delete_Requeted=mRef.child("RequestedSongs");
            Delete_Acepted.removeValue(new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if(firebaseError != null){
                        Toast.makeText(Dj.this, "Lista de aceptadas NO borrada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Dj.this, "Lista de aceptadas borrada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Delete_Rejected.removeValue(new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if(firebaseError != null){
                        Toast.makeText(Dj.this, "Lista de rechazadas NO borrada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Dj.this, "Lista de rechazadas borrada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Delete_Requeted.removeValue(new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if(firebaseError != null){
                        Toast.makeText(Dj.this, "Lista de pedidas NO borrada", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Dj.this, "Lista de pedidas borrada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new playlist();
                case 1:
                    return new AceptSong();
                case 2:
                    return new RejectSong();
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
