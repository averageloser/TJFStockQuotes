package com.example.tj.tjfstockquotes.UI;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
public class StockQuoteList extends android.support.v4.app.Fragment implements
        StockQuoteAdapter.OnListItemClickListener {

    public interface StockQuoteListActivityCallback {
        void onItemClicked(StockQuote quote);
    }

    private StockQuoteListActivityCallback activity;
    private RecyclerView recyclerView;
    private StockQuoteAdapter adapter;
    private List<StockQuote> quotes = new ArrayList();

    public StockQuoteList() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activity = (StockQuoteListActivityCallback) getActivity();
        } catch(ClassCastException e) {
            Log.e("StockQuoteList Error", "Class Cast Exception.  Acivities must implement OnItemClicked");

            throw new IllegalStateException("Acivities must implement OnItemClicked");
        }
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

        adapter = new StockQuoteAdapter(quotes);
        adapter.addItemClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                removeStockQuote(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    //Removes a stock quote when the user swipes it out of the recyclerview.  Can be handled without the activity.
    public void removeStockQuote(int position) {
        quotes.remove(position);

        adapter.notifyDataSetChanged();
    }
    //Adds a StockQuote to the adapter and updates it.  Can be handled without the activity.
    public void addStockQuote(StockQuote quote) {
        quotes.add(quote);

        adapter.notifyDataSetChanged();
    }

    //When an item in the adapter is clicked, get the StockQuote and pass it to the activity for processing.
    @Override
    public void onListItemClicked(int position) {
        activity.onItemClicked(quotes.get(position));
    }
}
