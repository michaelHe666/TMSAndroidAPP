package cn.edu.zust.dmt.hsy.chat_module.data.local;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import cn.edu.zust.dmt.hsy.chat_module.views.customized.cm1_MyChatView;
import cn.edu.zust.dmt.hsy.my_base_library.datas.local.BaseLocalData;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/14/2020 19:01
 **/
public final class MyChatData implements BaseLocalData {
    @NonNull
    private Drawable mPhotoDrawable;
    @NonNull
    private String mNameString;
    @NonNull
    private String mTagString;
    @NonNull
    private String mWordsString;
    @NonNull
    private cm1_MyChatView.MyBubbleStyle mStyle;

    public MyChatData(@NonNull final Drawable photoDrawable, @NonNull final String nameString
            , @NonNull final String tagString, @NonNull final String wordsString
            , @NonNull final cm1_MyChatView.MyBubbleStyle style) {
        mPhotoDrawable = photoDrawable;
        mNameString = nameString;
        mTagString = tagString;
        mWordsString = wordsString;
        mStyle = style;
    }

    @NonNull
    public Drawable getPhotoDrawable() {
        return mPhotoDrawable;
    }

    public void setPhotoDrawable(@NonNull Drawable photoDrawable) {
        mPhotoDrawable = photoDrawable;
    }

    @NonNull
    public String getNameString() {
        return mNameString;
    }

    public void setNameString(@NonNull String nameString) {
        mNameString = nameString;
    }

    @NonNull
    public String getTagString() {
        return mTagString;
    }

    public void setTagString(@NonNull String tagString) {
        mTagString = tagString;
    }

    @NonNull
    public String getWordsString() {
        return mWordsString;
    }

    public void setWordsString(@NonNull String wordsString) {
        mWordsString = wordsString;
    }

    @NonNull
    public cm1_MyChatView.MyBubbleStyle getStyle() {
        return mStyle;
    }

    public void setStyle(@NonNull cm1_MyChatView.MyBubbleStyle style) {
        mStyle = style;
    }
}
