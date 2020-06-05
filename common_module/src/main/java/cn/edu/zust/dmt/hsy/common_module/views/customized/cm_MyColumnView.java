package cn.edu.zust.dmt.hsy.common_module.views.customized;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.common_module.R;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/4/2020 18:22
 **/
//todo: this layout is not done
public final class cm_MyColumnView extends ViewGroup {
    private DataSetObserver mDataSetObserver = null;

    private int mColumnNumber = 1;
    private List<Integer> mLineHeights = new ArrayList<>();
    private BaseAdapter mBaseAdapter = null;
    private List<View> mViewList = new ArrayList<>();

    public cm_MyColumnView(@NonNull Context context) {
        this(context, null);
    }

    public cm_MyColumnView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public cm_MyColumnView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.cm_MyColumnView, defStyleAttr, 0);
        final int columnsNumber = typedArray.getInt(R.styleable.cm_MyColumnView_cm_myColumnNumbers, 2);
        if (columnsNumber > 0) {
            mColumnNumber = columnsNumber;
        } else {
            MyErrorHelper.showMyArgumentException("Illegal column numbers!");
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int maxWidth = MeasureSpec.getSize(widthMeasureSpec);

        removeAllViews();
        mLineHeights.clear();
        mViewList.clear();
        int myHeight = 0, currentLineHeight = 0;
        final int count = mBaseAdapter.getCount();

        for (int i = 1; i < count + 1; i++) {
            final View child = mBaseAdapter.getView(i - 1, null, this);
            addView(child);
            mViewList.add(child);
            measureChild(child, MeasureSpec.makeMeasureSpec(
                    maxWidth / mColumnNumber, MeasureSpec.AT_MOST), heightMeasureSpec);
            MyColumnViewLayoutParams params = (MyColumnViewLayoutParams) child.getLayoutParams();
            final int childHeight = child.getMeasuredHeight() + params.bottomMargin + params.topMargin;

            currentLineHeight = Math.max(currentLineHeight, childHeight);
            if (i % mColumnNumber == 0) {
                currentLineHeight = Math.max(currentLineHeight, childHeight);
                myHeight = myHeight + currentLineHeight;
                mLineHeights.add(currentLineHeight);
                //clear last line record
                currentLineHeight = 0;
            } else if (i == count) {
                currentLineHeight = Math.max(currentLineHeight, childHeight);
                myHeight = myHeight + currentLineHeight;
                mLineHeights.add(currentLineHeight);
            }
        }

        myHeight = myHeight + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(maxWidth, myHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int currentTop = getPaddingTop();
        int currentLeft;

        for (int i = 0; i < mLineHeights.size(); i++) {
            currentLeft = getPaddingLeft();
            final int currentLineHeight = mLineHeights.get(0);
            for (int j = 0; j < mColumnNumber; j++) {
                final View child = mViewList.get(i * mColumnNumber + j);
                final MyColumnViewLayoutParams params = (MyColumnViewLayoutParams) child.getLayoutParams();
                int childLeft = currentLeft + params.leftMargin;
                int childRight = currentLeft
                        + child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                int childTop = currentTop + params.topMargin;
                int childBottom = currentTop
                        + child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

                child.layout(childLeft, childTop, childRight, childBottom);

                currentLeft = currentLeft + child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            }
            currentTop = currentTop + currentLineHeight;
        }
//        invalidate();
    }

    /**
     * @param baseAdapter register {@link #mBaseAdapter,#mDataSetObserver} to current adapter
     */
    public void setBaseAdapter(@NonNull final BaseAdapter baseAdapter) {
        if (mBaseAdapter != null && mDataSetObserver != null) {
            mBaseAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        if (mDataSetObserver == null) {
            mDataSetObserver = new MyColumnDataSetObserver(this);
        }
        mBaseAdapter = baseAdapter;
        mBaseAdapter.registerDataSetObserver(mDataSetObserver);
        mBaseAdapter.notifyDataSetChanged();
    }

    /**
     * @description called by {@link MyColumnDataSetObserver#onChanged()} of {@link #mDataSetObserver}
     */
    private void onDataSetChanged() {
        if (mBaseAdapter == null) {
            removeAllViews();
            return;
        }
        final int mAdapterCount = mBaseAdapter.getCount();
        for (int i = 0; i < mAdapterCount; i++) {
            View convertView = null;
//            final int viewTypeCount = mBaseAdapter.getViewTypeCount();
            final int viewType = mBaseAdapter.getItemViewType(i);
            View view = null;
            if (i < getChildCount()) {
                int currentChildViewState;
                convertView = getChildAt(i);
                if (convertView != null) {
                    currentChildViewState = ((MyColumnViewLayoutParams) convertView.getLayoutParams()).
                            getViewState();
                } else {
                    currentChildViewState = MyColumnViewLayoutParams.TYPE_UNDEFINED;
                }
                if (currentChildViewState != viewType) {
                    view = mBaseAdapter.getView(i, null, this);
                    if (convertView != null) {
                        removeView(convertView);
                        addView(view, i);
                    } else {
                        addView(view, 1);
                    }
                } else {
                    view = mBaseAdapter.getView(i, convertView, this);
                    if (convertView != null && view != convertView) {
                        removeViewsInLayout(i, 1);
                        addView(view, i);
                    }
                }
            } else {
                view = mBaseAdapter.getView(i, null, this);
                addView(view);
            }
            if (view != null) {
                ((MyColumnViewLayoutParams) view.getLayoutParams()).viewState = viewType;
            }
        }

        int childCount = getChildCount();
        while (childCount > mAdapterCount) {
            removeViewsInLayout(getChildCount() - 1, 1);
            childCount = getChildCount();
        }

        requestLayout();
        invalidate();
    }

    /**
     * @description called by {@link MyColumnDataSetObserver#onInvalidated()} ()} of {@link #mDataSetObserver}
     */
    private void onViewInvalidated() {
        onDataSetChanged();
    }

    /**
     * @return safe {@link MyColumnViewLayoutParams} for {@link cm_MyColumnView}
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MyColumnViewLayoutParams(p);
    }

    /**
     * @return whether p is safe for {@link cm_MyColumnView}
     */
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof MyColumnViewLayoutParams);
    }

    /**
     * @return a safe {@link MyColumnViewLayoutParams} for {@link cm_MyColumnView}
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyColumnViewLayoutParams(getContext(), attrs);
    }

    /**
     * @description specially designed {@link DataSetObserver} for {@link cm_MyColumnView}
     */
    private static final class MyColumnDataSetObserver extends DataSetObserver {
        private final cm_MyColumnView mColumnView;

        public MyColumnDataSetObserver(@NonNull final cm_MyColumnView columnView) {
            mColumnView = columnView;
        }

        @Override
        public void onChanged() {
            super.onChanged();
            mColumnView.onDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            mColumnView.onViewInvalidated();
        }
    }

    /**
     * @description provide safe layout params for {@link cm_MyColumnView}
     */
    private static final class MyColumnViewLayoutParams extends ViewGroup.MarginLayoutParams {
        private int viewState;
        private static final int TYPE_UNDEFINED = -1;

        public MyColumnViewLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            final TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.cm_MyColumnView);
            this.viewState = typedArray.getInt(R.styleable.cm_MyColumnView_cm_myViewState, TYPE_UNDEFINED);
            typedArray.recycle();
        }

        public MyColumnViewLayoutParams(int width, int height) {
            super(width, height);
            this.viewState = TYPE_UNDEFINED;
        }

        public MyColumnViewLayoutParams(MarginLayoutParams source) {
            super(source);
            if (source instanceof MyColumnViewLayoutParams) {
                this.viewState = ((MyColumnViewLayoutParams) source).viewState;
            } else {
                this.viewState = TYPE_UNDEFINED;
            }
        }

        public MyColumnViewLayoutParams(LayoutParams source) {
            super(source);
            if (source instanceof MyColumnViewLayoutParams) {
                this.viewState = ((MyColumnViewLayoutParams) source).viewState;
            } else {
                this.viewState = TYPE_UNDEFINED;
            }
        }

        public int getViewState() {
            return viewState;
        }
    }

}
