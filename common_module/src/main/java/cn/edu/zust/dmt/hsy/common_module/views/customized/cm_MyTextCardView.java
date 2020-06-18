package cn.edu.zust.dmt.hsy.common_module.views.customized;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.edu.zust.dmt.hsy.common_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/12/2020 13:28
 **/
public final class cm_MyTextCardView extends View {
    /**
     * @description text content of {@link cm_MyTextCardView}
     */
    private String mTextCharSequence;
    /**
     * @description color for {@link #mTextCharSequence}
     */
    private int mTextColor;
    /**
     * @description text size for {@link #mTextCharSequence}
     */
    private int mTextSize;
    /**
     * @description color for {@link cm_MyTextCardView} card background
     */
    private int mCardBackgroundColor;
    /**
     * @description corner radius for {@link cm_MyTextCardView} card background
     */
    private float mCardCornerRadius;

    /**
     * @description bounds for {@link #mTextCharSequence}
     */
    private Rect mTextRect;
    /**
     * @description {@link Paint} for {@link #mTextCharSequence}
     */
    private Paint mTextPaint;

    /**
     * @description bounds for {@link cm_MyTextCardView} background
     */
    private RectF mCardRectF;
    /**
     * @description {@link Paint} for {@link cm_MyTextCardView} background
     */
    private Paint mCardPaint;

    public cm_MyTextCardView(@NonNull Context context) {
        this(context, null);
    }

    public cm_MyTextCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyTextCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes(context, attrs, defStyleAttr);
    }

    /**
     * @description set values of {@link #mTextCharSequence,#mTextColor,#mTextSize} for text and
     * {@link #mCardBackgroundColor,#mCardCornerRadius} for card background
     */
    private void initializeAttributes(@NonNull final Context context, @Nullable final AttributeSet attributeSet
            , int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet
                , R.styleable.cm_MyTextCardView, defStyleAttr, 0);
        mTextCharSequence = typedArray.getString(R.styleable.cm_MyTextCardView_cm_myTextCardViewText);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.cm_MyTextCardView_cm_myTextCardViewTextSize
                , (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP
                        , 16, getResources().getDisplayMetrics())));
        mTextColor = typedArray.getColor(R.styleable.cm_MyTextCardView_cm_myTextCardViewTextColor
                , Color.BLACK);
        mCardBackgroundColor = typedArray.getColor(R.styleable.cm_MyTextCardView_cm_myTextCardViewCardColor
                , Color.WHITE);
        mCardCornerRadius = typedArray.getDimension(R.styleable.cm_MyTextCardView_cm_myTextCardViewCardRadius
                , 0);
        typedArray.recycle();
        preparePaintAndRect();
    }

    /**
     * @description initialize {@link #mTextPaint,#mTextRect,#mCardRectF,#mCardPaint}
     */
    private void preparePaintAndRect() {
        mTextRect = new Rect();

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.getTextBounds(mTextCharSequence, 0, mTextCharSequence.length(), mTextRect);

        mCardRectF = new RectF();

        mCardPaint = new Paint();
        mCardPaint.setFlags(Paint.FILTER_BITMAP_FLAG);
        mCardPaint.setAntiAlias(true);
        mCardPaint.setColor(mCardBackgroundColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;
        //calculate width size
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int desiredWidth = getPaddingLeft() + mTextRect.width() + getPaddingRight();
            width = Math.min(desiredWidth, widthSize);
        }
        //calculate height size
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int desiredHeight = getPaddingTop() + mTextRect.height() + getPaddingBottom();
            height = Math.min(desiredHeight, heightSize);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();

        mCardRectF.set(0, 0, width, height);
        canvas.drawRoundRect(mCardRectF, mCardCornerRadius, mCardCornerRadius, mCardPaint);

        mTextPaint.setColor(mTextColor);
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int baseline = (height - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top;
        canvas.drawText(mTextCharSequence, getPaddingLeft(), baseline, mTextPaint);
    }
}
