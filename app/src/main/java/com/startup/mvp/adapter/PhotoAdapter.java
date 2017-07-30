package com.startup.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.startup.mvp.R;
import com.startup.mvp.interfaces.main.OnRecyclerViewItemClickListener;
import com.startup.mvp.model.Photo;

import java.util.List;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> implements View.OnClickListener {

    private List<Photo> listPhoto;
    private Context mcontext;
    private OnRecyclerViewItemClickListener<Photo> itemClickListener;
    private int itemLayout;

    public PhotoAdapter(Context context, int itemLayout) {
        this.mcontext = context;
        this.itemLayout = itemLayout;
    }

    public void setListPhoto(List<Photo> listPhoto) {
        this.listPhoto = listPhoto;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Photo item = listPhoto.get(position);
        holder.itemView.setTag(item);
        // holder.name.setText(item.getTitle());

        Glide.with(mcontext)
                .load(item.getThumbnailUrl())
                //.centerCrop()
                .crossFade()
                .into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        if (listPhoto != null)
            return listPhoto.size();
        else
            return 0;
    }

    @Override
    public void onClick(View view) {

        if (itemClickListener != null) {
            Photo model = (Photo) view.getTag();
            itemClickListener.onItemClick(view, model);
        }

    }


    public void add(Photo item, int position) {
        listPhoto.add(position, item);
        //notifyItemInserted(position);

    }

    public void remove(Photo item) {
        int position = listPhoto.indexOf(item);
        listPhoto.remove(position);
        //notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Photo> listener) {
        this.itemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.name_people)
        TextView name;
        ImageView imgPhoto;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_people);
            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
        }
    }
}
