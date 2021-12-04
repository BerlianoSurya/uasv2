package com.batararajadamanik.tubeshotel.ui.fitur;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.batararajadamanik.tubeshotel.R;
import com.batararajadamanik.tubeshotel.databinding.FragmentBookBinding;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    private ArrayList<Kamar> ListKamar;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentBookBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false);
        View view = binding.getRoot();

        recyclerView = binding.recyclerViewKamar;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        ListKamar = new DaftarKamar().KAMAR;
        adapter = new RecyclerViewAdapter(getActivity(), ListKamar);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
