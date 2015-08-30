package com.example.tj.tjfstockquotes.Model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tj.tjfstockquotes.R;

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
    public void onBindViewHolder(StockQuoteViewHolder stockQuoteViewHolder, int i) {
        StockQuote quote = list.get(i);

        stockQuoteViewHolder.tv.setText(quote.toString());
    }

    @Override
    public StockQuoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        StockQuoteViewHolder stockQuoteViewHolder = new StockQuoteViewHolder(view);

        return stockQuoteViewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class StockQuoteViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public StockQuoteViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
