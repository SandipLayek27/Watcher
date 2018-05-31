package in.sandiplayek.d_eye;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Android on 3/29/2018.
 */

public class DeviceReporter {
    Context context;
    public static String getCurrentTimeStamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date
            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> deviceInfo() {
        HashMap<String,String>deviceInfoHashMap=new HashMap<>();
        String deviceName = android.os.Build.MODEL;
        String deviceManufacture = android.os.Build.MANUFACTURER;
        String deviceBoard = android.os.Build.BOARD;
        String device = Build.DEVICE;
        String deviceBootLoader = Build.BOOTLOADER;
        String deviceBrand = Build.BRAND;
        String deviceFingerPrint = Build.FINGERPRINT;
        String deviceRadioVersion = android.os.Build.getRadioVersion();
        String deviceHOST = Build.HOST;
        String deviceID = Build.ID;
        String deviceProduct = Build.PRODUCT;
        String deviceType = Build.TYPE;
        String deviceUser = Build.USER;
        int version = Build.VERSION.SDK_INT;
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String osName = fields[Build.VERSION.SDK_INT + 1].getName();

        deviceInfoHashMap.put("DeviceName",deviceName);
        deviceInfoHashMap.put("DeviceManufacture",deviceManufacture);
        deviceInfoHashMap.put("DeviceBoard",deviceBoard);
        deviceInfoHashMap.put("Device",device);
        deviceInfoHashMap.put("DeviceBootLoader",deviceBootLoader);
        deviceInfoHashMap.put("DeviceBrand",deviceBrand);
        deviceInfoHashMap.put("DeviceFingerprint",deviceFingerPrint);
        deviceInfoHashMap.put("DeviceRadioVersion",deviceRadioVersion);
        deviceInfoHashMap.put("DeviceHost",deviceHOST);
        deviceInfoHashMap.put("DeviceId",deviceID);
        deviceInfoHashMap.put("DeviceProduct",deviceProduct);
        deviceInfoHashMap.put("DeviceType",deviceType);
        deviceInfoHashMap.put("DeviceUser",deviceUser);
        deviceInfoHashMap.put("DeviceVersion",""+version);
        deviceInfoHashMap.put("OSName",osName);
        return deviceInfoHashMap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static HashMap isSimOneAvailable(Context context){
        SubscriptionManager sManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        SubscriptionInfo infoSim1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            infoSim1 = sManager.getActiveSubscriptionInfoForSimSlotIndex(0);
        }
        if(infoSim1 != null) {
            HashMap<String,String>hashMapSimOne=new HashMap<>();
            String countryIsoCode = infoSim1.getCountryIso();
            String dataRoaming=""+infoSim1.getDataRoaming();
            String iccId=infoSim1.getIccId();
            String carrierName=""+infoSim1.getCarrierName();
            String displayName=""+infoSim1.getDisplayName();
            String mcc=""+infoSim1.getMcc();
            String mnc=""+infoSim1.getMnc();
            String number=""+infoSim1.getNumber();
            String simSlotIndex=""+infoSim1.getSimSlotIndex();
            hashMapSimOne.put("CountryIsoCode",countryIsoCode);
            hashMapSimOne.put("DataRoaming",dataRoaming);
            hashMapSimOne.put("IccId",iccId);
            hashMapSimOne.put("CarrierName",carrierName);
            hashMapSimOne.put("DisplayName",displayName);
            hashMapSimOne.put("Mcc",mcc);
            hashMapSimOne.put("Mnc",mnc);
            hashMapSimOne.put("Number",number);
            hashMapSimOne.put("SimSlotIndex",simSlotIndex);
            return hashMapSimOne;
        }
        HashMap<String,String>hashMapSimOne=new HashMap<>();
        hashMapSimOne = null;
        return hashMapSimOne;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static HashMap isSimTwoAvailable(Context context){
        SubscriptionManager sManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        SubscriptionInfo infoSim2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            infoSim2 = sManager.getActiveSubscriptionInfoForSimSlotIndex(1);
        }
        if(infoSim2 != null) {
            HashMap<String,String>hashMapSimTwo=new HashMap<>();
            String countryIsoCode = infoSim2.getCountryIso();
            String dataRoaming=""+infoSim2.getDataRoaming();
            String iccId=infoSim2.getIccId();
            String carrierName=""+infoSim2.getCarrierName();
            String displayName=""+infoSim2.getDisplayName();
            String mcc=""+infoSim2.getMcc();
            String mnc=""+infoSim2.getMnc();
            String number=""+infoSim2.getNumber();
            String simSlotIndex=""+infoSim2.getSimSlotIndex();
            hashMapSimTwo.put("CountryIsoCode",countryIsoCode);
            hashMapSimTwo.put("DataRoaming",dataRoaming);
            hashMapSimTwo.put("IccId",iccId);
            hashMapSimTwo.put("CarrierName",carrierName);
            hashMapSimTwo.put("DisplayName",displayName);
            hashMapSimTwo.put("Mcc",mcc);
            hashMapSimTwo.put("Mnc",mnc);
            hashMapSimTwo.put("Number",number);
            hashMapSimTwo.put("SimSlotIndex",simSlotIndex);
            return hashMapSimTwo;
        }
        HashMap<String,String>hashMapSimTwo=new HashMap<>();
        hashMapSimTwo = null;
        return hashMapSimTwo;
    }

    public static String getOperatingSystemName(){
        int CVersion = android.os.Build.VERSION.SDK_INT;
        String OSName="";
        switch (CVersion){

            case 11 :
                OSName="Honeycomb";
                break;

            case 12 :
                OSName="Honeycomb";
                break;

            case 13 :
                OSName="Honeycomb";
                break;

            case 14 :
                OSName="Ice Cream Sandwich";
                break;

            case 15 :
                OSName="Ice Cream Sandwich";
                break;

            case 16 :
                OSName="Jelly Bean";
                break;

            case 17 :
                OSName="Jelly Bean";
                break;

            case 18 :
                OSName="Jelly Bean";
                break;

            case 19 :
                OSName="KitKat";
                break;

            case 21 :
                OSName="Lollipop";
                break;

            case 22 :
                OSName="Lollipop";
                break;

            case 23 :
                OSName="Marshmallow";
                break;

            case 24 :
                OSName="Nougat";
                break;

            case 25 :
                OSName="Nougat";
                break;

            default:
                OSName="Not Found";
                break;
        }
        return OSName;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static HashMap getPhoneMemoryStatus(Context context){
        String isExternalMemoryAvailable="",lowMemorySpace="";
        HashMap<String,String>hashMapPhoneMemoryStatus=new HashMap<>();
        ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;
        long availableMemory = memInfo.availMem;
        boolean lowMemory = memInfo.lowMemory;
        if(lowMemory){
            lowMemorySpace="YES";
        }else{
            lowMemorySpace="NO";
        }
        long threshold = memInfo.threshold;
        boolean isExternalMemory=android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(isExternalMemory){
            isExternalMemoryAvailable="Yes";
            hashMapPhoneMemoryStatus.put("AvailableExternalMemory",""+getAvailableExternalMemorySize());
            hashMapPhoneMemoryStatus.put("TotalExternalMemory",""+getTotalExternalMemorySize());
        }else{
            isExternalMemoryAvailable="No";
        }
        hashMapPhoneMemoryStatus.put("TotalRAMMemory",""+formatSize(totalMemory));
        hashMapPhoneMemoryStatus.put("AvailableRAMMemory",""+formatSize(availableMemory));
        hashMapPhoneMemoryStatus.put("LowMemory",""+lowMemorySpace);
        hashMapPhoneMemoryStatus.put("Threshold",""+formatSize(threshold));
        hashMapPhoneMemoryStatus.put("ExternalMemoryAvailable",isExternalMemoryAvailable);
        return hashMapPhoneMemoryStatus;
    }

    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            return formatSize(availableBlocks * blockSize);
        } else {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long totalBlocks = stat.getBlockCountLong();
            return formatSize(totalBlocks * blockSize);
        } else {
            return null;
        }
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MB";
                size /= 1024;
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    public static String getSentSms(Context context){
        Cursor cur;
        Uri uriSMSURI = null;
        uriSMSURI = Uri.parse("content://sms/sent");
        cur = ((Activity)context).getContentResolver().query(uriSMSURI, null, null, null,null);
        String sms = "";
        try{
            while (cur.moveToNext()) {
                String  msg_body=cur.getString(cur.getColumnIndexOrThrow("body")).toString();
                sms += "MessageId: "+cur.getString(0)+"\n"+"ThreadId: "+cur.getString(1)+"\n"+"To: "+cur.getString(2)+"\n"+"ContactId: "+String.valueOf(cur.getLong(3))+"\n"+"TimeStamp: "+cur.getLong(4)+"\n"+"Type: "+cur.getInt(cur.getColumnIndexOrThrow("type"))+"\n"+"Protocol: "+cur.getInt(cur.getColumnIndexOrThrow("protocol"))+"\n"+"Body: "+msg_body+"\n\n";
            }
        }catch(Exception e){
            Toast.makeText(context, "No SENT SMS Found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return sms;
    }

    public static String getInboxSms(Context context){
        Cursor cur;
        Uri uriSMSURI = null;
        uriSMSURI = Uri.parse("content://sms/inbox");
        cur = ((Activity)context).getContentResolver().query(uriSMSURI, null, null, null,null);
        String sms = "";
        try{
            while (cur.moveToNext()) {
                String  msg_body=cur.getString(cur.getColumnIndexOrThrow("body")).toString();
                sms += "MessageId: "+cur.getString(0)+"\n"+"ThreadId: "+cur.getString(1)+"\n"+"From: "+cur.getString(2)+"\n"+"ContactId: "+String.valueOf(cur.getLong(3))+"\n"+"TimeStamp: "+cur.getLong(4)+"\n"+"Body: "+msg_body+"\n\n";
            }
        }catch(Exception e){
            Toast.makeText(context, "No INBOX SMS Found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return sms;
    }

    public static String getDraftSms(Context context){
        Cursor cur;
        Uri uriSMSURI = null;
        uriSMSURI = Uri.parse("content://draft");
        cur = ((Activity)context).getContentResolver().query(uriSMSURI, null, null, null,null);
        String sms = "";
        try{
            while (cur.moveToNext()) {
                String  msg_body=cur.getString(cur.getColumnIndexOrThrow("body")).toString();
                sms += "MessageId: "+cur.getString(0)+"\n"+"ThreadId: "+cur.getString(1)+"\n"+"To: "+cur.getString(2)+"\n"+"ContactId: "+String.valueOf(cur.getLong(3))+"\n"+"TimeStamp: "+cur.getLong(4)+"\n"+"Type: "+cur.getInt(cur.getColumnIndexOrThrow("type"))+"\n"+"Protocol: "+cur.getInt(cur.getColumnIndexOrThrow("protocol"))+"\n"+"Body: "+msg_body+"\n\n";
            }
        }catch(Exception e){
            Toast.makeText(context, "No Draft SMS Found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return sms;
    }

    public static String getContactLogList(Context context){
        Cursor cur;
        Uri uriSMSURI = null;
        StringBuffer sb = new StringBuffer();
        cur = ((Activity)context).managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = cur.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cur.getColumnIndex(CallLog.Calls.TYPE);
        int date = cur.getColumnIndex(CallLog.Calls.DATE);
        int duration = cur.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Details :");
        while (cur.moveToNext()) {
            String phNumber = cur.getString(number);
            String callType = cur.getString(type);
            String callDate = cur.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cur.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n----------------------------------");
        }
        cur.close();
        return String.valueOf(sb);
    }

    public static String getContactList(Context context){
        ContentResolver cr;
        Cursor cur;
        ProgressDialog pd;
        String contact_list="";
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait.......");
        pd.setTitle("Fetch Contact List");
        cr = ((Activity)context).getContentResolver();
        cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (cur.getCount() > 0) {
            pd.show();
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact_list=contact_list+"Name:- "+name+"\nPhone No:- "+phoneNo+"\n\n";
                    }

                    if(cur.getCount()<=0){
                        pd.dismiss();
                    }
                    pCur.close();
                }
            }
            pd.dismiss();
        }
        return contact_list;
    }

    public static boolean isPowerCableConnected(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }

}
