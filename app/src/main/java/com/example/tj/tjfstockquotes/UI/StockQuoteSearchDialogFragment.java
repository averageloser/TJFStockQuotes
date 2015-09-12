package com.example.tj.tjfstockquotes.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tj.tjfstockquotes.Model.StockQuote;
import com.example.tj.tjfstockquotes.R;

/**
 * Created by tj on 3/18/2015.
 * Pretty simple fragment dialog that changes a city then notifies its activity of the change.
 */
public class StockQuoteSearchDialogFragment extends DialogFragment {
    private EditText symbolInput;

    private StockQuoteSearchListener listener;

    //The callback to the activity.
    public interface StockQuoteSearchListener {
        void onStockQuoteSearched(String symbol);
    }

    public StockQuoteSearchDialogFragment() {
        //setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (StockQuoteSearchListener) getActivity();
        } catch (ClassCastException e) {
            Log.e("Search Dialog Error", "Class Cast Exception.  Acivities must implement StockQuoteSearchListener");

            throw new IllegalStateException("Acivities must implement StockQuoteSearchListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stock_quote_search_layout, container, false);

        symbolInput = (EditText) view.findViewById(R.id.symbol_input);

        Button searchButton = (Button) view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {

            //Call the activity and send the strings for the city and state or country to the activity for processing.
            @Override
            public void onClick(View v) {
                if (!symbolInput.getText().toString().isEmpty()) {
                    listener.onStockQuoteSearched(symbolInput.getText().toString());
                    clearFields();
                    dismiss();
                }
            }
        });

        return view;
    }

    //For clearing fields between searches.
    private void clearFields() {
        symbolInput.setText("");
    }
}
