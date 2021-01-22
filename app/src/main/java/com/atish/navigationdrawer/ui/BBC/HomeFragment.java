package com.atish.navigationdrawer.ui.BBC;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.atish.navigationdrawer.Adapter;
import com.atish.navigationdrawer.ApiInterface;

import com.atish.navigationdrawer.R;
import com.atish.navigationdrawer.RetrofitInstance;
import com.atish.navigationdrawer.modals.Article;
import com.atish.navigationdrawer.modals.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    private static final String API_KEY = "3782e6c8cb7e46bea8fc4564420b6bb0";
     SwipeRefreshLayout swipeRefreshLayout;
    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        swipeRefreshLayout = root.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        onLoadingSwipeRefresh("");
        return root;
    }


    public void loadJson(){
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        Call<News> call;


        call = apiInterface.getNews("us", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body().getArticles().size() > 0) {

                    List<Article> articles = response.body().getArticles();
                    Adapter adapter = new Adapter(getContext(), articles);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(getContext(), "List is not empty", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "This is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onRefresh() {
       loadJson();
    }
    public void onLoadingSwipeRefresh(final String keyword){
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                      loadJson();
                    }
                }
        );
    }
}