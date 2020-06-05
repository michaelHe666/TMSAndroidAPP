package cn.edu.zust.dmt.hsy.main_module.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import cn.edu.zust.dmt.hsy.main_module.R;

public class OptionButtonListAdapter extends BaseAdapter {


    public static final class OptionButtonAttributes {
        @StringRes
        private final int mOptionButtonTextStringRId;

        public OptionButtonAttributes(@StringRes int optionButtonTextStringRId) {
            mOptionButtonTextStringRId = optionButtonTextStringRId;
        }
    }

    private final List<OptionButtonListAdapter.OptionButtonAttributes> mOptionButtonAttributesList;
    private final LayoutInflater mLayoutInflater;

    public OptionButtonListAdapter(@NonNull final List<OptionButtonListAdapter.OptionButtonAttributes> optionButtonAttributesList
            , @NonNull final Context context) {
        mOptionButtonAttributesList = optionButtonAttributesList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mOptionButtonAttributesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOptionButtonAttributesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OptionButtonViewHolder currentViewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(
                    R.layout.mm_views_adapter_option_button, parent, false);
            currentViewHolder = new OptionButtonViewHolder(convertView);
            convertView.setTag(currentViewHolder);
        } else {
            currentViewHolder = (OptionButtonListAdapter.OptionButtonViewHolder) convertView.getTag();
        }

        currentViewHolder.mOptionButton.setText(mOptionButtonAttributesList.get(position).mOptionButtonTextStringRId);
        return convertView;
    }

    private static class OptionButtonViewHolder extends RecyclerView.ViewHolder {
        private final CardView mOptionButtonView;
        private final Button mOptionButton;


        public OptionButtonViewHolder(@NonNull final View itemView) {
            super(itemView);
            mOptionButtonView = itemView.findViewById(R.id.mm_views_adapter_option_button_card_view);
            mOptionButton = itemView.findViewById(R.id.mm_views_adapter_option_button);

        }
    }
}
