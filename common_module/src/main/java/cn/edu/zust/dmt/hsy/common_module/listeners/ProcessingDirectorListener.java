package cn.edu.zust.dmt.hsy.common_module.listeners;

import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseDirectorListener;

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
