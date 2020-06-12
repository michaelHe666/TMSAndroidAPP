package cn.edu.zust.dmt.hsy.main_module.datas.local;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.common_module.views.extended.cm_MySignalView;
import cn.edu.zust.dmt.hsy.my_base_library.datas.local.BaseLocalData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description special data model for {@link cn.edu.zust.dmt.hsy.main_module.views.adapters.FixtureCardAdapter}
 * @since 6/3/2020 9:38
 **/
public final class MyFixtureInfo implements BaseLocalData {
    @NonNull
    private String mName;
    @NonNull
    private String mDeadline;
    /**
     * @description get state meaning by {@link cm_MySignalView}
     */
    private int mLocationState = -1;
    private int mRiskState = -1;

    /**
     * @description {@link MyFixtureInfo} is not supposed to be initialized by this method
     */
    private MyFixtureInfo() {
        throw new AssertionError();
    }

    /**
     * @description public method for initialize {@link MyFixtureInfo}
     */
    public MyFixtureInfo(@NonNull final String name, @NonNull final String deadline, final int locationState,
                         final int riskState) {
        mName = name;
        mDeadline = deadline;
        mLocationState = locationState;
        mRiskState = riskState;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }

    @NonNull
    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(@NonNull String deadline) {
        mDeadline = deadline;
    }

    public int getLocationState() {
        return mLocationState;
    }

    public void setLocationState(int locationState) {
        mLocationState = locationState;
    }

    public int getRiskState() {
        return mRiskState;
    }

    public void setRiskState(int riskState) {
        mRiskState = riskState;
    }
}
