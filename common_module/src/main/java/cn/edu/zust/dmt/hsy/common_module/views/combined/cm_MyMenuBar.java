package cn.edu.zust.dmt.hsy.common_module.views.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/3/2020 13:51
 **/
public class cm_MyMenuBar extends ConstraintLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public cm_MyMenuBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyMenuBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
        initializedAttributes(context, attrs);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindViews(@NonNull final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cm_views_combined_my_menu_bar, this);
        mImageView = view.findViewById(R.id.cm_views_combined_my_menu_bar_image_view);
        mTextView = view.findViewById(R.id.cm_views_combined_my_menu_bar_text_view);
    }

    /**
     * @description initialize {@link cm_MyFormBar} attrs to view
     */
    private void initializedAttributes(@NonNull final Context context, @NonNull final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MyMenuBar);
        mTextView.setText(typedArray.getString(R.styleable.cm_MyMenuBar_cm_myMenuText));
        mImageView.setImageResource(typedArray.getResourceId(
                R.styleable.cm_MyMenuBar_cm_myMenuIcon, 0));
        typedArray.recycle();
    }
}
