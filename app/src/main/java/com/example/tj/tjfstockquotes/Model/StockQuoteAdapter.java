package com.example.tj.tjfstockquotes.Model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.tj.tjfstockquotes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tj on 8/29/2015.
 */
public class StockQuoteAdapter extends RecyclerView.Adapter<StockQuoteAdapter.StockQuoteViewHolder> {
    public static interface OnListItemClickListener {
        void onListItemClicked(int position);
    }

    private static List<OnListItemClickListener> itemClickListeners;

    private List<StockQuote> list;

    public StockQuoteAdapter(List<StockQuote> list) {
        this.list = list;

        itemClickListeners = new ArrayList();
    }

    public static void addItemClickListener(OnListItemClickListener listener) {
        if (itemClickListeners != null) {
            itemClickListeners.add(listener);
        } else {
            Log.i("StockQuoteAdapter", "Class not intialized yet.");
        }
    }

    public static void notifyOnListItemClickListeners(int position) {
        if (itemClickListeners != null && itemClickListeners.size() > 0) {
            for (OnListItemClickListener listener : itemClickListeners) {
                listener.onListItemClicked(position);
            }
        } else {
            Log.i("StockQuoteAdapter", "Class not intialized yet.");
        }

        Log.i("position", String.valueOf(position));
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
            name = "Problem with this quote.  Maybe wrong symbol?  Delete this and try again!";
        }

        stockQuoteViewHolder.tv.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class StockQuoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv;

        public StockQuoteViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.text);

            itemView.setOnClickListener(this);
        }

        //Here I can notify listeners of which item was clicked.
        @Override
        public void onClick(View v) {
            StockQuoteAdapter.notifyOnListItemClickListeners(getAdapterPosition());
        }
    }
}
