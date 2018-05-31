package in.sandiplayek.watcher;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import in.sandiplayek.d_eye.DeviceReporter;
import in.sandiplayek.d_eye.GPSTracker;
import in.sandiplayek.d_eye.InternetConnectivity;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private static final String[] PERMISSION_LIST ={
            Manifest.permission.ACCESS_FINE_LOCATION, 
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS};
    private static final int RC_PERMISSION = 124;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*GPSTracker gpsTracker = new GPSTracker(MainActivity.this);
        if(gpsTracker.canGetLocation()){
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        }*/

        //double latitude = gpsTracker.getLatitude();
        //double longitude = gpsTracker.getLongitude();

        //Location location = gpsTracker.getLocation();

        //gpsTracker.showSettingsAlert();

        /*
            DeviceReporter.getCurrentTimeStamp();

            DeviceReporter.deviceInfo();

            DeviceReporter.isSimOneAvailable(MainActivity.this);
        */

        if (hasPermissions()) {
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();
            //DeviceReporter.isSimTwoAvailable(MainActivity.this);
            //DeviceReporter.getPhoneMemoryStatus(MainActivity.this);
            //String sentSMS=DeviceReporter.getSentSms(MainActivity.this);
            //String inboxSMS=DeviceReporter.getInboxSms(MainActivity.this);
            //String draftSMS=DeviceReporter.getDraftSms(MainActivity.this);
            //String LogList=DeviceReporter.getContactLogList(MainActivity.this);
            //String ContactList=DeviceReporter.getContactList(MainActivity.this);

            /*if(DeviceReporter.isPowerCableConnected(MainActivity.this)){
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "DisConnected", Toast.LENGTH_SHORT).show();
            }*/
            //DeviceReporter.getPhoneMemoryStatus(MainActivity.this);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location_contacts), RC_PERMISSION, PERMISSION_LIST);
        }



        /*DeviceReporter.getOperatingSystemName();

        DeviceReporter.getPhoneMemoryStatus(MainActivity.this);

        DeviceReporter.getSentSms(MainActivity.this);

        DeviceReporter.getInboxSms(MainActivity.this);

        DeviceReporter.getDraftSms(MainActivity.this);

        DeviceReporter.getContactLogList(MainActivity.this);

        DeviceReporter.getContactList(MainActivity.this);*/

        HashMap<String,String>hashMap=new HashMap<>();
        hashMap=InternetConnectivity.getNetworkDetails(MainActivity.this);


        NetworkInfo networkInfo = InternetConnectivity.getNetworkInfo(MainActivity.this);
        String info = networkInfo.getExtraInfo();
        String reason = networkInfo.getReason();
        String subtypeName = networkInfo.getSubtypeName();
        NetworkInfo.State st = networkInfo.getState();
        String sta = st.name();
        String typeName = networkInfo.getTypeName();

        if(InternetConnectivity.isConnected(MainActivity.this)){
            Toast.makeText(this, "Connected By Internet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Not-Connected By Internet", Toast.LENGTH_SHORT).show();
        }

        if(InternetConnectivity.isConnectedMobile(MainActivity.this)){
            Toast.makeText(this, "Connected By Mobile Internet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Not-Connected By Mobile Internet", Toast.LENGTH_SHORT).show();
        }

        if(InternetConnectivity.isConnectedWifi(MainActivity.this)){
            Toast.makeText(this, "Connected By Wifi-Internet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Not-Connected By WiFi-Internet", Toast.LENGTH_SHORT).show();
        }

        if(InternetConnectivity.isConnectedFast(MainActivity.this)){
            Toast.makeText(this, "Fast Connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fast Not-Connected", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> list) {
        if(requestCode==124){

        }else{
            Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsDenied(int i, @NonNull List<String> list) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private boolean hasPermissions() {
        return EasyPermissions.hasPermissions(this, PERMISSION_LIST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
