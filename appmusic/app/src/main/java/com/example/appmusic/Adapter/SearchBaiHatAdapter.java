package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.PlayNhacActivity;
import com.example.appmusic.Model.Baihat;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHoler>{
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_search_bai_hat, parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txttenbaihat.setText(baihat.getTenBaiHat());
        holder.txttencasi.setText(baihat.getCaSi());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView txttenbaihat, txttencasi;
        ImageView imgbaihat, imgluotthich;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txttencasi = itemView.findViewById(R.id.textviewsearchcasi);
            imgbaihat = itemView.findViewById(R.id.imageviewSearchbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewSearchluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.Updateluotthich("1", baihatArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
                                imgluotthich.setImageResource(R.drawable.iconloved);
                                imgluotthich.setEnabled(false);
                            }else{
                                Toast.makeText(context, "Loi!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
}
