package com.batararajadamanik.tubeshotel.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.batararajadamanik.tubeshotel.R;

public class DashboardFragment extends Fragment {
    private CardView cardHome,cardFood,cardLocation,cardTransaksi;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    Context context;
    private static final int LOCATION_REQUEST = 1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static public final int REQUEST_LOCATION = 1;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        cardHome = view.findViewById(R.id.book);
        cardFood = view.findViewById(R.id.food);
        cardLocation = view.findViewById(R.id.cardLocation);
        cardTransaksi = view.findViewById(R.id.card_transaksi);
        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_nav_dashboard_to_nav_book);
            }
        });

        cardFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_nav_dashboard_to_nav_food);

            }
        });

        cardLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_dashboard_to_nav_location);
                }

            }
        });

        cardTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_dashboard_to_nav_transaksi_dashboard);
            }
        });
        return view;

    }
}
