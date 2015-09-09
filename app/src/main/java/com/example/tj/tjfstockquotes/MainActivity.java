package com.example.tj.tjfstockquotes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tj.tjfstockquotes.Model.StockQuote;
import com.example.tj.tjfstockquotes.Model.StockQuoteModel;
import com.example.tj.tjfstockquotes.UI.StockQuoteList;
import com.example.tj.tjfstockquotes.util.StockQuoteDownloader;

public class MainActivity extends AppCompatActivity implements StockQuoteDownloader.StockQuoteDownloaderListener {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private StockQuoteModel model;

    private StockQuoteList listFragment;
    private StockQuoteDownloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("TJF Stock Quotes");

        setSupportActionBar(toolbar);

        model = new StockQuoteModel();

        listFragment = new StockQuoteList();

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, listFragment).commit();

        //Add the downloader to the activity.
        downloader = new StockQuoteDownloader();
        downloader.setListener(this);

        getSupportFragmentManager().beginTransaction().add(downloader, "dl").commit();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSymbolData("GOOG");
            }
        });
    }

    //Requests a stock quote from the model.  If successful, quote will be returned in onStockQuoteDownloaded() callback.
    private void getSymbolData(String symbol) {
        downloader.download(symbol);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /////////////////////////Callbacks for the quote downloader fragment///////////////////////
    @Override
    public void onStockQuoteDownloaded(StockQuote quote) {
        listFragment.addStockQuote(quote);
    }

    //This does NOT happen on the UI thread!
    @Override
    public void onError(String error) {
        Log.i("error", error);
    }
}
