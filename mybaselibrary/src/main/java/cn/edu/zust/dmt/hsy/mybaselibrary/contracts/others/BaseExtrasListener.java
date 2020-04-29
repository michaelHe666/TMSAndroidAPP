package cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others;

import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/20/2020 14:48
 **/
public interface BaseExtrasListener {
    /**
     * @param myExtras info set as {@link Bundle} for parser
     */
    void parseMyExtras(@NonNull Bundle myExtras);
}
