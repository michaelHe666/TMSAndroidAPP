package cn.edu.zust.dmt.hsy.main_module.datas.local;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.my_base_library.datas.local.BaseLocalData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description special data model for
 * {@link cn.edu.zust.dmt.hsy.main_module.views.adapters.ConversationBarAdapter}
 * @since 6/12/2020 10:16
 **/
public final class MyConversationInfo implements BaseLocalData {
    /**
     * @description {@link Enum} for describe state of current {@link MyConversationInfo}
     */
    public enum MyConversationState {
        NORMAL,
        TOP
    }

    @NonNull
    private Drawable mPhotoDrawable;
    @NonNull
    private String mTitleString;
    @NonNull
    private MyConversationState mState;
    @NonNull
    private String mHint;

    /**
     * @description {@link MyConversationInfo} is not supposed to be initialized by this method
     */
    private MyConversationInfo() {
        throw new AssertionError();
    }

    /**
     * @description public method for initialize {@link MyConversationInfo}
     */
    public MyConversationInfo(@NonNull final Drawable photoDrawable, @NonNull final String titleString
            , @NonNull final MyConversationState state, @NonNull final String hint) {
        mPhotoDrawable = photoDrawable;
        mTitleString = titleString;
        mState = state;
        mHint = hint;
    }

    @NonNull
    public Drawable getPhotoDrawable() {
        return mPhotoDrawable;
    }

    public void setPhotoDrawable(@NonNull Drawable photoDrawable) {
        mPhotoDrawable = photoDrawable;
    }

    @NonNull
    public String getTitleString() {
        return mTitleString;
    }

    public void setTitleString(@NonNull String titleString) {
        mTitleString = titleString;
    }

    @NonNull
    public MyConversationState getState() {
        return mState;
    }

    public void setState(@NonNull MyConversationState state) {
        mState = state;
    }

    @NonNull
    public String getHint() {
        return mHint;
    }

    public void setHint(@NonNull String hint) {
        mHint = hint;
    }
}
