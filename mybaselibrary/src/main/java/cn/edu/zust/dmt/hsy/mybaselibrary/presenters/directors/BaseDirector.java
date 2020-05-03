package cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.listeners.BaseDirectorListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.interfaces.others.BaseViewListener;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/26/2020 14:09
 **/
public abstract class BaseDirector<T extends BaseDirectorListener> {
    /**
     * @param <K> type of {@link BaseDirectorListener} of {@link BaseDirector}
     * @description help child to create a cache-safe class
     */
    protected static abstract class SafeDirectorInnerClass<K extends BaseDirectorListener> {
        private final WeakReference<BaseViewListener> mViewWeakReference;
        private final WeakReference<K> mDirectorWeakReference;

        protected SafeDirectorInnerClass(@NonNull final BaseViewListener baseViewListener
                , @NonNull final K baseDirectorListener) {
            mViewWeakReference = new WeakReference<>(baseViewListener);
            mDirectorWeakReference = new WeakReference<>(baseDirectorListener);
        }

        protected final BaseViewListener getViewWeakReference() {
            final BaseViewListener baseViewListener = mViewWeakReference.get();
            final K baseDirectorListener = mDirectorWeakReference.get();
            if (baseViewListener == null || baseDirectorListener == null) {
                throw new NullPointerException("BaseViewListener or BaseDirectorListener no longer exist!");
            } else {
                return baseViewListener;
            }
        }

        protected final K getDirectorWeakReference() {
            final BaseViewListener baseViewListener = mViewWeakReference.get();
            final K baseDirectorListener = mDirectorWeakReference.get();
            if (baseViewListener == null || baseDirectorListener == null) {
                throw new NullPointerException("BaseViewListener or BaseDirectorListener no longer exist!");
            } else {
                return baseDirectorListener;
            }
        }
    }

    /**
     * @param baseViewListener        base view listener
     * @param currentDirectorListener specialized view listener
     */
    public abstract void loadActors(@NonNull BaseViewListener baseViewListener
            , @NonNull T currentDirectorListener);
}
