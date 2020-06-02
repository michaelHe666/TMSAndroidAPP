package cn.edu.zust.dmt.hsy.common_module.views.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/2/2020 20:04
 **/
public final class cm_MySignalLight extends ConstraintLayout {

    private ImageView mSignalImageView = null;

    /**
     * @description store state of current presenting signal, -1 for not initialized
     */
    private int mCurrentSignal = -1;

    public cm_MySignalLight(Context context) {
        super(context);
        bindView(context);
        initializeViews(context, null);
    }

    public cm_MySignalLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        bindView(context);
        initializeViews(context, attrs);
    }

    public cm_MySignalLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindView(context);
        initializeViews(context, attrs);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindView(@NonNull final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cm_views_combined_my_signal_light, this);
        mSignalImageView = view.findViewById(R.id.cm_view_combined_my_signal_light_image_view);
    }

    /**
     * @description initialize {@link cm_MySignalLight} attrs to view
     */
    private void initializeViews(@NonNull final Context context, @Nullable final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MySignalLight);
        setCurrentSignal(typedArray.getInt(R.styleable.cm_MySignalLight_cm_mySignalLight, -1));

        typedArray.recycle();
    }

    /**
     * @param signalValue tag for signal
     */
    protected void setCurrentSignal(final int signalValue) {
        switch (signalValue) {
            case 0:
                mSignalImageView.setImageResource(R.drawable.cm_icon_universal_green_ball);
                break;
            case 1:
                mSignalImageView.setImageResource(R.drawable.cm_icon_universal_red_ball);
                break;
        }
    }
}
