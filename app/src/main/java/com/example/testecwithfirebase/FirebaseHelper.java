package com.example.testecwithfirebase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class FirebaseHelper {
    public static final String TAG = "FirebaseHelper";
    private String url = "";
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAnalytics firebaseAnalytics;
    public FirebaseHelper(Context context) {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public String getUrl() {
        return this.url;
    }

    public void addTestingData(String _url) {
        DatabaseReference ref = firebaseDatabase.getReference(String.format("goods/%s", _url));
        ref.setValue("kobe", "test");

//        this.getTestingData("goods");


    }

    public void getTestingData(String _root) {
        DatabaseReference ref = firebaseDatabase.getReference().child(_root);
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        Log.d(TAG, "onDataChange: " + dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        Log.d(TAG, "onDataChange: " + databaseError.getMessage());
                    }
                });
    }

    public void analyticsTesting(String _id, String _name, String _content) {
        Log.d(TAG, "analyticsTesting: ");
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, _id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, _name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, _content);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle);

//        firebaseAnalytics.setUserProperty("Added Goods", _content);
    }


}
