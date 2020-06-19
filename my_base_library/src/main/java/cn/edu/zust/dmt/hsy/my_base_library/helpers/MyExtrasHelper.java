package cn.edu.zust.dmt.hsy.my_base_library.helpers;

import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/11/2020 10:14
 **/
public final class MyExtrasHelper {
    /**
     * @description ensure {@link MyExtrasHelper} should not be instantiated
     */
    private MyExtrasHelper() {
        throw new AssertionError();
    }

    /**
     * @description {@link Bundle} wrapper with builder pattern to help build instance
     */
    public static final class MyExtras {
        private Bundle mBundle;

        /**
         * @description should only be called in {@link #buildMyExtras(String, String)}
         */
        private MyExtras() {
            mBundle = new Bundle();
        }

        /**
         * @param key   key of extra info
         * @param value value of extra info
         * @return this as {@link MyExtras} instance
         */
        public MyExtras addExtra(@NonNull final String key, @NonNull final String value) {
            mBundle.putString(key, value);
            return this;
        }

        /**
         * @return wrapped {@link Bundle} inside {@link MyExtras}
         */
        @NonNull
        public Bundle getBundle() {
            return mBundle;
        }
    }

    /**
     * @param key   key of extra info
     * @param value value of extra info
     * @return new {@link MyExtras} instance
     */
    public static MyExtras buildMyExtras(@NonNull final String key, @NonNull final String value) {
        MyExtras extras = new MyExtras();
        extras.mBundle.putString(key, value);
        return extras;
    }

    /**
     * @return new {@link MyExtras} instance
     */
    public static MyExtras buildMyExtras() {
        return new MyExtras();
    }
}
