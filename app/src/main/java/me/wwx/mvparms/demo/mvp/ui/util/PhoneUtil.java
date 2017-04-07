package me.wwx.mvparms.demo.mvp.ui.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import me.wwx.mvparms.demo.R;

import static android.text.TextUtils.isEmpty;

/**
 * 作者：wwx on 2017/4/6 0006 17:14
 * 邮箱：wanwenxiu0709@foxmail.com
 * 描述：
 */

public class PhoneUtil {

    /**
     * 获取app版本
     * @param mContext
     * @return
     */
    public static String getVersion(Context mContext) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            String version = info.versionName;
            Log.d("geek","version"+version);
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return mContext.getString(R.string.can_not_find_version_name);
        }
    }

    /**
     * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
     * 渠道标志为：
     * 1，andriod（a）
     * 识别符来源标志：
     * 1， wifi mac地址（wifi）；
     * 2， IMEI（imei）；
     * 3， 序列号（sn）；
     * 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("a");
        try {
            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
            }
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
            }

            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                Log.e("geek : ", "序列号（sn）=" + deviceId.toString());
            }

            return deviceId.toString();
        } catch (Exception e) {
            Log.d("geek", "getDeviceId: e");
            deviceId.append("e"+deviceId.toString());
        }
        return deviceId.toString();
    }

    /** 获取手机品牌*/
    public static String getPhoneBrand(){
        return android.os.Build.BRAND;
    }

    /** 获取手机型号*/
    public static String getPhoneModel(){
        return android.os.Build.MODEL;
    }

    /** 获取Android系统版本*/
    public static String getPhoneSysVersion(){
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
}
