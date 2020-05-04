package cn.edu.zust.dmt.hsy.my_annotations_library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;

/**
 * @author MR.M
 * @projectName TMS
 * @description $
 * @time 4/10/2020 20:42
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MyRouter {
    /**
     * @description url path in router list
     */
    MyRouterPaths path();
}
