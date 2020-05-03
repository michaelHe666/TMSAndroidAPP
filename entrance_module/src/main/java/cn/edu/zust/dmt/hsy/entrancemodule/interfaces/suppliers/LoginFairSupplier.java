package cn.edu.zust.dmt.hsy.entrancemodule.interfaces.suppliers;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.suppliers.BaseFairSupplier;
import cn.edu.zust.dmt.hsy.mybaselibrary.models.BaseModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/25/2020 20:16
 **/
public interface LoginFairSupplier<T extends BaseModel> extends BaseFairSupplier<T> {
    @NonNull
    String getVoucher();

    @NonNull
    String getToken();
}
