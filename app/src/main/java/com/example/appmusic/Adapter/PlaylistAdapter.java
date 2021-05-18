package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmusic.Model.Playlist;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {


    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder
    {
        TextView textViewNamePlaylist;
        ImageView imageViewBackgroundPlaylist, imageViewPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //func này dùng để gắn layout của item vào trong list view
        ViewHolder viewHolder = null;
        if(convertView == null) // check xem view đã được khởi tạo chưa, nếu chưa thì sẽ gán view vào
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_playlist,null);

            viewHolder = new ViewHolder(); // Khởi tạo lại viewHolder

            viewHolder.textViewNamePlaylist = convertView.findViewById(R.id.textviewNamePlaylist); // ánh xạ các view của layout
            viewHolder.imageViewBackgroundPlaylist = convertView.findViewById(R.id.imageviewBackgroundPlaylist);
            viewHolder.imageViewPlaylist = convertView.findViewById(R.id.imageviewPlaylist);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist =  getItem(position); // mỗi dòng item sẽ trả về 1 object, dùng object đấy để nhận dữ liệu về
        Picasso.with(getContext()).load(playlist.getHinhPlayList()).into(viewHolder.imageViewBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imageViewPlaylist);
        viewHolder.textViewNamePlaylist.setText(playlist.getTen());


        return convertView; // khi đã gán dữ liệu xong thì phải return phần layout đã gán dữ liệu về

    }


}
