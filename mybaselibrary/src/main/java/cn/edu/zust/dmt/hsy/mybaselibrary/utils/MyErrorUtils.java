package cn.edu.zust.dmt.hsy.mybaselibrary.utils;

import androidx.annotation.NonNull;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/12/2020 14:53
 **/
public final class MyErrorUtils {
    /**
     * @description ensure {@link MyErrorUtils} should not be instantiated
     */
    private MyErrorUtils() {
        throw new AssertionError();
    }

    /**
     * @param hint message to show
     * @throws NullPointerException for locate exception code
     */
    public static void showMyNullPointerException(@NonNull String hint) throws NullPointerException {
        NullPointerException e = new NullPointerException(hint);
        e.printStackTrace();
        throw e;
    }

    /**
     * @param hint message to show
     * @throws IllegalArgumentException for locate exception code
     */
    public static void showMyArgumentException(@NonNull String hint) throws IllegalArgumentException {
        IllegalArgumentException e = new IllegalArgumentException(hint);
        e.printStackTrace();
        throw e;
    }
}
