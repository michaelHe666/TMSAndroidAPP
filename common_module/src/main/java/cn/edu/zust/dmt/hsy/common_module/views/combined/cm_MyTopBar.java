package cn.edu.zust.dmt.hsy.common_module.views.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * <p>
 * copyright(c) all rights reserved by MR.M
 * @since 4/6/2020 12:20
 **/
public final class cm_MyTopBar extends ConstraintLayout {

    /**
     * @description member views
     */
    private ImageButton mLeftButton = null;
    private ImageButton mRightButton = null;
    private TextView mCenterTextView = null;

    private int mLeftButtonRID = 0;
    private int mRightButtonRID = 0;

    public cm_MyTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
        initializeAttributes(context, attrs);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cm_views_combined_my_top_bar, this);
        mLeftButton = view.findViewById(R.id.cm_views_my_top_bar_left_image_button);
        mCenterTextView = view.findViewById(R.id.cm_views_my_top_bar_center_text_view);
        mRightButton = view.findViewById(R.id.cm_views_my_top_bar_right_image_button);
    }

    /**
     * @description initialize {@link cm_MyTopBar} attrs to view
     */
    private void initializeAttributes(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MyTopBar);

        final String titleString = typedArray.getString(R.styleable.cm_MyTopBar_cm_myTopBarTitle);
        final int leftButtonRID = typedArray
                .getResourceId(R.styleable.cm_MyTopBar_cm_myTopBarLeftIcon, 0);
        final int rightButtonRID = typedArray
                .getResourceId(R.styleable.cm_MyTopBar_cm_myTopBarRightIcon, 0);

        if (rightButtonRID != 0) {
            showRightButton(rightButtonRID);
        } else {
            hideRightButton();
        }

        if (leftButtonRID != 0) {
            showLeftButton(leftButtonRID);
        } else {
            hideLeftButton();
        }

        if (titleString != null) {
            setTitle(titleString);
        }

        typedArray.recycle();
    }

    /**
     * @param onClickListener left button listener interface
     */
    public void setLeftButtonOnClickListener(@NonNull OnClickListener onClickListener) {
        mLeftButton.setOnClickListener(onClickListener);
    }

    /**
     * @param onClickListener right button listener interface
     */
    public void setRightButtonOnClickListener(@NonNull OnClickListener onClickListener) {
        mRightButton.setOnClickListener(onClickListener);
    }

    /**
     * @param leftButtonRID RID for {@link #mLeftButton}
     * @description show button and enable {@link OnClickListener}
     */
    public void showLeftButton(@DrawableRes int leftButtonRID) {
        if (mLeftButtonRID != leftButtonRID) {
            mLeftButtonRID = leftButtonRID;
            mLeftButton.setImageResource(mLeftButtonRID);
        }
        mLeftButton.setClickable(true);
        mLeftButton.setVisibility(View.VISIBLE);
    }

    /**
     * @description hide button and disable {@link OnClickListener}
     */
    public void hideLeftButton() {
        mLeftButton.setVisibility(View.GONE);
        mLeftButton.setClickable(false);
    }

    /**
     * @param rightButtonRID show right icon button by RID
     */
    public void showRightButton(@DrawableRes int rightButtonRID) {
        if (mRightButtonRID != rightButtonRID) {
            mRightButtonRID = rightButtonRID;
            mRightButton.setImageResource(mRightButtonRID);
        }
        mRightButton.setClickable(true);
        mRightButton.setVisibility(View.VISIBLE);
    }

    /**
     * @description hide button and disable {@link OnClickListener}
     */
    public void hideRightButton() {
        mRightButton.setVisibility(View.GONE);
        mRightButton.setClickable(false);
    }

    /**
     * @param stringRID resources id for title string
     */
    public void setTitle(@StringRes final int stringRID) {
        String temp = getResources().getString(stringRID);
        setTitle(temp);
    }

    /**
     * @param string resources id for title string
     */
    public void setTitle(@NonNull final String string) {
        if (!mCenterTextView.getText().equals(string)) {
            mCenterTextView.setText(string);
        }
    }
}
