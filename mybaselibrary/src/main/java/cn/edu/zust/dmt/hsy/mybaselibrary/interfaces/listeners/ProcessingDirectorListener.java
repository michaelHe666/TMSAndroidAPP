package cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners;

import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/27/2020 14:21
 **/
public interface ProcessingDirectorListener extends BaseDirectorListener {
    @NonNull
    TextView getHintTextView();
}
