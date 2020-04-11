package com.example.testecwithfirebase;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uuid = UUID.randomUUID().toString();
                FirebaseHelper firebaseHelper = new FirebaseHelper(MainActivity.this);
                firebaseHelper.addTestingData(uuid);
                Snackbar.make(view, String.format("Add goods %s", uuid), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, uuid);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Kobe");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Text");
                Log.d(TAG, "onClick: "+bundle.toString());
                FirebaseAnalytics.getInstance(MainActivity.this).logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//                firebaseHelper.analyticsTesting(uuid, "kobe", "Add Kobe");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenu: ");
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
            Log.d(TAG, "onOptionsItemSelected: ");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
