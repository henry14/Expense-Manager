package org.grameenfoundation.expensemanager;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import org.grameenfoundation.expensemanager.Helpers.ExpenseProvider;
import org.grameenfoundation.expensemanager.Models.Expense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyActivity extends AppCompatActivity { //implements FloatingActionButton.OnClickListener {

    //private static final Uri fetchUri = "org.grameenfoundation.provider.expensemanager";
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setAdapter(new DisplayAdapter(getAllExpenses()));


    }

    @Override
    protected void onStart() {
        super.onStart();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DailyActivity.this, AddExpense.class));

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_daily, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.date_choice) {
            /*
            Call the Calendar
            Use calendar provider for selecting a date
            Will equally be used for selecting desired month
             */
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Expense getExpense(){

        return null;
    }

    private List<Expense> getAllExpenses() {

        final List<Expense> mExpenses = new ArrayList<Expense>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] mProjection = {Expense.LABEL, Expense.COST, Expense.DATE};
                Cursor mCursor = getContentResolver().query(ExpenseProvider.mContentUri, mProjection, null, null, "Item");

                while (mCursor.moveToNext()) {
                    mExpenses.add(new Expense(mCursor.getString(0), mCursor.getInt(1), mCursor.getString(2)));
                }
                mCursor.close();
            }
        }).start();

        return mExpenses;
    }
}