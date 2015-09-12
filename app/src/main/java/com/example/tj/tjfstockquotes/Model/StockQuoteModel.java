package com.example.tj.tjfstockquotes.Model;

/**
 * Created by tj on 8/29/2015.
 */

import android.net.Uri;
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
import java.util.Set;


/**
 * Created by tom on 8/9/2015.
 */
public class StockQuoteModel {
    public interface StockQuoteModelListener {
        void onStockQuoteDownloaded(StockQuote quote);
        void onAllStockQuotesDownloaded(List<StockQuote> quotes);
        void onStockQuotesDownloadError();
    }

    private final String BASEURL = "http://dev.markitondemand.com/Api/v2/Lookup/jsonp?input=NFLX&callback=myFunction";
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

        String response = builder.toString().substring(19, builder.toString().length() - 2);

        Log.i("response", response);

        return response;
    }

    /**
     *
     * @param symbol The stock quote symbol for which to search.
     * @return A StockQuote object for this symbol.
     * @throws JSONException
     * @throws IOException
     */
    public StockQuote getStockQuote(String symbol) throws JSONException, IOException {
        Uri uri = new Uri.Builder()
            .scheme("http")
            .authority("dev.markitondemand.com")
            .appendPath("Api")
            .appendPath("V2")
            .appendPath("Lookup")
            .appendPath("jsonp")
            .appendQueryParameter("input", symbol.toUpperCase()).build();

        String responseData = getJSONData(uri.toString());

        /*Set all Stock Quote information here.  I don't actually do that, so you will need to call
         the appropriate setters for the info you want. I just get name and price for this example.*/

        StockQuote stockQuote = new StockQuote();

        //The first result is the exact match.
        JSONObject result = new JSONObject(responseData);

        stockQuote.setSymbol(symbol.toUpperCase());
        stockQuote.setName(result.getString("Name"));
        stockQuote.setExchange(result.getString("Exchange"));

        return stockQuote;
    }
}