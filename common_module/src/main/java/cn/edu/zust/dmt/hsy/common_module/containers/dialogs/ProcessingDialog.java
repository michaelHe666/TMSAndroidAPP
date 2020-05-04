package cn.edu.zust.dmt.hsy.common_module.containers.dialogs;

import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.R;
import cn.edu.zust.dmt.hsy.my_base_library.containers.dialogs.BaseDialog;
import cn.edu.zust.dmt.hsy.common_module.listeners.ProcessingDirectorListener;
import cn.edu.zust.dmt.hsy.common_module.directors.ProcessingDirector;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/19/2020 15:35
 **/
public final class ProcessingDialog extends BaseDialog {
    private static final class MyProcessingDirectorListener extends BaseDialogDirectorListener
            implements ProcessingDirectorListener {
        private MyProcessingDirectorListener(@NonNull BaseDialog baseDialog) {
            super(baseDialog);
        }

        @NonNull
        @Override
        public TextView getHintTextView() {
            return getWeakReferenceView(R.id.cm_dialog_processing_text);
        }
    }

    @Override
    protected int getViewRId() {
        return R.layout.cm_dialog_processing;
    }

    @Override
    protected void loadDirectorsToView() {
        new ProcessingDirector().loadActors(getBaseContainerListener()
                , new MyProcessingDirectorListener(this));
    }
}
