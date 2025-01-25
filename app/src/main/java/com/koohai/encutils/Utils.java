package com.koohai.encutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;
public class Utils {
    public String getPath(Context cxt){
        PackageManager pm = cxt.getPackageManager();
        List<PackageInfo> pkgList = pm.getInstalledPackages(0);
        if (pkgList == null || pkgList.size() == 0) return null;
        for (PackageInfo pi : pkgList) {
            if (pi.applicationInfo.nativeLibraryDir.startsWith("/data/app/")
                    && pi.packageName.startsWith("com.koohai.revdemo")) {
                Log.e("koohai", pi.applicationInfo.nativeLibraryDir);
                return pi.applicationInfo.nativeLibraryDir;
            }
        }
        return null;
    }
}
