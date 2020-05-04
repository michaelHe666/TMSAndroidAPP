package cn.edu.zust.dmt.hsy.my_processors_library.utils;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/11/2020 10:26
 **/
public final class MyErrorUtils {
    /**
     * @description {@link MyErrorUtils} should not be initialized
     */
    private MyErrorUtils() {
        throw new AssertionError();
    }

    /**
     * @param hint message to show
     * @throws IllegalArgumentException for locate exception code
     */
    public static void showMyArgumentException(String hint) throws IllegalArgumentException {
        IllegalArgumentException e = new IllegalArgumentException(hint);
        e.printStackTrace();
        throw e;
    }

    /**
     * @param hint message to show
     * @throws NullPointerException for locate exception code
     */
    public static void showMyNullPointerException(String hint) throws NullPointerException {
        NullPointerException e = new NullPointerException(hint);
        e.printStackTrace();
        throw e;
    }
}
