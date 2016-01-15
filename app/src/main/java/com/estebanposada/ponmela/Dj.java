package com.estebanposada.ponmela;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Dj extends AppCompatActivity {

    //private Firebase mRef;
    private Button ingresar;
    private EditText song;

    private LName[] dataName=
            new LName[]{
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.sol, null, R.drawable.arrow_right, R.drawable.check, R.drawable.cancel)
            };
    ListView lstNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dj);

        ingresar=(Button)findViewById(R.id.b_dj_ingresar);
        song=(EditText)findViewById(R.id.et_dj_song);
/*
        Firebase.setAndroidContext(this);
        mRef= new Firebase("https://boiling-fire-6064.firebaseio.com/");

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Dj.this,"Oprimiste el boton",Toast.LENGTH_SHORT).show();
                Firebase RequestedSongs = mRef.child("RequestedSongs").child(song.getText().toString());
                RequestedSong nueva= new RequestedSong(song.getText().toString());
                RequestedSongs.setValue(nueva, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null){
                            Toast.makeText(Dj.this, "No se envió la canción" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Dj.this, "La canción se envió correctamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/

        String probe[]=getResources().getStringArray(R.array.probe);
        int j;
        for(j=0;j<probe.length;j++)
            dataName[j].setSong(probe[j]);

        Adapter Adap = new Adapter(this, dataName);
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,);
        lstNames = (ListView) findViewById(R.id.Lst);
        lstNames.setAdapter(Adap);
    }

    public class Adapter extends ArrayAdapter {
        public Adapter(Context context, LName[] dataName) {
            super(context, R.layout.playlist, dataName);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflate = LayoutInflater.from(getContext());
            View Item = inflate.inflate(R.layout.playlist, null);

            ImageView Im1 = (ImageView) Item.findViewById(R.id.imagen);
            Im1.setImageResource(dataName[position].getIdsong());

            TextView tx1 = (TextView) Item.findViewById(R.id.tx);
            tx1.setText(dataName[position].getSong());

            ImageView Im2 = (ImageView) Item.findViewById(R.id.image2);
            Im2.setImageResource(dataName[position].getIdarrow());

            ImageView Im3 = (ImageView) Item.findViewById(R.id.image3);
            Im3.setImageResource(dataName[position].getIdcheck());

            ImageView Im4 = (ImageView) Item.findViewById(R.id.image4);
            Im4.setImageResource(dataName[position].getIdcancel());

            return Item;
        }
    }
}