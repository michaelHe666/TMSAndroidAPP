package cn.edu.zust.dmt.hsy.my_base_library.constants;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/24/2020 15:37
 **/
public final class MyAppSettings {
    /**
     * @description {@link MyAppSettings} should not be initialized
     */
    private MyAppSettings() {
        throw new AssertionError();
    }

    public static final String HTTP_URL = "http://192.168.0.103:3030";
    public static final String HTTPS_URL = "https://192.168.43.17:3001";
}
