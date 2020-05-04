package cn.edu.zust.dmt.hsy.my_base_library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyThreadHelper;
import dalvik.system.DexFile;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/12/2020 21:21
 **/
public final class MyAPTUtils {
    /**
     * @description ensure {@link MyAPTUtils} should not be instantiated
     */
    private MyAPTUtils() {
        throw new AssertionError();
    }

    /**
     * @param context             application context
     * @param packageNameForClass packageNameForClass for searching classes
     * @return all class starts packageNameForClass in context
     */
    public static Set<String> getMyRouterClassSet(@NonNull final Context context
            , @NonNull final String packageNameForClass) {
        final List<String> pathList = Objects.requireNonNull(getSourcePathList(context));
        final Set<String> myRouterClassSet = new HashSet<>();
        final CountDownLatch countDownLatch = new CountDownLatch(pathList.size());
        for (String pathString : pathList) {
            MyThreadHelper.INSTANCE.runMyTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //todo: try JNI by myself to solve depreciate of DexFile
                                DexFile dexFile = new DexFile(pathString);
                                Enumeration<String> dexEntries = dexFile.entries();
                                while (dexEntries.hasMoreElements()) {
                                    String className = dexEntries.nextElement();
                                    if (className.startsWith(packageNameForClass)) {
                                        myRouterClassSet.add(className);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                countDownLatch.countDown();
                            }
                        }
                    }
            );
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myRouterClassSet;
    }

    /**
     * @param context context where could lead to application context
     * @return pathSourceList of application
     */
    @Nullable
    private static List<String> getSourcePathList(@NonNull final Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (applicationInfo != null) {
            List<String> sourcePathList = new ArrayList<>();
            sourcePathList.add(applicationInfo.sourceDir);
            String[] tempStringArray = applicationInfo.splitSourceDirs;
            if (tempStringArray != null) {
                sourcePathList.addAll(Arrays.asList(tempStringArray));
            }
            return sourcePathList;
        } else {
            return null;
        }
    }
}
