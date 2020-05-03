package cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors;

import android.os.Bundle;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.mybaselibrary.constants.MyExtraConstants;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners.ProcessingDirectorListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseExtrasListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseViewListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.utils.MyErrorUtils;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/27/2020 14:21
 **/
public final class ProcessingDirector extends BaseDirector<ProcessingDirectorListener> {
    @Override
    public void loadActors(@NonNull final BaseViewListener baseViewListener
            , @NonNull final ProcessingDirectorListener currentDirectorListener) {
        setMyExtrasParser(baseViewListener, currentDirectorListener);
    }

    private void setMyExtrasParser(@NonNull final BaseViewListener baseViewListener
            , @NonNull final ProcessingDirectorListener currentDirectorListener) {
        baseViewListener.addMyExtrasParser(new MyExtrasParser(baseViewListener, currentDirectorListener));
    }

    private static final class MyExtrasParser extends SafeDirectorInnerClass<ProcessingDirectorListener>
            implements BaseExtrasListener {
        private MyExtrasParser(@NonNull final BaseViewListener baseViewListener
                , @NonNull final ProcessingDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
        }

        @Override
        public void parseMyExtras(@NonNull Bundle myExtras) {
            final String hintString = myExtras.getString(String.valueOf(MyExtraConstants.TAG_PROCESSING_HINT));
            if (hintString == null) {
                MyErrorUtils.showMyNullPointerException("Hint string no longer exist!");
            } else {
                getDirectorWeakReference().getHintPresenter().setText(hintString);
            }
        }
    }
}
