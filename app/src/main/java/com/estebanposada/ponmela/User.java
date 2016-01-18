package com.estebanposada.ponmela;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class User extends AppCompatActivity {



    private Firebase mRef;
    private Button ingresar;
    private EditText song;

    private LName[] dataName=
            new LName[]{
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel),
                    new LName(R.drawable.part, null, R.drawable.check, R.drawable.cancel)
            };
    ListView lstNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ingresar=(Button)findViewById(R.id.b_ingresar);
        song= (EditText)findViewById(R.id.et_song);

        /*
        ASDF FIREBASE
         */

        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://boiling-fire-6064.firebaseio.com/");

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase requestedSongs = mRef.child("RequestedSongs").child(song.getText().toString());

                if (song.getText().toString().equals("")){
                    Toast.makeText(User.this, "Ingrese canción", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences prefs= getApplication().getSharedPreferences("PONMELA",0);
                    String username=prefs.getString("username",null);
                    RequestedSong nueva = new RequestedSong(song.getText().toString(),username);

                    requestedSongs.setValue(nueva, new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            if (firebaseError != null) {
                                Toast.makeText(User.this, "No se pudo enviar la canción" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(User.this, "Se envio la cancion correctamente", Toast.LENGTH_SHORT).show();
                                song.setText("");
                            }
                        }
                    });
                }
            }
        });
        lstNames=(ListView) findViewById(R.id.Lst2);
        Firebase RequestedSongs=mRef.child("RequestedSongs");
        RequestedSongs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int contador = (int) dataSnapshot.getChildrenCount();
                int i = 0;
                String[] Songs = new String[contador];
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RequestedSongsDJ RSDJ = postSnapshot.getValue(RequestedSongsDJ.class);
                    Songs[i] = RSDJ.getNombre();// + "-" + RSDJ.getUsuario();
                    i++;
                }
                int j;
                for (j = 0; j < Songs.length; j++) {

                    /*
                    dataName[j].setIdcancel(R.drawable.cancel);
                    dataName[j].setIdsong(R.drawable.sol);
                    dataName[j].setIdcheck(R.drawable.check);*/

                    dataName[j].setSong(Songs[j]);
                }
                for(j=Songs.length;j<dataName.length;j++){
                    dataName[j].setIdcancel(0);
                    dataName[j].setIdsong(0);
                    dataName[j].setIdcheck(0);
                    dataName[j].setSong(null);
                    //getView().setVisibility(View.GONE);
                }
                Adapter Adap2 = new Adapter(getApplicationContext(), dataName);
                lstNames.setAdapter(Adap2);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Lectura Fallida: " + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        /*String probe[]=getResources().getStringArray(R.array.probe);

      /* SharedPreferences prefs= getApplication().getSharedPreferences("PONMELA",0);
        String username=prefs.getString("username",null);
        int j;
        for(j=0;j<probe.length;j++)
            dataName[j].setSong(probe[j]);

        Adapter Adap2 = new Adapter(this, dataName);
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,);
        lstNames = (ListView) findViewById(R.id.Lst2);
        lstNames.setAdapter(Adap2);*/
    }

    public class Adapter extends ArrayAdapter {
        public Adapter(Context context, LName[] dataName) {
            super(context, R.layout.request, dataName);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflate = LayoutInflater.from(getContext());
            View Item = inflate.inflate(R.layout.request, null);

            ImageView Im1 = (ImageView) Item.findViewById(R.id.imagen);
            Im1.setImageResource(dataName[position].getIdsong());

            TextView tx1 = (TextView) Item.findViewById(R.id.tx);
            tx1.setText(dataName[position].getSong());

           /* ImageView Im2 = (ImageView) Item.findViewById(R.id.image2);
            Im2.setImageResource(dataName[position].getIdarrow());

            ImageView Im3 = (ImageView) Item.findViewById(R.id.image3);
            Im3.setImageResource(dataName[position].getIdcheck());

            ImageView Im4 = (ImageView) Item.findViewById(R.id.image4);
            Im4.setImageResource(dataName[position].getIdcancel());*/

            return Item;
        }
    }

}
