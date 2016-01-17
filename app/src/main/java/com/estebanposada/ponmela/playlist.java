package com.estebanposada.ponmela;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
        //Query query = RequestedSongs.orderByKey();
        RequestedSongs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int contador = (int) snapshot.getChildrenCount();
                int i = 0;
                String[] Songs = new String[contador];
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    RequestedSongsDJ RSDJ = postSnapshot.getValue(RequestedSongsDJ.class);
                    Songs[i] = RSDJ.getNombre() +"-"+ RSDJ.getUsuario();
                    i++;
                }
                int j;
                for (j = 0; j < Songs.length; j++)
                    dataName[j].setSong(Songs[j]);
                Adapter Adap = new Adapter(getActivity(), dataName);
                //lstNames=(ListView) view.findViewById(R.id.Lstpl);
                lstNames.setAdapter(Adap);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getActivity(), "Lectura Fallida: "+firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        /*
        Final Firebase
         */
        // Inflate the layout for this fragment

/*
        String probe[]=getResources().getStringArray(R.array.probe2);
        int j;
        for(j=0;j<probe.length;j++)
            dataName[j].setSong(probe[j]);

        Adapter Adap = new Adapter(getActivity(), dataName);
        lstNames = (ListView) view.findViewById(R.id.Lstpl);
        lstNames.setAdapter(Adap);*/


        return view;


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
