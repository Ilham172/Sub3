package com.example.mobiletugas6.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiletugas6.R;
import com.example.mobiletugas6.models.Movie.NowPlayingModel;
import com.example.mobiletugas6.networks.Const;

import java.util.List;

public class NowPlayingRecyclerViewAdapter extends RecyclerView.Adapter<NowPlayingRecyclerViewAdapter.ViewHolder> {

    private List<NowPlayingModel> nowPlayings;
    private OnNoteListener mOnNoteListener;

    public NowPlayingRecyclerViewAdapter(List<NowPlayingModel> nowPlayings, OnNoteListener onNoteListener) {
        this.nowPlayings = nowPlayings;
        this.mOnNoteListener = onNoteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_now_playing_layout, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + nowPlayings.get(position).getImgUrl())
                .into(holder.ivMovieImg);
        holder.tvMovieTitle.setText(nowPlayings.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivMovieImg;
        TextView tvMovieTitle;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            ivMovieImg = view.findViewById(R.id.iv_movie_img);
            tvMovieTitle = view.findViewById(R.id.tv_movie_title);
            this.onNoteListener = onNoteListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}