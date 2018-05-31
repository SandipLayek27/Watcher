# Watcher
Device Watcher: User can easily fetch mobile device information like storage, application error log etc. 
For, Marshmallow OS Device we used runtime Permissions.
We create this library for my working purpose.
## Developed
[![Sandip](https://avatars1.githubusercontent.com/u/31722942?v=4&u=18643bfaaba26114584d27693e9891db26bcb582&s=39) Sandip](https://github.com/SandipLayek27)  
# ★ Gradle Dependency
```sh
Add Gradle dependency in the build.gradle file of your application module (app in the most cases) : First Tab:
allprojects{
    repositories{
        jcenter()
        maven {
            url 'https://jitpack.io'
        }
    }
}
AND
dependencies {
    
}
```

# ★ Features are.
```sh
1. Device Reporter.
  1.a.  Current TimeStamp.
  1.b.  Device Info:- 
          Device Model Name.
          Device Manufacture.
          Device Board.
          Device.
          Device BootLoader.
          Device Brand.
          Device Finger Print.
          Device Radio Version.
          Device HOST.
          Device ID.
          Device Product.
          Device Type.
          Device User.
          Version.
          Os Name.
  1.c.  Sim One Available: If availbale then fetch details.
          CountryIsoCode.
          DataRoaming.
          IccId.
          CarrierName.
          DisplayName.
          Mcc.
          Mnc.
          Number.
          SimSlotIndex.
  1.d.  Sim One Available: If availbale then fetch details.
          CountryIsoCode.
          DataRoaming.
          IccId.
          CarrierName.
          DisplayName.
          Mcc.
          Mnc.
          Number.
          SimSlotIndex.
  1.e.  Operating System Name.
  1.f.  Phone Memory Status.
          Is External Memory Available.
          Available External Memory.
          Total External Memory.
          Total RAM Memory.
          Available RAM Memory.
          Low Memory.
          Thres hold.
  1.g.  Get Sent SMS.
  1.h.  Get Inbox SMS.
  1.i.  Get Draft SMS.
  1.j.  Get Call Log List.
  1.k.  Get Contact List.
  1.l.  Power Cable Connected Or Not.

2. GPS Tracker.
  2.a.  Can Get Location.
  2.b.  Get Latitude.
  2.c.  Get Longitude.
  2.d.  Get Location.
  2.e.  show Settings Alert.
  
3. Internet Connectivity.
  3.a.  Get Network Info.
  3.b.  Get Network Details.
  3.c.  Connection Checking.
  3.d.  Connected WIFI or Not.
  3.e.  Connected Mobile Internet or Not.
  3.f.  Check Connection Speed. 
  
4. Method Caller.
  4.a.  Float Bounder.
  
5. Write To File.
  5.a.  Write Log To File. PASS(TAGNAME,VALUE)
  5.b.  Create LogCat.
  5.c.  Create Error LogCat.
```

# ★ Uses of this features.
```sh
----------------------------------------------------------------------------------------------------------
❆ MainActivity.java PAGE:- [FOR RUNTIME PERMISSION]
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
        if (hasPermissions()) {
           
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location_contacts), RC_PERMISSION, PERMISSION_LIST);
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
----------------------------------------------------------------------------------------------------------
❆ Methods Calling Technique:-
1. Device Reporter.
  1.a.  Current TimeStamp.                                        
          String currentTimeStamp = DeviceReporter.getCurrentTimeStamp();
          
  1.b.  Device Info:-                                             
          HashMap<String,String>hashMap=new HashMap();
          hashMap = DeviceReporter.deviceInfo();
          
  1.c.  Sim One Available: If availbale then fetch details.
          HashMap<String,String>hashMap=new HashMap();
          hashMap = DeviceReporter.isSimOneAvailable(CONTEXT);
          
  1.d.  Sim Two Available: If availbale then fetch details. 
          HashMap<String,String>hashMap=new HashMap();
          hashMap = DeviceReporter.isSimTwoAvailable(CONTEXT);
         
  1.e.  Operating System Name.
          String osName = DeviceReporter.getOperatingSystemName();
          
  1.f.  Phone Memory Status.  
          HashMap<String,String>hashMap=new HashMap();
          hashMap = DeviceReporter.getPhoneMemoryStatus(CONTEXT);
         
  1.g.  Get Sent SMS.                                               
          String data = DeviceReporter.getSentSms(CONTEXT);
          
  1.h.  Get Inbox SMS.                                              
          String data = DeviceReporter.getInboxSms(CONTEXT);
          
  1.i.  Get Draft SMS.                                              
          String data = DeviceReporter.getDraftSms(CONTEXT);
          
  1.j.  Get Call Log List.                                          
          String data = DeviceReporter.getContactLogList(CONTEXT);
          
  1.k.  Get Contact List.                                           
          String data = DeviceReporter.getContactList(CONTEXT);
          
  1.l.  Power Cable Connected Or Not.                               
          boolean data = DeviceReporter.isPowerCableConnected(CONTEXT);

2. GPS Tracker.
    GPSTracker gpsTracker = new GPSTracker(MainActivity.this);
    if(gpsTracker.canGetLocation()){
       Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
    }else{
       Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
    }
    double latitude = gpsTracker.getLatitude();
    double longitude = gpsTracker.getLongitude();
    Location location = gpsTracker.getLocation();
    gpsTracker.showSettingsAlert();
  
3. Internet Connectivity.
    HashMap<String,String>hashMap=new HashMap<>();
    hashMap=InternetConnectivity.getNetworkDetails(CONTEXT);
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
  
4. Method Caller.
  4.a.  Float Bounder.
    MethodCaller.floatBounder(VALUE);
  
5. Write To File.
    WriteToFile.writeLogToFile(TAGGING,VALUE);
    WriteToFile.createLogCat();
    WriteToFile.createErrorLogCat();
    
----------------------------------------------------------------------------------------------------------
❆ AndroidManifest.xml PAGE:-
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.READ_CALL_LOG"/>
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
    <uses-permission android:name="android.permission.READ_SMS"/>
    <uses-permission android:name="android.permission.READ_PHONE_NUMBERS"/>
```
