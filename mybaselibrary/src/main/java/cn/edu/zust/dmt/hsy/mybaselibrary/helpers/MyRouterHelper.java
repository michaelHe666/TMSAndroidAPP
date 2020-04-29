package cn.edu.zust.dmt.hsy.mybaselibrary.helpers;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterSettings;
import cn.edu.zust.dmt.hsy.myannotationslibrary.interfaces.IMyRouterRecorder;
import cn.edu.zust.dmt.hsy.mybaselibrary.utils.MyAPTUtils;
import cn.edu.zust.dmt.hsy.mybaselibrary.utils.MyErrorUtils;
import cn.edu.zust.dmt.hsy.mybaselibrary.views.activities.BaseActivity;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * <p>
 * copyright(c) all rights reserved by MR.M
 * @since 4/10/2020 20:30
 **/
public enum MyRouterHelper {
    /**
     * @description instance holder for singleton
     */
    INSTANCE;

    /**
     * @description holder of all router path injection to target class
     */
    private final Map<MyRouterPaths, String> mRouterMap = new HashMap<>();

    /**
     * @param application initialize {@link MyRouterHelper} to make sure it works
     */
    public void initializeMyRouter(@NonNull final Application application) {
        initializeMyRouterMap(application);
    }

    /**
     * @param context context contains {@link IMyRouterRecorder} info
     */
    private void initializeMyRouterMap(@NonNull final Context context) {
        Set<String> myRouterClassSet = MyAPTUtils.getMyRouterClassSet(context
                , new MyRouterSettings().GENERATED_FILE_PATH);
        for (String myRouterRecorder : myRouterClassSet) {
            try {
                Map<MyRouterPaths, String> mapTemp = ((IMyRouterRecorder) Class.forName(myRouterRecorder)
                        .getEnumConstants()[0]).getRouterMap();
                for (MyRouterPaths key : mapTemp.keySet()) {
                    mRouterMap.put(key, Objects.requireNonNull(mapTemp.get(key)));
                }
            } catch (ClassNotFoundException e) {
                MyErrorUtils.showMyArgumentException("MyAPTUtils returns illegal className set!");
            } catch (NullPointerException e) {
                MyErrorUtils.showMyNullPointerException("MyRouterRecorders returns illegal map!");
            }
        }
    }

    /**
     * @param context provide method to start {@link BaseActivity}
     * @param path    MyRouterPath of {@link BaseActivity}
     * @param extras  extra info for start intent
     * @description {@link BaseActivity} filter is added to path targetClass
     */
    public void startBaseActivity(@NonNull final Context context, @NonNull final MyRouterPaths path
            , @Nullable final Bundle extras) {
        final String targetBaseActivityClassString = mRouterMap.get(path);
        if (targetBaseActivityClassString == null) {
            MyErrorUtils.showMyArgumentException("Invalid path for MyRouter!");
        } else {
            try {
                Class<?> unknownClass = Class.forName(targetBaseActivityClassString);
                Class<? extends BaseActivity> targetClass = unknownClass.asSubclass(BaseActivity.class);
                Intent intent = new Intent(context, targetClass);
                if (extras != null) {
                    intent.putExtras(extras);
                }
                context.startActivity(intent);
            } catch (ClassNotFoundException e) {
                MyErrorUtils.showMyArgumentException("MyRouter lead to an invalid target class!");
            }
        }
    }
}
