package com.example.mobiletugas6.fragments;

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
import com.example.mobiletugas6.adapters.PopularRecyclerViewAdapter;
import com.example.mobiletugas6.models.Movie.PopularModel;
import com.example.mobiletugas6.models.Movie.PopularResponse;
import com.example.mobiletugas6.networks.Const;
import com.example.mobiletugas6.networks.MovieApiClient;
import com.example.mobiletugas6.networks.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularFragment extends Fragment implements PopularRecyclerViewAdapter.OnItemClick{
    private static final String TAG = "PopularFragment";
    private RecyclerView recyclerView;
    private PopularRecyclerViewAdapter adapter;
    private List<PopularModel> popularModelList;

    public PopularFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_list, container, false);
        recyclerView = view.findViewById(R.id.rv_popular_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        loadData();
        return view;
    }

    public void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit()
                .create(MovieApiInterface.class);

        Call<PopularResponse> popularCall = movieApiInterface.getPopular(Const.API_KEY);
        popularCall.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                if (response.isSuccessful() && response.body().getPopulars() != null) {
                    popularModelList = response.body().getPopulars();
                    adapter = new PopularRecyclerViewAdapter(popularModelList, (PopularFragment.this));
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {

    }
}