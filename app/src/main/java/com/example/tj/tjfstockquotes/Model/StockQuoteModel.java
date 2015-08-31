package com.example.tj.tjfstockquotes.Model;

/**
 * Created by tj on 8/29/2015.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tom on 8/9/2015.
 */
public class StockQuoteModel {
    public interface StockQuoteModelListener {
        void onStockQuoteDownloaded(StockQuote quote);
        void onAllStockQuotesDownloaded(List<StockQuote> quotes);
        void onStockQuotesDownloadError();
    }

    private final String urlStart = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(";
    private final String urlEnd = ")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    /**
     * @param url - String url location of rest data.
     * @return - String containing json data for parsing.
     */
    private String getJSONData(String url) throws IOException {
        URL restLocation = null;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        StringBuilder builder = null;
        String line = null;

        restLocation = new URL(url);

        conn = (HttpURLConnection) restLocation.openConnection();

        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        conn.disconnect();

        return builder.toString();
    }

    /**
     *
     * @param symbol The stock quote symbol for which to search.
     * @return A StockQuote object for this symbol.
     * @throws JSONException
     * @throws IOException
     */
    public StockQuote getStockQuote(String symbol) throws JSONException, IOException {
        String responseData = getJSONData(urlStart + "%22" + symbol + "%22" + urlEnd);

        Log.i("getQuote", responseData);

        StockQuote stockQuote = new StockQuote();

        JSONObject response = new JSONObject(responseData);

        JSONObject query = response.getJSONObject("query");

        JSONObject results = query.getJSONObject("results");

        JSONObject resultsQuote = results.getJSONObject("quote");

        stockQuote.setName(resultsQuote.getString("Name"));

        return stockQuote;
    }

    public List<StockQuote> getStockQuoteList(String... symbols) throws IOException, JSONException {
        String urlOption = "%2C";

        List<StockQuote> quotes = new ArrayList();

        String symbolsParam = "";

        for (int i = 0; i < symbols.length; i++) {
            symbolsParam += "%22" + symbols[i] + "%22";

            //Add a comma after all symbols, except for the last.
            if (i < symbols.length - 1) {
                symbolsParam += "%2C";
            }
        }

        String url = getJSONData(urlStart + symbolsParam + urlEnd);

        Log.i("URL", url);

        return quotes;
    }
}