package com.example.tj.tjfstockquotes.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tj.tjfstockquotes.R;

/**
 * Created by tj on 8/30/2015.
 */
public class StockQuoteDetails extends android.support.v4.app.Fragment {
    private TextView stockQuoteName;
    private TextView stockQuoteSymbol;
    private TextView stockQuoteExchange;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        stockQuoteName = (TextView) view.findViewById(R.id.stock_name);

        stockQuoteSymbol = (TextView) view.findViewById(R.id.stock_symbol);

        stockQuoteExchange = (TextView) view.findViewById(R.id.stock_exchange);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();

        String name = arguments.getString("name");

        String symbol = arguments.getString("symbol");

        String exchange = arguments.getString("exchange");

        stockQuoteSymbol.setText(symbol);

        stockQuoteName.setText(name);

        stockQuoteExchange.setText(exchange);
    }
}
