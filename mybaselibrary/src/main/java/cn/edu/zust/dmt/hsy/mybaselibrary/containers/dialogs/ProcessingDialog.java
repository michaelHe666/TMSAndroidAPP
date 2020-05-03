package cn.edu.zust.dmt.hsy.mybaselibrary.containers.dialogs;

import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.R;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners.ProcessingDirectorListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors.ProcessingDirector;

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
        public TextView getHintPresenter() {
            return getWeakReferenceView(R.id.mbl_dialog_processing_text);
        }
    }

    @Override
    protected int getViewRId() {
        return R.layout.mbl_dialog_processing;
    }

    @Override
    protected void loadDirectorsToView() {
        new ProcessingDirector().loadActors(getBaseViewListener()
                , new MyProcessingDirectorListener(this));
    }
}
