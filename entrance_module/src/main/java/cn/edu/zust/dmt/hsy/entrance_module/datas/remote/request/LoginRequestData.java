package cn.edu.zust.dmt.hsy.entrance_module.datas.remote.request;

import cn.edu.zust.dmt.hsy.my_base_library.datas.remote.request.BaseRequestData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description {@link BaseRequestData} shall keep same field name as network standards since it uses Gson.
 * all {@link BaseRequestData} should only has its private fields.
 * @since 4/23/2020 15:48
 **/
public final class LoginRequestData implements BaseRequestData {
    private String voucher;
    private String password;

    public LoginRequestData(String voucher, String password) {
        this.voucher = voucher;
        this.password = password;
    }
}
