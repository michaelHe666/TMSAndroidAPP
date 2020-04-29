package cn.edu.zust.dmt.hsy.entrancemodule.presenters.directors;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.entrancemodule.contracts.listeners.GateDirectorListener;
import cn.edu.zust.dmt.hsy.myannotationslibrary.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.mybaselibrary.constants.MyExtraConstants;
import cn.edu.zust.dmt.hsy.mybaselibrary.contracts.others.BaseViewListener;
import cn.edu.zust.dmt.hsy.mybaselibrary.presenters.directors.BaseDirector;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/5/2020 21:52
 **/
public final class GateDirector extends BaseDirector<GateDirectorListener> {
    /**
     * @param baseViewListener     base view listener
     * @param gateDirectorListener current specialized view listener
     * @description load actors to view holder
     */
    public void loadActors(@NonNull final BaseViewListener baseViewListener
            , @NonNull final GateDirectorListener gateDirectorListener) {
        setLoginRouterView(baseViewListener, gateDirectorListener);
        setRegisterRouterView(baseViewListener, gateDirectorListener);
    }

    /**
     * @description set how to trigger login router event on view
     */
    private void setLoginRouterView(@NonNull final BaseViewListener baseViewListener
            , @NonNull final GateDirectorListener gateDirectorListener) {
        gateDirectorListener.getLoginRouterView().setOnClickListener(
                new MyLoginRouterViewListener(baseViewListener, gateDirectorListener));
    }

    /**
     * @description set how to trigger register router event on view
     */
    private void setRegisterRouterView(@NonNull final BaseViewListener baseViewListener
            , @NonNull final GateDirectorListener gateDirectorListener) {
        gateDirectorListener.getRegisterRouterView().setOnClickListener(
                new MyRegisterRouterViewListener(baseViewListener, gateDirectorListener));
    }

    private static final class MyLoginRouterViewListener extends SafeDirectorListener<GateDirectorListener>
            implements View.OnClickListener {
        private MyLoginRouterViewListener(@NonNull final BaseViewListener baseViewListener
                , @NonNull final GateDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(MyExtraConstants.TAG_UNIVERSE_FRAGMENT)
                    , String.valueOf(MyExtraConstants.VALUE_IDENTITY_LOGIN));
            getViewWeakReference().callMyRouter(MyRouterPaths.IDENTITY_PATH, bundle);
        }
    }

    private static final class MyRegisterRouterViewListener extends SafeDirectorListener<GateDirectorListener>
            implements View.OnClickListener {
        private MyRegisterRouterViewListener(@NonNull final BaseViewListener baseViewListener
                , @NonNull final GateDirectorListener baseDirectorListener) {
            super(baseViewListener, baseDirectorListener);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(String.valueOf(MyExtraConstants.TAG_UNIVERSE_FRAGMENT)
                    , String.valueOf(MyExtraConstants.VALUE_IDENTITY_REGISTER));
            getViewWeakReference().callMyRouter(MyRouterPaths.IDENTITY_PATH, bundle);
        }
    }
}
