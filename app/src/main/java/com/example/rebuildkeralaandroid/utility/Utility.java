package com.example.rebuildkeralaandroid.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Utility {
    public static void setVerticalRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, Context context, boolean nestedScroll) {
        if (recyclerView != null && context != null) {
            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setNestedScrollingEnabled(nestedScroll);
            recyclerView.setAdapter(adapter);
        }
    }

    public static Boolean hasNetwork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
