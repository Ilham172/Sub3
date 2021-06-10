package com.example.mobiletugas6.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiletugas6.R;
import com.example.mobiletugas6.models.Movie.NowPlayingModel;
import com.example.mobiletugas6.models.Tv.AiringTodayModel;
import com.example.mobiletugas6.networks.Const;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivDetailImage;
    private TextView tvDetailTitle, tvDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivDetailImage = findViewById(R.id.iv_detail_image);
        tvDetailTitle = findViewById(R.id.tv_detail_title);
        tvDetailDescription = findViewById(R.id.tv_detail_description);

        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Object model = getIntent().getParcelableExtra("DETAIL");

        if (model.getClass().equals(NowPlayingModel.class)) {
            Glide.with(DetailActivity.this)
                    .load(Const.IMG_URL_200 + ((NowPlayingModel) model).getImgUrl())
                    .into(ivDetailImage);
            tvDetailTitle.setText(((NowPlayingModel) model).getTitle());
            tvDetailDescription.setText(((NowPlayingModel) model).getOverview());
        } else if (model.getClass().equals(AiringTodayModel.class)) {
            Glide.with(DetailActivity.this)
                    .load(Const.IMG_URL_200 + ((AiringTodayModel) model).getImgUrl())
                    .into(ivDetailImage);
            tvDetailTitle.setText(((AiringTodayModel) model).getTitle());
            tvDetailDescription.setText(((AiringTodayModel) model).getOverview());
        }


    }

    public void clickback(View view) {
        finish();
    }
}