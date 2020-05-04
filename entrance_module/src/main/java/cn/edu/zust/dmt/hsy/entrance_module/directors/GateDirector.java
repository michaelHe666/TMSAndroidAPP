package cn.edu.zust.dmt.hsy.entrance_module.directors;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrance_module.interfaces.listeners.GateDirectorListener;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.constants.MyExtrasConstants;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.others.BaseContainerListener;
import cn.edu.zust.dmt.hsy.my_base_library.directors.BaseDirector;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/5/2020 21:52
 **/
public final class GateDirector extends BaseDirector<GateDirectorListener> {
    /**
     * @param baseContainerListener     base view listener
     * @param gateDirectorListener current specialized view listener
     * @description load actors to view holder
     */
    public void loadActors(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final GateDirectorListener gateDirectorListener) {
        setLoginRouterView(baseContainerListener, gateDirectorListener);
        setRegisterRouterView(baseContainerListener, gateDirectorListener);
    }

    /**
     * @description set how to trigger login router event on view
     */
    private void setLoginRouterView(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final GateDirectorListener gateDirectorListener) {
        gateDirectorListener.getLoginRouterView().setOnClickListener(
                new MyLoginRouterViewListener(baseContainerListener, gateDirectorListener));
    }

    /**
     * @description set how to trigger register router event on view
     */
    private void setRegisterRouterView(@NonNull final BaseContainerListener baseContainerListener
            , @NonNull final GateDirectorListener gateDirectorListener) {
        gateDirectorListener.getRegisterRouterView().setOnClickListener(
                new MyRegisterRouterViewListener(baseContainerListener, gateDirectorListener));
    }

    private static final class MyLoginRouterViewListener extends SafeDirectorInnerClass<GateDirectorListener>
            implements View.OnClickListener {
        private MyLoginRouterViewListener(@NonNull final BaseContainerListener baseContainerListener
                , @NonNull final GateDirectorListener baseDirectorListener) {
            super(baseContainerListener, baseDirectorListener);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(MyExtrasConstants.TAG_UNIVERSE_FRAGMENT)
                    , String.valueOf(MyExtrasConstants.VALUE_IDENTITY_LOGIN));
            getSafeContainer().callMyRouter(MyRouterPaths.IDENTITY_PATH, bundle);
        }
    }

    private static final class MyRegisterRouterViewListener extends SafeDirectorInnerClass<GateDirectorListener>
            implements View.OnClickListener {
        private MyRegisterRouterViewListener(@NonNull final BaseContainerListener baseContainerListener
                , @NonNull final GateDirectorListener baseDirectorListener) {
            super(baseContainerListener, baseDirectorListener);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(MyExtrasConstants.TAG_UNIVERSE_FRAGMENT)
                    , String.valueOf(MyExtrasConstants.VALUE_IDENTITY_REGISTER));
            getSafeContainer().callMyRouter(MyRouterPaths.IDENTITY_PATH, bundle);
        }
    }
}
