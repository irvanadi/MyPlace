package com.xeylyne.myplace.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xeylyne.myplace.Activities.DetailActivity;
import com.xeylyne.myplace.Models.Place;
import com.xeylyne.myplace.R;
import com.xeylyne.myplace.Models.RequestIDPlace;
import com.xeylyne.myplace.Models.ResultMessage;
import com.xeylyne.myplace.Utilities.RetrofitInstance;
import com.xeylyne.myplace.Utilities.DataHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<Place> results;
    DataHelper dataHelper;
    String id_places;

    public HomeAdapter(Context context, ArrayList<Place> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ViewHolder holder = new ViewHolder(v);
        dataHelper = new DataHelper(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Place result = results.get(position);
        final Bitmap bitmap = DataHelper.getImage(result.getImage());
        holder.txtName.setText(result.getNAME_PLACE());
        holder.txtAddress.setText(result.getADDRESS_PLACE());
        holder.txtFavFood.setText(result.getFAV_FOOD());
        holder.txtAvgPrice.setText(result.getAVG_PRICE());
        holder.imgLogo.setImageBitmap(bitmap);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AlertDialog.Builder delete = new AlertDialog.Builder(context);
                    delete.setMessage("Yakin Mau Menghapus Tempat Ini ?").setCancelable(false)
                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SharedPreferences credential = context.getSharedPreferences("login", MODE_PRIVATE);
                                    final String id = credential.getString("id", "Null");

                                    Toast.makeText(context, "Item telah Terhapus", Toast.LENGTH_SHORT).show();
                                    dataHelper.deleteData(result.getID_PLACE());

                                    Call<RequestIDPlace> call1 = RetrofitInstance.getInstance().id_places(result.getNAME_PLACE(), Integer.parseInt(id));
                                    call1.enqueue(new Callback<RequestIDPlace>() {
                                        @Override
                                        public void onResponse(Call<RequestIDPlace> call, Response<RequestIDPlace> response) {
                                            id_places = response.body().getResult().get(0).getIDPLACE();
                                            Log.d("ID_PLACE", "onResponse: "+ id_places);
                                            Call<ResultMessage> call2 = RetrofitInstance.getInstance().delete(Integer.parseInt(id_places));
                                            call2.enqueue(new Callback<ResultMessage>() {
                                                @Override
                                                public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                                                    results.remove(position);
                                                    notifyDataSetChanged();
                                                    Log.d("SUCC2", "onResponse: "+result.getID_PLACE());
                                                }

                                                @Override
                                                public void onFailure(Call<ResultMessage> call, Throwable t) {

                                                }
                                            });

                                        }

                                        @Override
                                        public void onFailure(Call<RequestIDPlace> call, Throwable t) {

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = delete.create();
                    alertDialog.setTitle("Delete Statement");
                    alertDialog.show();
                }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences credential = context.getSharedPreferences("login", MODE_PRIVATE);
                final String id = credential.getString("id", "Null");

                AlertDialog.Builder delete = new AlertDialog.Builder(context);
                delete.setMessage("Yakin Mau Sharing Tempat Ini ?").setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("ID", id);
                                Log.d("ID2", "onClick: " + result.getNAME_PLACE()+
                                        result.getADDRESS_PLACE()+ result.getFAV_FOOD()+ result.getAVG_PRICE()+
                                        result.getREVIEW()+ id);

//                                byte[] asd = DataHelper.getBytes(bitmap);
//
//                                File asd2 = savebitmap(bitmap);
//
//                                RequestBody nameplace = RequestBody.create(MediaType.parse("text/plain"), result.getNAME_PLACE());
//                                RequestBody addressplace = RequestBody.create(MediaType.parse("text/plain"), result.getADDRESS_PLACE());
//                                RequestBody favfood = RequestBody.create(MediaType.parse("text/plain"), result.getFAV_FOOD());
//                                RequestBody avgprice = RequestBody.create(MediaType.parse("text/plain"), result.getAVG_PRICE());
//                                RequestBody review = RequestBody.create(MediaType.parse("text/plain"), result.getREVIEW());
//                                RequestBody id_user = RequestBody.create(MediaType.parse("text/plain"), id);
//                                RequestBody image = RequestBody.create(MediaType.parse("image/*"), asd2);
//                                MultipartBody.Part body = MultipartBody.Part.createFormData("cycle", asd2.getName(), image);
//                                Log.d("asd", nameplace.toString() + addressplace + favfood + avgprice +review +id_user+body);
//                                Call<ResultMessage> call = RetrofitInstance.getInstance().insertPLace2(nameplace, addressplace, favfood,
//                                        avgprice, review, id_user, body);
//                                call.enqueue(new Callback<ResultMessage>() {
//                                    @Override
//                                    public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
//                                        if (response.isSuccessful()){
//                                            Log.d("Success", "onResponse: "+response.body().getMessage());
//                                            Toast.makeText(context, "Thanks For Sharing :)", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            Toast.makeText(context, "Sharing Gagal Tolong Cek Koneksi Anda :)", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResultMessage> call, Throwable t) {
//                                        Toast.makeText(context, "Sharing Gagal Tolong Cek Koneksi Anda :)", Toast.LENGTH_SHORT).show();
//                                    }
//                                });



                                Call<ResultMessage> call = RetrofitInstance.getInstance().insertPLace(result.getNAME_PLACE(),
                                        result.getADDRESS_PLACE(), result.getFAV_FOOD(), result.getAVG_PRICE(),
                                        result.getREVIEW(), Integer.parseInt(id));
                                call.enqueue(new Callback<ResultMessage>() {
                                    @Override
                                    public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                                        if (response.isSuccessful()){
                                            Log.d("Success", "onResponse: "+response.body().getMessage());
                                            Toast.makeText(context, "Thanks For Sharing :)", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Sharing Gagal Tolong Cek Koneksi Anda :)", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResultMessage> call, Throwable t) {
                                        Toast.makeText(context, "Sharing Gagal Tolong Cek Koneksi Anda :)", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = delete.create();
                alertDialog.setTitle("Sharing Statement");
                alertDialog.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", result.getNAME_PLACE());
                intent.putExtra("address", result.getADDRESS_PLACE());
                intent.putExtra("favfood", result.getFAV_FOOD());
                intent.putExtra("avgprice", result.getAVG_PRICE());
                intent.putExtra("review", result.getREVIEW());
                context.startActivity(intent);
            }
        });
    }


    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        OutputStream outStream = null;

        File file = new File( extStorageDirectory, "bmp.png");
        if (file.exists()) {
            file.delete();
            file = new File( extStorageDirectory,"bmp.png");
            Log.e("file exist", "" + file + ",Bitmap= " + bmp);
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("file", "" + file);
        return file;

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgLogo, btnDelete, btnShare;
        TextView txtName, txtAddress, txtFavFood, txtAvgPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLogo = itemView.findViewById(R.id.imgLogoCafe);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtFavFood = itemView.findViewById(R.id.txtFavoriteFoods);
            txtAvgPrice = itemView.findViewById(R.id.txtAveragePrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnShare = itemView.findViewById(R.id.btnShare);
        }
    }
}
