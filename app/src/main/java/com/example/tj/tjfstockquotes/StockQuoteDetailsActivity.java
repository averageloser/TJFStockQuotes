package com.example.tj.tjfstockquotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tj.tjfstockquotes.UI.StockQuoteDetails;

/**
 * Created by tj on 9/12/15.
 */
public class StockQuoteDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_main);

        Bundle arguments = getIntent().getExtras();

        StockQuoteDetails detailsFragment = new StockQuoteDetails();

        detailsFragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction().add(R.id.details_container, detailsFragment).commit();
    }
}
