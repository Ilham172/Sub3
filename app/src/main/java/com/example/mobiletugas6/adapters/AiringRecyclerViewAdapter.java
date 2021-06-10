package com.example.mobiletugas6.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiletugas6.R;
import com.example.mobiletugas6.models.Tv.AiringTodayModel;
import com.example.mobiletugas6.networks.Const;

import java.util.List;

public class AiringRecyclerViewAdapter extends RecyclerView.Adapter<AiringRecyclerViewAdapter.ViewHolder> {

    private List<AiringTodayModel> airingToday;
    private OnItemClick onItemClick;

    public AiringRecyclerViewAdapter(List<AiringTodayModel> airingToday, OnItemClick onItemClick) {
        this.airingToday = airingToday;
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClick(int pos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_airing_layout, parent, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + airingToday.get(position).getImgUrl())
                .into(holder.ivMovieImg);
        holder.tvMovieTitle.setText(airingToday.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return airingToday.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivMovieImg;
        TextView tvMovieTitle;

        public ViewHolder(View view, OnItemClick onItemClick) {
            super(view);
            view.setOnClickListener(this);
            ivMovieImg = view.findViewById(R.id.iv_airing_today_img);
            tvMovieTitle = view.findViewById(R.id.tv_airing_today_title);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(getBindingAdapterPosition());
        }
    }
}