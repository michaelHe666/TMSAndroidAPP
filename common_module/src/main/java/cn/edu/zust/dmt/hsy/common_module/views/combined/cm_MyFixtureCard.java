package cn.edu.zust.dmt.hsy.common_module.views.combined;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
 * @since 6/2/2020 19:25
 **/
public final class cm_MyFixtureCard extends ConstraintLayout {

    private TextView mNameTextView;
    private TextView mDeadlineTextView;
    private cm_MySignalLight mLocationSignal;
    private cm_MySignalLight mRiskSignal;

    public cm_MyFixtureCard(Context context) {
        super(context);
        bindView(context);
        initializeViews(context, null);
    }

    public cm_MyFixtureCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        bindView(context);
        initializeViews(context, null);
    }

    public cm_MyFixtureCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindView(context);
        initializeViews(context, null);
    }

    /**
     * @description bind child views to class member variables
     */
    private void bindView(@NonNull final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cm_views_combined_my_fixture_card, this);
        mNameTextView = view.findViewById(R.id.cm_views_my_fixture_card_title_text_view);
        mDeadlineTextView = view.findViewById(R.id.cm_views_my_fixture_card_deadline_content_text_view);
        mLocationSignal = view.findViewById(R.id.cm_views_my_fixture_card_location_my_signal_view);
        mRiskSignal = view.findViewById(R.id.cm_views_my_fixture_card_risk_my_signal_view);
    }

    /**
     * @description initialize {@link cm_MyFixtureCard} attrs to view
     */
    private void initializeViews(@NonNull final Context context, @Nullable final AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.cm_MyFixtureCard);

        final String title = typedArray.getString(R.styleable.cm_MyFixtureCard_cm_myFixtureName);
        final String deadline = typedArray.getString(R.styleable.cm_MyFixtureCard_cm_myFixtureDeadline);
        mLocationSignal.setCurrentSignal(
                typedArray.getInt(R.styleable.cm_MyFixtureCard_cm_myFixtureLocationSignal, -1));
        mRiskSignal.setCurrentSignal(
                typedArray.getInt(R.styleable.cm_MyFixtureCard_cm_myFixtureRiskSignal, -1));

        if (title != null) {
            setFixtureName(title);
        }
        if (deadline != null) {
            setFixtureDeadline(deadline);
        }

        typedArray.recycle();
    }

    public void setFixtureName(@NonNull final String fixtureName) {
        mNameTextView.setText(fixtureName);
    }

    public void setFixtureDeadline(@NonNull final String fixtureDeadline) {
        mDeadlineTextView.setText(fixtureDeadline);
    }

    public void setLocationSafe() {
        mLocationSignal.setCurrentSignal(0);
    }

    public void setLocationUnsafe() {
        mLocationSignal.setCurrentSignal(1);
    }

    public void setRiskLow() {
        mRiskSignal.setCurrentSignal(0);
    }

    public void setRiskHigh() {
        mRiskSignal.setCurrentSignal(1);
    }
}
