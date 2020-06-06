package cn.edu.zust.dmt.hsy.main_module.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.zust.dmt.hsy.common_module.views.extended.cm_MySignalView;
import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.datas.local.MyFixtureInfo;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/3/2020 9:23
 **/
public final class FixtureCardAdapter extends RecyclerView.Adapter<FixtureCardAdapter.FixtureCardViewHolder> {

    private List<MyFixtureInfo> mFixtureInfoList;

    public FixtureCardAdapter(@NonNull final List<MyFixtureInfo> fixtureInfoList) {
        mFixtureInfoList = fixtureInfoList;
    }

    @NonNull
    @Override
    public FixtureCardViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        return new FixtureCardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mm_views_adapter_fixture_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureCardViewHolder holder, int position) {
        final MyFixtureInfo fixtureInfo = mFixtureInfoList.get(position);
        holder.mFixtureNameTextView.setText(fixtureInfo.getName());
        holder.mDeadlineTextView.setText(fixtureInfo.getDeadline());
        holder.mLocationSignalView.setCurrentSignal(fixtureInfo.getLocationState());
        holder.mRiskSignalView.setCurrentSignal(fixtureInfo.getRiskState());
    }

    @Override
    public int getItemCount() {
        return mFixtureInfoList.size();
    }

    protected static final class FixtureCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView mFixtureNameTextView;
        private final TextView mDeadlineTextView;
        private final cm_MySignalView mLocationSignalView;
        private final cm_MySignalView mRiskSignalView;

        public FixtureCardViewHolder(@NonNull final View itemView) {
            super(itemView);
            mFixtureNameTextView = itemView.findViewById(R.id.mm_views_adapter_fixture_card_name_text_view);
            mDeadlineTextView = itemView
                    .findViewById(R.id.mm_views_adapter_fixture_card_deadline_content_text_view);
            mLocationSignalView = itemView
                    .findViewById(R.id.mm_views_adapter_fixture_card_location_my_signal_view);
            mRiskSignalView = itemView.findViewById(R.id.mm_views_adapter_fixture_card_risk_my_signal_view);
        }
    }
}
