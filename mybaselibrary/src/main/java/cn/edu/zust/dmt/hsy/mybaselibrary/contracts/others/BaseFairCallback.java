package cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.models.BaseModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/24/2020 14:31
 **/
public interface BaseFairCallback<T extends BaseModel> {
    /**
     * @param response data returned by fair
     */
    void onModel(@NonNull T response);

    /**
     * @param e possible error throw by fair
     */
    void onError(@NonNull Throwable e);

    /**
     * @description final action for callback
     */
    void onComplete();
}
