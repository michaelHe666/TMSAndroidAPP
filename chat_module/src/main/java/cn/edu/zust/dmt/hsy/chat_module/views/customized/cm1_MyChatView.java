package cn.edu.zust.dmt.hsy.chat_module.views.customized;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.chat_module.R;
import cn.edu.zust.dmt.hsy.chat_module.views.adapters.ChatAdapter;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description special {@link android.view.View} designed for
 * {@link ChatAdapter}
 * @since 6/12/2020 11:03
 **/
public final class cm1_MyChatView extends View {
    //data
    /**
     * @description photo for current {@link cm1_MyChatView} speaker
     */
    @Nullable
    private Drawable mPhotoDrawable = null;
    /**
     * @description tag for current {@link cm1_MyChatView} speaker
     */
    @Nullable
    private String mTagString = null;
    /**
     * @description name for current {@link cm1_MyChatView} speaker
     */
    @Nullable
    private String mNameString = null;
    /**
     * @description words of current {@link cm1_MyChatView} speaker
     */
    @Nullable
    private String mWordsString = null;
    /**
     * @description contains lists of split words if {@link #mWordsString} is too long
     */
    @NonNull
    private List<String> mSplitWordsList = new ArrayList<>();
    /**
     * @description {@link MyBubbleStyle} for {@link cm1_MyChatView}
     */
    @NonNull
    private MyBubbleStyle mBubbleStyle = MyBubbleStyle.LEFT;

    //dimensions
    /**
     * @description for {@link #mTagString}
     */
    private float mTagFontSize;
    /**
     * @description for {@link #mNameString}
     */
    private float mNameFontSize;
    /**
     * @description for {@link #mWordsString}
     */
    private float mWordsFontSize;
    /**
     * @description for {@link #mWordsString} bubble inner padding
     */
    private float mBubbleWidthPercent;
    /**
     * @description for {@link #mWordsString} bubble inner padding
     */
    private float mBubblePadding;
    /**
     * @description for {@link #mWordsString} bubble corner radius
     */
    private float mBubbleRadius;

    //colors
    /**
     * @description for {@link #mWordsString} bubble background color
     */
    private int mBubbleColor;

    //paints and rect
    /**
     * @description for {@link #mPhotoDrawable}
     */
    @NonNull
    private RectF mPhotoRectF = new RectF();
    /**
     * @description for {@link #mTagString}
     */
    @NonNull
    private Paint mTagPaint = new Paint();
    /**
     * @description for {@link #mTagString}
     */
    @NonNull
    private Rect mTagRect = new Rect();
    /**
     * @description for {@link #mNameString}
     */
    @NonNull
    private Paint mNamePaint = new Paint();
    /**
     * @description for {@link #mNameRect}
     */
    @NonNull
    private Rect mNameRect = new Rect();
    /**
     * @description for {@link #mWordsString}
     */
    @NonNull
    private Paint mWordsPaint = new Paint();
    /**
     * @description for {@link #mWordsString}
     */
    @NonNull
    private Rect mWordsRect = new Rect();
    /**
     * @description for {@link #mWordsString} bubble background
     */
    @NonNull
    private RectF mBubbleRectF = new RectF();
    /**
     * @description for {@link #mWordsString} bubble background
     */
    @NonNull
    private Paint mBubblePaint = new Paint();

    /**
     * @description class for describe style for current {@link cm1_MyChatView}
     */
    public enum MyBubbleStyle {
        /**
         * @description {@link #mPhotoDrawable} is at left of {@link #mWordsString}
         */
        LEFT,
        /**
         * @description {@link #mPhotoDrawable} is at right of {@link #mWordsString}
         */
        RIGHT,
    }

    /**
     * @param context for example initialized by new {@link cm1_MyChatView}
     */
    public cm1_MyChatView(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * @description for example initialized in xml
     */
    public cm1_MyChatView(@NonNull final Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @description inject {@link #initializeAttributes(Context, AttributeSet, int)}
     */
    public cm1_MyChatView(@NonNull final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes(context, attrs, defStyleAttr);
    }

    /**
     * @description get attributes possible exist and initialize child view attributes
     */
    private void initializeAttributes(@NonNull final Context context, @Nullable AttributeSet attributeSet
            , int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet
                , R.styleable.cm1_MyChatView, defStyleAttr, 0);

        //tag attributes
        mTagString = typedArray.getString(R.styleable.cm1_MyChatView_cm1_MyChatTag);
        final int defaultFontSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12
                , getResources().getDisplayMetrics()));
        mTagFontSize = typedArray.getDimensionPixelSize(R.styleable.cm1_MyChatView_cm1_MyChatTagSize
                , defaultFontSize);

        //name attributes
        mNameString = typedArray.getString(R.styleable.cm1_MyChatView_cm1_MyChatName);
        mNameFontSize = typedArray.getDimensionPixelSize(
                R.styleable.cm1_MyChatView_cm1_MyChatNameSize, defaultFontSize);

        //words attributes
        mWordsString = typedArray.getString(R.styleable.cm1_MyChatView_cm1_MyChatWords);
        mWordsFontSize = typedArray.getDimensionPixelSize(
                R.styleable.cm1_MyChatView_cm1_MyChatWordsSize, defaultFontSize);

        //bubble attributes
        mBubbleWidthPercent = typedArray.getFloat(R.styleable.cm1_MyChatView_cm1_MyChatBubbleWidthPercent
                , 0.6f);
        if (mBubbleWidthPercent < 0.4f || mBubbleWidthPercent > 0.8f) {
            MyErrorHelper.showMyArgumentException("Bubble width percent for MyChatView should be [0.4,0.8]!");
        }
        final int defaultPaddingSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10
                , getResources().getDisplayMetrics()));
        mBubblePadding = typedArray.getDimensionPixelSize(R.styleable.cm1_MyChatView_cm1_MyChatBubblePadding
                , defaultPaddingSize);
        mBubbleColor = typedArray.getColor(R.styleable.cm1_MyChatView_cm1_MyChatBubbleColor, Color.WHITE);
        mBubbleRadius = typedArray.getDimensionPixelSize(R.styleable.cm1_MyChatView_cm1_MyChatBubbleCornerRadius
                , defaultPaddingSize);
        switch (typedArray.getInteger(R.styleable.cm1_MyChatView_cm1_MyChatBubbleStyle, 0)) {
            case 0:
                mBubbleStyle = MyBubbleStyle.LEFT;
                break;
            case 1:
                mBubbleStyle = MyBubbleStyle.RIGHT;
                break;
        }
        typedArray.recycle();

        initializePaintAndRect();
    }

    private void initializePaintAndRect() {
        mTagPaint.setAntiAlias(true);

        mNamePaint.setAntiAlias(true);

        mWordsPaint.setAntiAlias(true);

        mBubblePaint.setAntiAlias(true);
        mBubblePaint.setColor(mBubbleColor);
        mBubblePaint.setFlags(Paint.FILTER_BITMAP_FLAG);
    }

    /**
     * @description measure {@link #mTagString,#mNameString,#mWordsString} and {@link cm1_MyChatView}
     */
    //todo: consider add native method to split words to improve runtime efficiency
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int contentWidth = widthSize - getPaddingLeft() - getPaddingRight();
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //measure tag
        if (!TextUtils.isEmpty(mTagString)) {
            mTagPaint.setTextSize(mTagFontSize);
            mTagPaint.getTextBounds(mTagString, 0, mTagString.length(), mTagRect);
        }

        //measure name
        if (!TextUtils.isEmpty(mNameString)) {
            mNamePaint.setTextSize(mNameFontSize);
            mNamePaint.getTextBounds(mNameString, 0, mNameString.length(), mNameRect);
        }

        //measure words
        mSplitWordsList.clear();
        if (!TextUtils.isEmpty(mWordsString)) {
            mWordsPaint.setTextSize(mWordsFontSize);
            mWordsPaint.getTextBounds(mWordsString, 0, mWordsString.length(), mWordsRect);
            final int wordsWidth = mWordsRect.width();
            if ((wordsWidth + mBubblePadding) > contentWidth * mBubbleWidthPercent) {
                final int splitLineCount = (int) (Math.ceil((wordsWidth * 1.0f) /
                        (contentWidth * mBubbleWidthPercent - mBubblePadding * 2.0f)));
                int currentLineStart = 0;
                int currentLineEnd = 0;
                for (int i = 0; i < splitLineCount; i++) {
                    if (i == splitLineCount - 1) {
                        mSplitWordsList.add(mWordsString.substring(currentLineStart));
                        break;
                    }
                    mWordsPaint.getTextBounds(mWordsString, 0, 0, mWordsRect);
                    while ((mWordsRect.width() + mBubblePadding * 2.0f) <= contentWidth * mBubbleWidthPercent) {
                        currentLineEnd = currentLineEnd + 1;
                        mWordsPaint.getTextBounds(mWordsString, currentLineStart, currentLineEnd, mWordsRect);
                    }
                    mSplitWordsList.add(mWordsString.substring(currentLineStart, currentLineEnd - 1));
                    currentLineStart = currentLineEnd;
                }
            }
        }

        int height;
        //calculate height size
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            final int wordsHeight = mWordsRect.height();
            height = Math.max(mTagRect.height(), mNameRect.height()) + getPaddingTop() + getPaddingBottom();
            final int lineCount = mSplitWordsList.size();
            if (lineCount == 0) {
                height = (int) (Math.ceil(Math.max(Math.ceil(contentWidth * (1.0f - mBubbleWidthPercent) * 0.4f)
                        , height + wordsHeight)) + 2.0f * mBubblePadding);
            } else {
                height = (int) (height + wordsHeight * lineCount + 2.0f * mBubblePadding);
            }
        }
        setMeasuredDimension(widthSize, height);
    }

    /**
     * @param canvas draw {@link #mTagString,#mNameString,#mWordsString,#mPhotoDrawable,#mBubbleRectF}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        final int width = getMeasuredWidth();

        final int nameAndBarHeight = Math.max(mTagRect.height(), mNameRect.height());

        final float leftGuideLine = (1.0f - mBubbleWidthPercent) * 0.5f * width;
        final float rightGuideLine = (1.0f + mBubbleWidthPercent) * 0.5f * width;
        switch (mBubbleStyle) {
            case LEFT:
                break;
            case RIGHT:
                final int lineCount = mSplitWordsList.size();
                if (lineCount == 0) {
                    if (mWordsString != null) {
                        final int lineHeight = mWordsRect.height();
                        mBubbleRectF.set(rightGuideLine - mWordsRect.width() - 2 * mBubblePadding
                                , nameAndBarHeight, rightGuideLine
                                , nameAndBarHeight + lineHeight + 2 * mBubblePadding);
                        canvas.drawRoundRect(mBubbleRectF, mBubbleRadius, mBubbleRadius, mBubblePaint);
                        canvas.drawText(mWordsString, rightGuideLine - mBubblePadding - mWordsRect.width()
                                , lineHeight + nameAndBarHeight + mBubblePadding, mWordsPaint);
                    }
                } else {
                    final int lineHeight = mWordsRect.height();
                    mBubbleRectF.set(leftGuideLine, nameAndBarHeight, rightGuideLine
                            , nameAndBarHeight + lineHeight * lineCount + 2 * mBubblePadding);
                    canvas.drawRoundRect(mBubbleRectF, mBubbleRadius, mBubbleRadius, mBubblePaint);
                    for (int i = 0; i < lineCount; i++) {
                        canvas.drawText(mSplitWordsList.get(i), leftGuideLine + mBubblePadding
                                , lineHeight + mBubblePadding + i * lineHeight + nameAndBarHeight
                                , mWordsPaint);
                    }
                }
                break;
        }
    }

    /**
     * @param photoDrawable for {@link #mPhotoDrawable}
     */
    public void setPhotoDrawable(@NonNull final Drawable photoDrawable) {
        if (!photoDrawable.equals(mPhotoDrawable)) {
            mPhotoDrawable = photoDrawable;
            invalidate();
        }
    }

    /**
     * @param tagString for {@link #mTagString}
     */
    public void setTagString(@NonNull final String tagString) {
        if (!tagString.equals(mTagString)) {
            mTagString = tagString;
            invalidate();
        }
    }

    /**
     * @param nameString for {@link #mNameString}
     */
    public void setNameString(@NonNull final String nameString) {
        if (!nameString.equals(mNameString)) {
            mNameString = nameString;
            invalidate();
        }
    }

    /**
     * @param wordsString for {@link #mWordsString}
     */
    public void setWordsString(@NonNull final String wordsString) {
        if (!wordsString.equals(mWordsString)) {
            mWordsString = wordsString;
            invalidate();
        }
    }

    /**
     * @param bubbleStyle for {@link #mBubbleStyle}
     */
    public void setBubbleStyle(@NonNull MyBubbleStyle bubbleStyle) {
        if (!bubbleStyle.equals(mBubbleStyle)) {
            mBubbleStyle = bubbleStyle;
            invalidate();
        }
    }

    /**
     * @param bubbleColor for {@link #mBubbleColor}
     */
    public void setBubbleColor(@ColorInt int bubbleColor) {
        if (bubbleColor != mBubbleColor) {
            mBubbleColor = bubbleColor;
            invalidate();
        }
    }
}
