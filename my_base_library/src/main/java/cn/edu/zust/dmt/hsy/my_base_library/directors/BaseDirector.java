package cn.edu.zust.dmt.hsy.my_base_library.directors;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.BaseDirectorListener;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseContainerListener;

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
        private final WeakReference<BaseContainerListener> mViewWeakReference;
        private final WeakReference<K> mDirectorWeakReference;

        protected SafeDirectorInnerClass(@NonNull final BaseContainerListener baseContainerListener
                , @NonNull final K baseDirectorListener) {
            mViewWeakReference = new WeakReference<>(baseContainerListener);
            mDirectorWeakReference = new WeakReference<>(baseDirectorListener);
        }

        protected final BaseContainerListener getSafeContainer() {
            final BaseContainerListener baseContainerListener = mViewWeakReference.get();
            final K baseDirectorListener = mDirectorWeakReference.get();
            if (baseContainerListener == null || baseDirectorListener == null) {
                throw new NullPointerException("BaseViewListener or BaseDirectorListener no longer exist!");
            } else {
                return baseContainerListener;
            }
        }

        protected final K getSafeDirector() {
            final BaseContainerListener baseContainerListener = mViewWeakReference.get();
            final K baseDirectorListener = mDirectorWeakReference.get();
            if (baseContainerListener == null || baseDirectorListener == null) {
                throw new NullPointerException("BaseViewListener or BaseDirectorListener no longer exist!");
            } else {
                return baseDirectorListener;
            }
        }
    }

    /**
     * @param baseContainerListener        base view listener
     * @param currentDirectorListener specialized view listener
     */
    public abstract void loadActors(@NonNull BaseContainerListener baseContainerListener
            , @NonNull T currentDirectorListener);
}
