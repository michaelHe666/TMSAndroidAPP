package cn.edu.zust.dmt.hsy.common_module.views.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/3/2020 13:09
 **/
public final class cm_MyOptionButton extends ConstraintLayout {
    private CardView mCardView;
    private ImageView mImageView;
    private TextView mTextView;

    public cm_MyOptionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyOptionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
        initializeAttributes(context, attrs);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindViews(@NonNull final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cm_views_combined_my_option_button, this);
        mCardView = view.findViewById(R.id.cm_views_combined_my_option_button_card_view);
        mImageView = view.findViewById(R.id.cm_views_combined_my_option_button_image_view);
        mTextView = view.findViewById(R.id.cm_views_combined_my_option_button_text_view);
    }

    /**
     * @description initialize {@link cm_MyFormBar} attrs to view
     */
    private void initializeAttributes(@NonNull final Context context, @NonNull final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MyOptionButton);
        mCardView.setCardBackgroundColor(typedArray.getColor(
                R.styleable.cm_MyOptionButton_cm_myOptionColor, 0));
        mImageView.setImageResource(typedArray.getResourceId(
                R.styleable.cm_MyOptionButton_cm_myOptionIcon, 0));
        mTextView.setText(typedArray.getText(R.styleable.cm_MyOptionButton_cm_myOptionText));
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimensionPixelSize(
                R.styleable.cm_MyOptionButton_cm_myOptionTextSize, 0));
        typedArray.recycle();
    }
}
