package com.logicshore.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class MainBroadcastReceiver extends BroadcastReceiver {

    public static ConnectionReceiverListener connectionReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

               // Log.d("Connection",intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)+"");
                if (noConnectivity) {
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();

                } else {
                    TestApplication.getInstance().getApplicationContext();
                    if (connectionReceiverListener != null) {
                        Log.d("eree",noConnectivity+"");
                        connectionReceiverListener.onNetworkConnectionChanged(noConnectivity);
                    }
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                }
            }

    }

    public static boolean noConnectivity(){
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            boolean noConnectivity = false;
            Log.d("Connection",noConnectivity+"");

        }
        return true;
    }

    public interface ConnectionReceiverListener {
        void onNetworkConnectionChanged(boolean noConnectivity);
    }


}
