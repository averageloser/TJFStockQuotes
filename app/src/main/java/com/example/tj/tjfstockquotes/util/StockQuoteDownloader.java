package com.example.tj.tjfstockquotes.util;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tj.tjfstockquotes.Model.StockQuote;
import com.example.tj.tjfstockquotes.Model.StockQuoteModel;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tj on 8/31/2015.
 */
public class StockQuoteDownloader extends android.support.v4.app.Fragment {
    public interface StockQuoteDownloaderListener {
        void onStockQuoteDownloaded(StockQuote quote);
        void onError(String error);
    }

    private List<StockQuoteDownloaderListener> listeners = Collections.synchronizedList(new ArrayList());

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        setRetainInstance(true);
    }

    public void setListener(StockQuoteDownloaderListener listener) {
        listeners.add(listener);
    }

    public void download(String symbol) {
        new Downloader().execute(symbol);
    }

    private class Downloader extends AsyncTask<String, Void, StockQuote> {

        @Override
        protected StockQuote doInBackground(String... params) {
            StockQuote quote = null;

            StockQuoteModel model = new StockQuoteModel();

            try {
                quote = model.getStockQuote(params[0]);
            } catch (JSONException  | IOException e) {
                //Notify listeners of th error.  This doesn't happen on the ui thread.
                for (StockQuoteDownloaderListener listener : listeners) {
                    listener.onError(e.getMessage());
                }
            }

            return quote;
        }

        @Override
        protected void onPostExecute(StockQuote stockQuote) {
            //Notify listeners of the completion.
            for (StockQuoteDownloaderListener listener : listeners) {
                listener.onStockQuoteDownloaded(stockQuote);
            }
        }
    }
}
