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
    private String userId;
    private String permission;
    private String workCell;

    public LoginResponseData(@NonNull final String userId, @NonNull final String permission
            , @NonNull final String workCell) {
        this.userId = userId;
        this.permission = permission;
        this.workCell = workCell;
    }

    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getPermission() {
        return permission;
    }

    public String getWorkCell() {
        return workCell;
    }
}
