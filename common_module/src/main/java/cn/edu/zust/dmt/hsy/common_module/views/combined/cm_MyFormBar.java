package cn.edu.zust.dmt.hsy.common_module.views.combined;

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

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 4/7/2020 16:42
 **/
public final class cm_MyFormBar extends ConstraintLayout {

    /**
     * @description member views
     */
    private TextView mTextView = null;
    private EditText mEditText = null;

    private int mTextSize = 40;

    public cm_MyFormBar(@NonNull Context context) {
        super(context);
        bindViews(context);
        initializeViews(context, null);
    }

    public cm_MyFormBar(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        bindViews(context);
        initializeViews(context, attrs);
    }

    public cm_MyFormBar(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
        initializeViews(context, attrs);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindViews(@NonNull final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cm_views_combined_my_form_bar, this);
        mEditText = view.findViewById(R.id.cm_views_my_form_bar_edit_text);
        mTextView = view.findViewById(R.id.cm_views_my_form_bar_text_view);
    }

    /**
     * @description initialize {@link cm_MyFormBar} attrs to view
     */
    private void initializeViews(@NonNull final Context context, @Nullable final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MyFormBar);

        final String titleString = typedArray.getString(R.styleable.cm_MyFormBar_cm_myFormBarTitle);
        final String hintString = typedArray.getString(R.styleable.cm_MyFormBar_cm_myFormBarHint);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.cm_MyFormBar_cm_myFormBarTextSize, mTextSize);

        mTextView.setText(titleString);
        mEditText.setHint(hintString);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);

        typedArray.recycle();
    }

    /**
     * @param textWatcher watcher for {@link #mEditText}
     */
    public void setContentWatcher(@NonNull final TextWatcher textWatcher) {
        mEditText.addTextChangedListener(textWatcher);
    }

    /**
     * @param content string for {@link #mEditText}
     */
    public void setContent(@NonNull final String content) {
        mEditText.setText(content);
    }
}
