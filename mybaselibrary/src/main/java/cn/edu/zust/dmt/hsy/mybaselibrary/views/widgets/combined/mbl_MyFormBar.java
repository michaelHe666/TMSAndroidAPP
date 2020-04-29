package cn.edu.zust.dmt.hsy.mybaselibrary.views.widgets.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.zust.dmt.hsy.mybaselibrary.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/7/2020 16:42
 **/
public final class mbl_MyFormBar extends ConstraintLayout {

    /**
     * @description member views
     */
    private TextView mTextView = null;
    private EditText mEditText = null;

    private int mTextSize = 40;

    public mbl_MyFormBar(@NonNull Context context) {
        super(context);
        bindViews(context);
        initializeViews(context, null);
    }

    public mbl_MyFormBar(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        bindViews(context);
        initializeViews(context, attrs);
    }

    public mbl_MyFormBar(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
        initializeViews(context, attrs);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindViews(@NonNull final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.mbl_widgets_combined_my_form_bar, this);
        mEditText = view.findViewById(R.id.mbl_widgets_my_form_bar_edit_text);
        mTextView = view.findViewById(R.id.mbl_widgets_my_form_bar_text_view);
    }

    /**
     * @description initialize MyFormBar attrs
     */
    private void initializeViews(@NonNull final Context context, @Nullable final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.mbl_MyFormBar);

        final String titleString = typedArray.getString(R.styleable.mbl_MyFormBar_mbl_myFormBarTitle);
        final String hintString = typedArray.getString(R.styleable.mbl_MyFormBar_mbl_myFormBarHint);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.mbl_MyFormBar_mbl_myFormBarTextSize, mTextSize);

        mTextView.setText(titleString);
        mEditText.setHint(hintString);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);

        typedArray.recycle();
    }

    public void setBarContentWatcher(@NonNull final TextWatcher textWatcher) {
        mEditText.addTextChangedListener(textWatcher);
    }

    public String getBarContent() {
        return mEditText.getText().toString();
    }

    public void setBarContent(@NonNull final String content) {
        mEditText.setText(content);
    }
}
