package cn.edu.zust.dmt.hsy.my_base_library.models.remote.response;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/23/2020 16:16
 **/
public final class BaseNetworkResponse<T extends BaseResponseModel> {
    private int returnCode;
    private String message;
    private T data;

    public BaseNetworkResponse(int returnCode, String message, T data) {
        this.returnCode = returnCode;
        this.message = message;
        this.data = data;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
