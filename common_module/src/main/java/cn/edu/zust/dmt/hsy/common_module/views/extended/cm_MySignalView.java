package cn.edu.zust.dmt.hsy.common_module.views.extended;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/2/2020 20:04
 **/
public final class cm_MySignalView extends AppCompatImageView {

    public cm_MySignalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MySignalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes(context, attrs);
        setScaleType(ScaleType.CENTER_CROP);
    }

    /**
     * @description initialize {@link cm_MySignalView} attrs to view
     */
    private void initializeAttributes(@NonNull final Context context, @NonNull final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MySignalView);
        setCurrentSignal(typedArray.getInt(R.styleable.cm_MySignalView_cm_mySignalLight, -1));

        typedArray.recycle();
    }

    /**
     * @param signalValue tag for signal
     */
    public void setCurrentSignal(final int signalValue) {
        switch (signalValue) {
            case 0:
                setImageResource(R.drawable.cm_icon_universal_green_ball);
                break;
            case 1:
                setImageResource(R.drawable.cm_icon_universal_red_ball);
                break;
        }
    }
}
