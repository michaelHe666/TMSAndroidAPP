package cn.edu.zust.dmt.hsy.common_module.views.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
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
     * @description {@link cm_MyFormBar} title view
     */
    private TextView mTextView = null;
    /**
     * @description {@link cm_MyFormBar} content view
     */
    private EditText mEditText = null;

    /**
     * @description enum the input types of {@link #mEditText}
     */
    public enum MyFormBarInputType {
        PLAIN_TEXT,
        PASSWORD,
    }

    public cm_MyFormBar(@NonNull final Context context) {
        this(context, null);
    }

    public cm_MyFormBar(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyFormBar(@NonNull final Context context, @Nullable final AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
        initializeAttributes(context, attrs);
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
    private void initializeAttributes(@NonNull final Context context
            , @Nullable final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MyFormBar);
        mTextView.setText(typedArray.getString(R.styleable.cm_MyFormBar_cm_myFormBarTitle));
        mEditText.setHint(typedArray.getString(R.styleable.cm_MyFormBar_cm_myFormBarHint));
        switch (typedArray.getInteger(R.styleable.cm_MyFormBar_cm_myFormBarInputType, 0)) {
            case 0:
                setInputType(MyFormBarInputType.PLAIN_TEXT);
                break;
            case 1:
                setInputType(MyFormBarInputType.PASSWORD);
                break;
        }
        typedArray.recycle();
    }

    /**
     * @param textWatcher watcher for {@link #mEditText}
     */
    public void setContentChangedWatcher(@NonNull final TextWatcher textWatcher) {
        mEditText.addTextChangedListener(textWatcher);
    }

    /**
     * @param content string for {@link #mEditText}
     */
    public void setContent(@NonNull final String content) {
        mEditText.setText(content);
    }

    /**
     * @return content of {@link #mEditText}
     */
    public String getContent() {
        return mEditText.getText().toString();
    }

    /**
     * @param inputType for {@link #mEditText#setInputType(int)}
     */
    public void setInputType(@NonNull final MyFormBarInputType inputType) {
        switch (inputType) {
            case PLAIN_TEXT:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case PASSWORD:
                mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                break;
        }
    }
}
