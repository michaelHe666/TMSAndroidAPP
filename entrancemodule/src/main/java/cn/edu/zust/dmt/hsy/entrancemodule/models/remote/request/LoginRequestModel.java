package cn.edu.zust.dmt.hsy.entrancemodule.models.remote.request;

import cn.edu.zust.dmt.hsy.mybaselibrary.models.remote.request.BaseRequestModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description This class shall keep same field name as network standards since it uses Gson.
 * @since 4/23/2020 15:48
 **/
public class LoginRequestModel implements BaseRequestModel {
    private String voucher;
    private String token;

    public LoginRequestModel(String voucher, String token) {
        this.voucher = voucher;
        this.token = token;
    }
}
