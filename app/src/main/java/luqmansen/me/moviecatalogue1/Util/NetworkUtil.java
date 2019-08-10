package luqmansen.me.moviecatalogue1.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkUtil {

    private Context context;

    public NetworkUtil(Context context) {
        this.context = context;
    }

    public void isNetworkAvailable() {
        ConnectivityManager cm =  (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressWarnings("deprecation") NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected == false){
            Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
        }
    }
}
