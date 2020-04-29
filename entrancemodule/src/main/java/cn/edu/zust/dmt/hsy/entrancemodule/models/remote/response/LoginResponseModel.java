package cn.edu.zust.dmt.hsy.entrancemodule.models.remote.response;

import cn.edu.zust.dmt.hsy.mybaselibrary.models.remote.response.BaseResponseModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/24/2020 10:51
 **/
public class LoginResponseModel implements BaseResponseModel {
    private String username;
    private String permission;

    public LoginResponseModel(String username, String permission) {
        this.username = username;
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public String getPermission() {
        return permission;
    }
}
