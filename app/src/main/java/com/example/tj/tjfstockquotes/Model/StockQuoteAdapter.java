package com.example.tj.tjfstockquotes.Model;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tj.tjfstockquotes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tj on 8/29/2015.
 */
public class StockQuoteAdapter extends RecyclerView.Adapter<StockQuoteAdapter.StockQuoteViewHolder> {

    private List<StockQuote> list;

    public StockQuoteAdapter(List<StockQuote> list) {
        this.list = list;
    }

    @Override
    public StockQuoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new StockQuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StockQuoteViewHolder stockQuoteViewHolder, int i) {
        StockQuote quote = list.get(i);

        String name = null;

        if (quote != null) {
            name = quote.getName();
        } else {
            name = "Error.  Delete this and try again!";
        }

        stockQuoteViewHolder.tv.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public StockQuoteViewHolder getViewHolder() {
        return this.getViewHolder();
    }

    public static class StockQuoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv;

        public StockQuoteViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("position", String.valueOf(getAdapterPosition()));
        }
    }
}
