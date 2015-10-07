package com.henderlabs.fragmentexercise;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_place, new FragmentOne())
                        .addToBackStack(null)
                        .commit();
            } else {
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentOne, new FragmentOne())
                        .add(R.id.fragmentTwo, new FragmentTwo())
                        .commit();
            }
        }

        setContentView(R.layout.activity_main);
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

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    public void selectFrag(View view) {
        Fragment frag;

        if (view == findViewById(R.id.button2))
            frag = new FragmentTwo();
        else
            frag = new FragmentOne();

        FragmentManager fragM = getFragmentManager();
        FragmentTransaction fragT = fragM.beginTransaction();
        fragT.replace(R.id.fragment_place, frag);
        fragT.addToBackStack(null);
        fragT.commit();
    }
}
