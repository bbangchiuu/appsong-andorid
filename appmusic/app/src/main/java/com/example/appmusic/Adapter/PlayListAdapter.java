package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmusic.Model.Playlist;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<Playlist> {
    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground, imgplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhnen()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playlist.getHinhicon()).into(viewHolder.imgplaylist);
        viewHolder.txttenplaylist.setText(playlist.getTen());

        return convertView;
    }
}
