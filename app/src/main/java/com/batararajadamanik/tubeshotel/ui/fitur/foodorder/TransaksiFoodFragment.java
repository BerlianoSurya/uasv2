package com.batararajadamanik.tubeshotel.ui.fitur.foodorder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.batararajadamanik.tubeshotel.R;
import com.batararajadamanik.tubeshotel.databinding.FragmentBookBinding;
import com.batararajadamanik.tubeshotel.ui.fitur.menu.ApiClient;
import com.batararajadamanik.tubeshotel.ui.fitur.menu.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiFoodFragment extends Fragment {
    private ArrayList<TransaksiFoodDAO> ListTransaksi = new ArrayList<>();
    private FoodRecyclerViewAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentBookBinding binding;
    FirebaseAuth fAuth;
    SwipeRefreshLayout refreshLayout;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    String userId;
    private String email;
    View v;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransaksiFoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransaksiFoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransaksiFoodFragment newInstance(String param1, String param2) {
        TransaksiFoodFragment fragment = new TransaksiFoodFragment();
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
        v = inflater.inflate(R.layout.fragment_transaksi_food, container, false);

        refreshLayout = v.findViewById(R.id.swipe_refresh);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        recyclerView = v.findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        final DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    email = documentSnapshot.getString("email");
                    loadFood(email);

                    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            loadFood(email);
                            refreshLayout.setRefreshing(false);
                        }
                    });

                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });


        return v;

    }
    public void loadFood(String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiFoodResponse> call = apiService.getFoodTransaksiByEmail(email, "data");

        call.enqueue(new Callback<TransaksiFoodResponse>() {
            @Override
            public void onResponse(Call<TransaksiFoodResponse> call, Response<TransaksiFoodResponse> response) {

                if (response.body()==null) {
                    ListTransaksi = new ArrayList<>();
                    generateDataList(ListTransaksi);
                    Toast.makeText(getContext(),"Data Kosong", Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }else{
                    generateDataList(response.body().getTransactionsFood());
                    Toast.makeText(getContext(),"Berhasil Muat Data", Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<TransaksiFoodResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });
    }


    private void generateDataList(List<TransaksiFoodDAO> customerList) {
        recyclerView = v.findViewById(R.id.user_rv);
        recyclerAdapter = new FoodRecyclerViewAdapter(getContext(), customerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
