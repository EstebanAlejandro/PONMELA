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


/**
 * A simple {@link Fragment} subclass.
 */
public class RejectSong extends Fragment {

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

    public RejectSong() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reject_song, container, false);

        String probe[]=getResources().getStringArray(R.array.probe2);
        int j;
        for(j=0;j<probe.length;j++)
            dataName[j].setSong(probe[j]);

        lstNames = (ListView) view.findViewById(R.id.Lstrej);
        Adapter Adap = new Adapter(getActivity(), dataName);
        lstNames.setAdapter(Adap);

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
