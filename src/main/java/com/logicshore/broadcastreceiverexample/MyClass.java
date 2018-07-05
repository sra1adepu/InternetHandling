package com.logicshore.broadcastreceiverexample;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MyClass extends Activity implements MainBroadcastReceiver.ConnectionReceiverListener {

    RequestQueue queue;
    MainBroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myclass);
        queue = Volley.newRequestQueue(MyClass.this);

        broadcastReceiver = new MainBroadcastReceiver();
        intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
//methodecall();
               // checkConnection();

    }

    private void checkConnection() {
        boolean isConnected = MainBroadcastReceiver.noConnectivity();
    Log.d("Sravanisconnected",isConnected+"");
        if(isConnected){
            methodecall();
        }

    }

    private void methodecall() {
        String url="";
                    //add method here for getting data.

// prepare the Request
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.d("Response", response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.toString());
                        }
                    }
            );
            // add it to the RequestQueue1
            queue.add(getRequest);
        }



    @Override
    protected void onResume() {

        TestApplication.getInstance().setConnectionListener(this);
       // checkConnection();
        super.onResume();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.d("netwrkconnection",isConnected+"");
        if(isConnected){

        }else{
            checkConnection();
        }
    }
}




