package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adapter.SearchAdapter;
import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbarSearch;
    RecyclerView recyclerViewSearch;
    TextView textViewNull;
    SearchAdapter searchAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbarSearch = view.findViewById(R.id.toolbarSearch);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        textViewNull = view.findViewById(R.id.textViewNull);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarSearch);
        toolbarSearch.setTitle("");
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) { // hàm để gắn layout của menu vào
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { // khi nào nhấn enter mới bắt đầu search

                SearchKey(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) { // khi value thay đổi thì bắt đầu search luôn
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerViewSearch.setVisibility(View.GONE);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);



    }

    private  void SearchKey(String query)
    {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetSearchSong(query);
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                ArrayList<WhiteList> arrSong = (ArrayList<WhiteList>) response.body();
                if(arrSong.size() > 0)
                {
                    searchAdapter = new SearchAdapter(getActivity(),arrSong);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearch.setLayoutManager(linearLayoutManager);
                    recyclerViewSearch.setAdapter(searchAdapter);
                    textViewNull.setVisibility(View.GONE); // ẩn đi
                    recyclerViewSearch.setVisibility(View.VISIBLE);
                }
                else
                {
                    textViewNull.setVisibility(View.VISIBLE); // ẩn đi
                    recyclerViewSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {

            }
        });
    }
}
