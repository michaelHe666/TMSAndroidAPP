package cn.edu.zust.dmt.hsy.mybaselibrary.models.remote.request;

import android.os.Build;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cn.edu.zust.dmt.hsy.mybaselibrary.BuildConfig;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/23/2020 16:29
 **/
public class BaseNetworkRequest<T extends BaseRequestModel> {
    private final JsonElement args;
    private final JsonElement deviceInfo;

    public BaseNetworkRequest(@NonNull final T args) {
        Gson gson = new Gson();
        this.args = gson.toJsonTree(args);
        this.deviceInfo = getDeviceInfo(gson);
    }

    private JsonElement getDeviceInfo(@NonNull final Gson gson) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("OS", gson.toJsonTree("Android"));
        jsonObject.add("MobileType", gson.toJsonTree(Build.MODEL));
        jsonObject.add("MobileSDK", gson.toJsonTree(Build.VERSION.SDK_INT));
        jsonObject.add("AppVersion", gson.toJsonTree(BuildConfig.VERSION_CODE));
        return jsonObject;
    }
}
