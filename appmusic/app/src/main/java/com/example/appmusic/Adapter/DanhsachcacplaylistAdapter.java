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
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachcacplaylistAdapter extends  RecyclerView.Adapter<DanhsachcacplaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public DanhsachcacplaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Picasso.with(context).load(playlist.getHinhnen()).into(holder.imghinhnen);
        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenplaylist;
        ImageView imghinhnen;
        public ViewHolder(View itemView) {
            super(itemView);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DachsachbaihatActivity.class);
                    intent.putExtra("itemplaylist", playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
