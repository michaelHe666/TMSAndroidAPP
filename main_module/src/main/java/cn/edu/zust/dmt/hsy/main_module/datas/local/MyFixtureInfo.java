package cn.edu.zust.dmt.hsy.main_module.datas.local;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.views.extended.cm_MySignalView;
import cn.edu.zust.dmt.hsy.my_base_library.datas.local.BaseLocalData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/3/2020 9:38
 **/
public final class MyFixtureInfo implements BaseLocalData {
    private String mName = "";
    private String mDeadline = "";
    /**
     * @description get state meaning by {@link cm_MySignalView}
     */
    private int mLocationState = -1;
    private int mRiskState = -1;

    public MyFixtureInfo(@NonNull final String name, @NonNull final String deadline, final int locationState,
                         final int riskState) {
        mName = name;
        mDeadline = deadline;
        mLocationState = locationState;
        mRiskState = riskState;
    }

    public String getName() {
        return mName;
    }

    public void setName(@NonNull final String name) {
        mName = name;
    }

    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(@NonNull final String deadline) {
        mDeadline = deadline;
    }

    public int getLocationState() {
        return mLocationState;
    }

    public void setLocationState(final int locationState) {
        mLocationState = locationState;
    }

    public int getRiskState() {
        return mRiskState;
    }

    public void setRiskState(final int riskState) {
        mRiskState = riskState;
    }
}
