package cn.edu.zust.dmt.hsy.myannotationslibrary.constants;

import cn.edu.zust.dmt.hsy.myannotationslibrary.interfaces.IMyRouterRecorder;

/**
 * @author MR.M
 * @projectName TMS
 * @packageName cn.edu.zust.dmt.hsy.myannotationslibrary
 * @description $
 * @time 4/12/2020 14:00
 **/
public final class MyRouterSettings {
    public final String SUPPORTED_CLASS_PATH = "cn.edu.zust.dmt.hsy.mybaselibrary.containers.activities.BaseActivity";
    public final String GENERATED_FILE_PATH = "cn.edu.zust.dmt.hsy.myrouterrecorder";
    public final String GENERATED_INTERFACE_NAME = IMyRouterRecorder.class.getCanonicalName();
    public final String GENERATED_METHOD_NAME = IMyRouterRecorder.class.getMethods()[0].getName();
}
