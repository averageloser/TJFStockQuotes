package com.example.tj.tjfstockquotes;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tj.tjfstockquotes.Model.StockQuote;
import com.example.tj.tjfstockquotes.Model.StockQuoteModel;
import com.example.tj.tjfstockquotes.UI.StockQuoteDetails;
import com.example.tj.tjfstockquotes.UI.StockQuoteList;
import com.example.tj.tjfstockquotes.UI.StockQuoteSearchDialogFragment;
import com.example.tj.tjfstockquotes.util.StockQuoteDownloader;

public class StockQuoteListActivity extends AppCompatActivity implements StockQuoteDownloader.StockQuoteDownloaderListener,
    StockQuoteSearchDialogFragment.StockQuoteSearchListener, StockQuoteList.StockQuoteListActivityCallback {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private StockQuoteModel model;
    private StockQuoteList listFragment;
    private StockQuoteDetails detailsFragment;
    private StockQuoteDownloader downloader;
    private Bundle detailsArguments; //the bundle passed to the details activity containing Quote info.
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

        detailsArguments = new Bundle();

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, listFragment)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();

        //Add the downloader to the activity.
        downloader = new StockQuoteDownloader();
        downloader.setListener(this);

        getSupportFragmentManager().beginTransaction().add(downloader, "dl").commit();

        //The stock quote search dialog fragment.
        final StockQuoteSearchDialogFragment stockQuoteSearchDialogFragment = new StockQuoteSearchDialogFragment();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stockQuoteSearchDialogFragment.isAdded() && !stockQuoteSearchDialogFragment.isVisible()) {

                    //Now make sure there is an internet connection.  If not, inform the user.
                    if (isDataNetworkActive()) {
                        stockQuoteSearchDialogFragment.show(getSupportFragmentManager(), "search");
                    } else {
                        new AlertDialog.Builder(StockQuoteListActivity.this)
                                .setTitle("Data connection issue")
                                .setMessage("Seems you don't have internet access.  Check that and try again.")
                                .show();
                    }
                }
            }
        });
    }

    //Requests a stock quote from the model.  If successful, quote will be returned in onStockQuoteSearched() callback.
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
    /* When the downloader finishes a download this is called.  The quote is then passed to the listFragment
    so that it can add it to its list of quotes, then the RecyclerView is updated.
     */
    @Override
    public void onStockQuoteDownloaded(StockQuote quote) {
        listFragment.addStockQuote(quote);
    }

    //This does NOT happen on the UI thread!
    @Override
    public void onError(String error) {
        Log.i("error", error);
    }

    //Callback to notify me when users have entered their desired stock symbol.  Call the downloader with the data.
    @Override
    public void onStockQuoteSearched(String symbol) {
        downloader.download(symbol);
    }

    //Callback to inform me of which item was clicked in the recyclerview.
    @Override
    public void onItemClicked(StockQuote quote) {
        /*Now that I know which Item was clicked in the list, I can pass that information to the details fragment.
        I could just pass the quote to the fragment as a parcelable, but why bother with the extra overhead? */
        String name = quote.getName();

        String symbol = quote.getSymbol();

        String exchange = quote.getExchange();

        detailsArguments.putString("name", name);
        detailsArguments.putString("symbol", symbol);
        detailsArguments.putString("exchange", exchange);

        //Create and start the intent for the details activity and pass the details arguments to it.
        Intent detailsActivityIntent = new Intent(this, StockQuoteDetailsActivity.class);
        detailsActivityIntent.putExtras(detailsArguments);

        startActivity(detailsActivityIntent);
    }

    //Check to make sure a network connection is available.
    private boolean isDataNetworkActive() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        Log.i("NetworkInfoSize", String.valueOf(netInfo.length));

        for (NetworkInfo ni : netInfo) {
            int type = ni.getType();

            if (type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE) {
                if (ni.isConnected()) {
                    return true;
                }
            }
        }

        return false;
    }
}
