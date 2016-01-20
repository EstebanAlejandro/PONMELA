package com.estebanposada.ponmela;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class playlist extends Fragment {

    private Firebase mRef;
    private Firebase mRef2;
    public String Stuser[]={}, StSong[]={};
    ListView lstNames;

    public playlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        /*
        Firebase
         */
        Firebase.setAndroidContext(getContext());
        mRef = new Firebase("https://boiling-fire-6064.firebaseio.com/");
        lstNames=(ListView) view.findViewById(R.id.Lstpl);
        Firebase RequestedSongs=mRef.child("RequestedSongs");
        RequestedSongs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int contador = (int) snapshot.getChildrenCount();
                int i = 0;
                String[] Songs = new String[contador];
                String[] Users = new String[contador];
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    RequestedSongsDJ RSDJ = postSnapshot.getValue(RequestedSongsDJ.class);
                    Songs[i] = RSDJ.getNombre();// + "-" + RSDJ.getUsuario();
                    Users[i] = RSDJ.getUsuario();
                    i++;
                }
                Stuser = Users;
                StSong = Songs;

                ArrayList<LName> items = new ArrayList<LName>();
                int j;
                //if (Songs.length != 0) {
                    for (j = 0; j < Songs.length; j++) {
                        items.add(new LName(R.drawable.part, Songs[j], R.drawable.check, R.drawable.cancel));
                    }
                    lstNames.setAdapter(new Adapter(getContext(), items));
                //}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getActivity(), "Lectura Fallida: " + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        /*
        Final Firebase
         */
        return view;


    }

    public class Adapter extends ArrayAdapter {
        public Adapter(Context context, List objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.playlist, null);
            }
            ImageView im1 = (ImageView) convertView.findViewById(R.id.imagen);
            TextView tx1 = (TextView) convertView.findViewById(R.id.tx);
            ImageView im3 = (ImageView) convertView.findViewById(R.id.image3);
            ImageView im4 = (ImageView) convertView.findViewById(R.id.image4);

            LName item = (LName) getItem(position);
            im1.setImageResource(item.getIdsong());
            im3.setImageResource(item.getIdcheck());
            im4.setImageResource(item.getIdcancel());
            tx1.setText(item.getSong());

            im3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toAcept(position);
                }
            });
            im4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toReject(position);
                }
            });

            return convertView;
        }
    }

    public void toAcept (int pos){
        Firebase.setAndroidContext(getContext());
        mRef = new Firebase("https://boiling-fire-6064.firebaseio.com/");
        mRef2= new Firebase("https://boiling-fire-6064.firebaseio.com/");
        Firebase requestedSongs = mRef.child("AceptedSongs").child(StSong[pos]);
        /*
        Eliminar Cancion aceptada de la lista de pedidas
         */
        final Firebase eliminarcancion=mRef2.child("RequestedSongs");
        eliminarcancion.child(StSong[pos]).removeValue();
        /*
        fin
         */
        AceptedSongs nueva = new AceptedSongs(StSong[pos],Stuser[pos]);
        requestedSongs.setValue(nueva, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null){
                    Toast.makeText(getActivity(), "No se pudo enviar la canción", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Se envio la cancion correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //RequestedSong nue = new RequestedSong(dataName[pos].getSong(),"Probe");
        Firebase removeSongs = mRef.child("RequestedSongs").child(StSong[pos]);
        removeSongs.removeValue(new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null){
                    Toast.makeText(getActivity(), "No se pudo eliminar la canción", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Se eliminó la canción correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //requestedSongs

    }
    public void toReject (int pos){
        Firebase.setAndroidContext(getContext());
        mRef = new Firebase("https://boiling-fire-6064.firebaseio.com/");
        Firebase requestedSongs = mRef.child("RejectedSongs").child(StSong[pos]);
        RejectedSongs nueva = new RejectedSongs(StSong[pos],Stuser[pos]);
        requestedSongs.setValue(nueva, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null){
                    Toast.makeText(getActivity(), "No se pudo enviar la canción", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Se envio la cancion correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Firebase removeSongs = mRef.child("RequestedSongs").child(StSong[pos]);
        removeSongs.removeValue(new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    Toast.makeText(getActivity(), "No se pudo eliminar la canción", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Se eliminó la canción correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
