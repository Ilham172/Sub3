package com.example.mobiletugas6.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobiletugas6.R;
import com.example.mobiletugas6.activities.DetailActivity;
import com.example.mobiletugas6.adapters.NowPlayingRecyclerViewAdapter;
import com.example.mobiletugas6.models.Movie.NowPlayingModel;
import com.example.mobiletugas6.models.Movie.NowPlayingResponse;
import com.example.mobiletugas6.networks.Const;
import com.example.mobiletugas6.networks.MovieApiClient;
import com.example.mobiletugas6.networks.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment implements NowPlayingRecyclerViewAdapter.OnNoteListener {
    private static final String TAG = "NowPlayingFragment";
    private RecyclerView recyclerView;
    private NowPlayingRecyclerViewAdapter adapter;
    private List<NowPlayingModel> nowPlayings;
    private NowPlayingRecyclerViewAdapter.OnNoteListener mOnNoteListener;

    public NowPlayingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing_list, container, false);
        recyclerView = view.findViewById(R.id.rv_now_playing_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL, false));
        loadData();
        return view;
    }

    public void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit()
                .create(MovieApiInterface.class);

        Call<NowPlayingResponse> nowPlayingCall = movieApiInterface.getNowPlaying(Const.API_KEY);
        nowPlayingCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayings() != null) {
                    nowPlayings = response.body().getNowPlayings();
                    adapter = new NowPlayingRecyclerViewAdapter(nowPlayings, NowPlayingFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("DETAIL", nowPlayings.get(position));
        startActivity(intent);
    }
}