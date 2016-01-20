package com.estebanposada.ponmela;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class RejectSong extends Fragment {


    ListView lstNames;
    private Firebase mRef;
    public String StSong[]={};

    public RejectSong() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reject_song, container, false);

        /*
        Firebase
         */
        Firebase.setAndroidContext(getContext());
        mRef = new Firebase("https://boiling-fire-6064.firebaseio.com/");
        lstNames=(ListView) view.findViewById(R.id.Lstrej);
        Firebase RequestedSongs=mRef.child("RejectedSongs");
        RequestedSongs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int contador = (int) snapshot.getChildrenCount();
                int i = 0;
                String[] Songs = new String[contador];
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    RequestedSongsDJ RSDJ = postSnapshot.getValue(RequestedSongsDJ.class);
                    Songs[i] = RSDJ.getNombre() + "-" + RSDJ.getUsuario();
                    i++;
                }
                StSong=Songs;
                ArrayList<LName> items = new ArrayList<LName>();
                int j;
                //if (Songs.length != 0) {
                    for (j = 0; j < Songs.length; j++) {
                        items.add(new LName(R.drawable.part, Songs[j], 0, 0));
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
