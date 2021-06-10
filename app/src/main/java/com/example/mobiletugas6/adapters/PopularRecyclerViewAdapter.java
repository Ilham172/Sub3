package com.example.mobiletugas6.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiletugas6.R;
import com.example.mobiletugas6.models.Movie.PopularModel;
import com.example.mobiletugas6.networks.Const;

import java.util.List;

public class PopularRecyclerViewAdapter extends RecyclerView.Adapter<PopularRecyclerViewAdapter.ViewHolder> {

    private final List<PopularModel> popular;
    private OnItemClick onItemClick;

    public PopularRecyclerViewAdapter(List<PopularModel> popular, OnItemClick onItemClick) {
        this.popular = popular;
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClick(int pos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_popular_layout, parent, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + popular.get(position).getImgUrl())
                .into(holder.ivPopularImg);
        holder.tvPopularTitle.setText(popular.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return popular.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivPopularImg;
        TextView tvPopularTitle;

        public ViewHolder(View view, OnItemClick onItemClick) {
            super(view);
            view.setOnClickListener(this);
            ivPopularImg = view.findViewById(R.id.iv_popular_img);
            tvPopularTitle = view.findViewById(R.id.tv_popular_title);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(getBindingAdapterPosition());
        }
    }
}