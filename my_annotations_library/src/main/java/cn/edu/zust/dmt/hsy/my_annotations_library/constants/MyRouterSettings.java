package cn.edu.zust.dmt.hsy.my_annotations_library.constants;

import cn.edu.zust.dmt.hsy.my_annotations_library.interfaces.IMyRouterRecorder;

/**
 * @author MR.M
 * @projectName TMS
 * @description $
 * @time 4/12/2020 14:00
 **/
public final class MyRouterSettings {
    public final String SUPPORTED_CLASS_PATH = "cn.edu.zust.dmt.hsy.my_base_library.containers.activities.BaseActivity";
    public final String GENERATED_FILE_PATH = "cn.edu.zust.dmt.hsy.my_router_recorder";
    public final String GENERATED_INTERFACE_NAME = IMyRouterRecorder.class.getCanonicalName();
    public final String GENERATED_METHOD_NAME = IMyRouterRecorder.class.getMethods()[0].getName();
}
