package cn.edu.zust.dmt.hsy.myannotationslibrary.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;

/**
 * @author MR.M
 * @projectName TMS
 * @packageName cn.edu.zust.dmt.hsy.myannotationslibrary
 * @description $
 * @time 4/10/2020 20:42
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface MyRouter {
    /**
     * @description url path in router list
     */
    MyRouterPaths path();
}
