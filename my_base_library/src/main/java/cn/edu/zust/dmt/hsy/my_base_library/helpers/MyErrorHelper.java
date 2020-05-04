package cn.edu.zust.dmt.hsy.my_base_library.helpers;

import androidx.annotation.NonNull;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/12/2020 14:53
 **/
public final class MyErrorHelper {
    /**
     * @description ensure {@link MyErrorHelper} should not be instantiated
     */
    private MyErrorHelper() {
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
