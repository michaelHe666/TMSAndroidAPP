package cn.edu.zust.dmt.hsy.mybaselibrary.contracts.suppliers;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others.BaseFairCallback;
import cn.edu.zust.dmt.hsy.mybaselibrary.models.BaseModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/26/2020 21:50
 **/
public interface BaseFairSupplier<T extends BaseModel> {
    @NonNull
    BaseFairCallback<T> getBaseFairCallback();
}
