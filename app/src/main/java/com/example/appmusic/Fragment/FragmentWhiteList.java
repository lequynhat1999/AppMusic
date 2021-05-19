package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adapter.WhiteListAdapter;
import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentWhiteList extends Fragment {
    View view;
    RecyclerView recyclerViewWhiteList;
    WhiteListAdapter whiteListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_whitelist,container,false);
        recyclerViewWhiteList = view.findViewById(R.id.recyclerViewWhiteList);
        GetData();
        return view;
    }

    private void GetData() {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetDataWhiteList();
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                ArrayList<WhiteList> arrWhiteList = (ArrayList<WhiteList>) response.body();
                whiteListAdapter = new WhiteListAdapter(getActivity(),arrWhiteList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewWhiteList.setLayoutManager(linearLayoutManager);
                recyclerViewWhiteList.setAdapter(whiteListAdapter);


            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {

            }
        });
    }
}
