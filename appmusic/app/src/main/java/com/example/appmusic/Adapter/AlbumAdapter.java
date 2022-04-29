package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.DachsachbaihatActivity;
import com.example.appmusic.Model.Album;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHoler>{
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent, false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txtcasialbum.setText(album.getTenCaSiAlbum());
        holder.txttenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imghinhanhAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView imghinhanhAlbum;
        TextView txttenalbum, txtcasialbum;
        public ViewHoler(View itemView) {
            super(itemView);
            imghinhanhAlbum = itemView.findViewById(R.id.imageviewalbum);
            txtcasialbum = itemView.findViewById(R.id.textviewtencasialbum);
            txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chuyenDoiManHinh(getPosition());
                }
            });
            imghinhanhAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chuyenDoiManHinh(getPosition());
                }
            });
        }
    }

    void chuyenDoiManHinh(int position){
        Intent intent = new Intent(context, DachsachbaihatActivity.class);
        intent.putExtra("album", albumArrayList.get(position));
        context.startActivity(intent);
    }
}
