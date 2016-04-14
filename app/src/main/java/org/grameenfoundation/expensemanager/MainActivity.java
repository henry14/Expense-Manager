package org.grameenfoundation.expensemanager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ListView mListview;
    private ActionBarDrawerToggle mToggle;
    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitles = getResources().getStringArray(R.array.expense_array);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListview = (ListView) findViewById(R.id.left_drawer);

        LayoutInflater mInfalter = this.getLayoutInflater();
        LinearLayout mHeader = (LinearLayout)mInfalter.inflate(R.layout.list_header, null);
        LinearLayout mFooter = (LinearLayout)mInfalter.inflate(R.layout.list_footer, null);

        mListview.addHeaderView(mHeader);
        mListview.addFooterView(mFooter);

        mListview.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mTitles));
        mListview.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mToggle = new ActionBarDrawerToggle(this, mDrawer, null, R.string.item_txt, R.string.cost_txt){

            @Override
            public void onDrawerOpened(View mView){
                super.onDrawerOpened(mView);
                getSupportActionBar().setTitle(R.string.nav_open);
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View mView) {
                getSupportActionBar().setTitle(R.string.nav_closed);
                invalidateOptionsMenu();
            }

        };

        mToggle.setDrawerIndicatorEnabled(true);
        mDrawer.addDrawerListener(mToggle);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean mDrawerOpen = mDrawer.isDrawerOpen(mListview);
        menu.findItem(R.id.action_settings).setVisible(!mDrawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
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
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            String testing = "test";
        }
    }
}
