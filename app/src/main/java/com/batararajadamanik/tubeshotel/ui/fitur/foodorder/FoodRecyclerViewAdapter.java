package com.batararajadamanik.tubeshotel.ui.fitur.foodorder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.batararajadamanik.tubeshotel.R;
import com.batararajadamanik.tubeshotel.ui.fitur.menu.ApiClient;
import com.batararajadamanik.tubeshotel.ui.fitur.menu.ApiInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder> {
    public static final String TAG = "TAG";
    private Context context;
    private List<TransaksiFoodDAO> transaksiList;
    private ProgressDialog progressDialog;


    public FoodRecyclerViewAdapter(Context context, List<TransaksiFoodDAO> transaksiList) {
        this.context = context;
        this.transaksiList = transaksiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_adapter_view, parent, false);
        return new FoodViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        TransaksiFoodDAO transaksi = transaksiList.get(position);
        Glide.with(context)
                .load(transaksi.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivMenu);
        holder.menu.setText(transaksi.getMenu());
        holder.price.setText("Rp" + Math.round(transaksi.getPrice()));
        holder.amount.setText(transaksi.getAmount());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hapus Transaksi")
                        .setMessage("Apakah ingin hapus data ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteFood(transaksi.getId());
                            }
                        }).setNegativeButton("Tidak", null)
                        .create().show();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit")
                        .setMessage("Hanya bisa ganti jumlah pesanan. Lanjutkan?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final NumberPicker numberPicker = new NumberPicker(context);
                                numberPicker.setMaxValue(100); //Maximum value to select
                                numberPicker.setMinValue(1); //Minimum value to select


                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setView(numberPicker);
                                builder.setTitle("Number picker");
                                builder.setMessage("Jumlah :");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context, "Number selected " + numberPicker.getValue(), Toast.LENGTH_LONG).show();
                                        updateTransaksiFood(transaksi.getId(), numberPicker.getValue());
                                    }
                                });
                                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context, "Belum Memilih!", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                });
                                builder.show();


                            }
                        }).setNegativeButton("Tidak", null)
                        .create().show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView price, menu, amount;
        Button edit, delete;
        ImageView ivMenu;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            progressDialog = new ProgressDialog(context);
            menu = itemView.findViewById(R.id.twName);
            price = itemView.findViewById(R.id.twPrice);
            amount = itemView.findViewById(R.id.twAmount);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            ivMenu = itemView.findViewById(R.id.ivMenu);


            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }

    private void updateTransaksiFood(String id, int amount) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        Call<TransaksiFoodResponse> update = apiService.updatefood(id, amount);


        update.enqueue(new Callback<TransaksiFoodResponse>() {
            @Override
            public void onResponse(Call<TransaksiFoodResponse> call, Response<TransaksiFoodResponse> response) {
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<TransaksiFoodResponse> call, Throwable t) {
                Toast.makeText(context, "Berhasil Edit", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });
    }

    private void deleteFood(String id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiFoodResponse> delete = apiService.deleteFoodTransaksi(id);


        delete.enqueue(new Callback<TransaksiFoodResponse>() {
            @Override
            public void onResponse(Call<TransaksiFoodResponse> call, Response<TransaksiFoodResponse> response) {
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TransaksiFoodResponse> call, Throwable t) {
                Toast.makeText(context, "Berhasil Hapus", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });
    }
}
