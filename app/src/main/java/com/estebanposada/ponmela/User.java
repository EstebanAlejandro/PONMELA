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

import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity {



    private Firebase mRef;
    private Button ingresar;
    private EditText song;
    public String Stuser[]={}, StSong[]={};

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
                    Toast.makeText(User.this, username, Toast.LENGTH_SHORT).show();
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
                String[] Users = new String[contador];
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RequestedSongsDJ RSDJ = postSnapshot.getValue(RequestedSongsDJ.class);
                    Songs[i] = RSDJ.getNombre() + "-" + RSDJ.getUsuario();
                    Users[i] = RSDJ.getUsuario();
                    i++;
                }
                Stuser=Users;
                StSong=Songs;
                ArrayList<LName> items = new ArrayList<LName>();
                int j;
                for (j = 0; j < Songs.length; j++) {
                    items.add(new LName(R.drawable.part, Songs[j], R.drawable.check, R.drawable.cancel));
                }
                lstNames.setAdapter(new Adapter(getApplication(), items));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Lectura Fallida: " + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class Adapter extends ArrayAdapter {
        public Adapter(Context context, List objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.request, null);
            }
            ImageView im1 = (ImageView) convertView.findViewById(R.id.imagen);
            TextView tx1 = (TextView) convertView.findViewById(R.id.tx);

            LName item = (LName) getItem(position);
            im1.setImageResource(item.getIdsong());
            tx1.setText(item.getSong());


            return convertView;
        }
    }

}
