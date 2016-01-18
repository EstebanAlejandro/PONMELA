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


/**
 * A simple {@link Fragment} subclass.
 */
public class playlist extends Fragment {

    private Firebase mRef;
    private Firebase mRef2;
    int p;
    private LName[] dataName = new LName[]{
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
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
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
                Adapter Adap = new Adapter(getActivity(), dataName);
                //lstNames=(ListView) view.findViewById(R.id.Lstpl);
                lstNames.setAdapter(Adap);
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
        public Adapter(Context context, LName[] dataName) {
            super(context, R.layout.playlist, dataName);
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            LayoutInflater inflate = LayoutInflater.from(getContext());
            View Item = inflate.inflate(R.layout.playlist, null);

            ImageView Im1 = (ImageView) Item.findViewById(R.id.imagen);
            Im1.setImageResource(dataName[position].getIdsong());

            TextView tx1 = (TextView) Item.findViewById(R.id.tx);
            tx1.setText(dataName[position].getSong());

            //ImageView Im2 = (ImageView) Item.findViewById(R.id.image2);
            //Im2.setImageResource(dataName[position].getIdarrow());

            ImageView Im3 = (ImageView) Item.findViewById(R.id.image3);
            Im3.setImageResource(dataName[position].getIdcheck());
            Im3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toAcept(position);
                }
            });

            ImageView Im4 = (ImageView) Item.findViewById(R.id.image4);
            Im4.setImageResource(dataName[position].getIdcancel());
            Im4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(), dataName[position].getSong(), Toast.LENGTH_SHORT).show();
                    toReject(position);

                }
            });

            return Item;
        }
    }

    public void toAcept (int pos){
        Firebase.setAndroidContext(getContext());
        mRef = new Firebase("https://boiling-fire-6064.firebaseio.com/");
        mRef2= new Firebase("https://boiling-fire-6064.firebaseio.com/");
        Firebase requestedSongs = mRef.child("AceptedSongs").child(dataName[pos].getSong());
        /*
        Eliminar Cancion aceptada de la lista de pedidas
         */
        final Firebase eliminarcancion=mRef2.child("RequestedSongs");
        eliminarcancion.child(dataName[pos].getSong()).removeValue();
        /*
        fin
         */
        AceptedSongs nueva = new AceptedSongs(dataName[pos].getSong(),"Probe");
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
        Firebase removeSongs = mRef.child("RequestedSongs").child(dataName[pos].getSong());
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
        Firebase requestedSongs = mRef.child("RejectedSongs").child(dataName[pos].getSong());
        RejectedSongs nueva = new RejectedSongs(dataName[pos].getSong(),"Probe");
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
        Firebase removeSongs = mRef.child("RequestedSongs").child(dataName[pos].getSong());
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
