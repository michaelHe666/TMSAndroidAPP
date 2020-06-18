package cn.edu.zust.dmt.hsy.common_module.views.customized;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/3/2020 14:41
 **/
//todo: attribute set impact is ignored ,try fix by add judge
public final class cm_MyFlowLayout extends FrameLayout {
    private List<Integer> mLineHeights = new ArrayList<>();
    private List<List<View>> mViewListsRecorder = new ArrayList<>();

    public cm_MyFlowLayout(@NonNull Context context) {
        this(context, null);
    }

    public cm_MyFlowLayout(@Nullable Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @description measure all child views and fill {@link #mViewListsRecorder,#mLineHeights}
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int maxWidth = MeasureSpec.getSize(widthMeasureSpec);

        mLineHeights.clear();
        mViewListsRecorder.clear();
        int myWidth = 0, myHeight = 0;
        final int count = getChildCount();

        int currentLineWidth = 0, currentLineHeight = 0;
        List<View> currentLineViews = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) child.getLayoutParams();

            final int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            final int childHeight = child.getMeasuredHeight() + params.bottomMargin + params.topMargin;

            if (currentLineWidth + childWidth > maxWidth - getPaddingLeft() - getPaddingRight()) {
                myWidth = Math.max(myWidth, currentLineWidth);
                myHeight = myHeight + currentLineHeight;

                mLineHeights.add(currentLineHeight);
                mViewListsRecorder.add(currentLineViews);

                currentLineViews = new ArrayList<>();
                currentLineWidth = childWidth;
                currentLineHeight = childHeight;
            } else {
                currentLineWidth = currentLineWidth + childWidth;
                currentLineHeight = Math.max(currentLineHeight, childHeight);
            }
            currentLineViews.add(child);

            if (i == (count - 1)) {
                myHeight = myHeight + currentLineHeight;
                myWidth = Math.max(myWidth, currentLineWidth);
                mLineHeights.add(currentLineHeight);
                mViewListsRecorder.add(currentLineViews);
            }
        }

        myHeight = myHeight + getPaddingTop() + getPaddingBottom();
        myWidth = myWidth + getPaddingRight() + getPaddingLeft();

        setMeasuredDimension(myWidth, myHeight);
    }

    /**
     * @description locate all child views by {@link #mLineHeights,#mViewListsRecorder}
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentTop = getPaddingTop();
        int currentLeft;

        for (int i = 0; i < mViewListsRecorder.size(); i++) {
            currentLeft = getPaddingLeft();
            final List<View> currentLineViews = mViewListsRecorder.get(i);
            final int currentLineHeight = mLineHeights.get(i);
            for (int j = 0; j < currentLineViews.size(); j++) {
                final View child = currentLineViews.get(j);
                final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) child.getLayoutParams();
                int childLeft = currentLeft + params.leftMargin;
                int childRight = currentLeft + child.getMeasuredWidth();
                int childTop = currentTop + params.topMargin;
                int childBottom = currentTop + child.getMeasuredHeight();

                child.layout(childLeft, childTop, childRight, childBottom);

                currentLeft = currentLeft + child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            }
            currentTop = currentTop + currentLineHeight;
        }
    }
}
