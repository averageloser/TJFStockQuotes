package com.example.tj.tjfstockquotes.UI;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tj.tjfstockquotes.Model.StockQuote;
import com.example.tj.tjfstockquotes.Model.StockQuoteAdapter;
import com.example.tj.tjfstockquotes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tj on 8/29/2015.
 */
public class FragmentList extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
    private StockQuoteAdapter adapter;
    List<StockQuote> quotes = new ArrayList();

    public FragmentList() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*
        for (int i = 0; i < 30; i++) {
            StockQuote quote = new StockQuote();
            quote.setName(String.valueOf(i));

            quotes.add(quote);

            Log.i("quote", quote.toString());
        }
        */

        adapter = new StockQuoteAdapter(quotes);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    //Adds a StockQuote to the adapter and updates it.
    public void addStockQuote(StockQuote quote) {
        quotes.add(quote);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
