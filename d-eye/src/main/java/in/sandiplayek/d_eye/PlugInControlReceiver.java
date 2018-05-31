package in.sandiplayek.d_eye;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PlugInControlReceiver extends BroadcastReceiver {
    public void onReceive(Context context , Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "POWER CONNECTED", Toast.LENGTH_SHORT).show();
        }
        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "POWER CONNECTED", Toast.LENGTH_SHORT).show();
        }
    }
}