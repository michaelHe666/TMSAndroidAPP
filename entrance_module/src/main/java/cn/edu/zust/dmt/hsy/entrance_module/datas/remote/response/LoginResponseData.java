package cn.edu.zust.dmt.hsy.entrance_module.datas.remote.response;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.response.BaseResponseData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/24/2020 10:51
 **/
public final class LoginResponseData implements BaseResponseData {
    private String username;
    private String permission;

    public LoginResponseData(@NonNull final String username, @NonNull final String permission) {
        this.username = username;
        this.permission = permission;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPermission() {
        return permission;
    }
}
