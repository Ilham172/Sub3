package com.example.mobiletugas6.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletugas6.R;
import com.example.mobiletugas6.activities.DetailActivity;
import com.example.mobiletugas6.adapters.AiringRecyclerViewAdapter;
import com.example.mobiletugas6.models.Tv.AiringResponse;
import com.example.mobiletugas6.models.Tv.AiringTodayModel;
import com.example.mobiletugas6.networks.Const;
import com.example.mobiletugas6.networks.TvApiClient;
import com.example.mobiletugas6.networks.TvApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AiringFragment extends Fragment implements AiringRecyclerViewAdapter.OnItemClick{
    private static final String TAG = "AiringFragment";
    private RecyclerView recyclerView;
    private AiringRecyclerViewAdapter adapter;
    private List<AiringTodayModel> airingToday;

    public AiringFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airing_list, container, false);
        recyclerView = view.findViewById(R.id.rv_airing_today_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        loadData();
        return view;
    }

    public void loadData() {
        TvApiInterface tvApiInterface = TvApiClient.getRetrofit()
                .create(TvApiInterface.class);

        Call<AiringResponse> nowPlayingCall = tvApiInterface.getAiring(Const.API_KEY);
        nowPlayingCall.enqueue(new Callback<AiringResponse>() {
            @Override
            public void onResponse(Call<AiringResponse> call, Response<AiringResponse> response) {
                if (response.isSuccessful() && response.body().getAirings() != null) {
                    airingToday = response.body().getAirings();
                    adapter = new AiringRecyclerViewAdapter(airingToday, (AiringFragment.this));
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AiringResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("DETAIL", airingToday.get(pos));
        startActivity(intent);
    }
}