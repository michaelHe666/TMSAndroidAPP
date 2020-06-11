package cn.edu.zust.dmt.hsy.main_module.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.zust.dmt.hsy.main_module.R;

/**
 * @author JiaTao Yao
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/9/2020 20:04
 **/
public final class OptionButtonAdapter extends BaseAdapter {

    public static final class OptionButtonAttributes {
        @ColorRes
        private final int mBackgroundColorRId;
        @DrawableRes
        private final int mIconDrawableRId;
        @StringRes
        private final int mTextStringRId;

        public OptionButtonAttributes(@ColorRes int backgroundColorRId, @DrawableRes int iconDrawableRId,
                                      @StringRes int textStringRId) {
            mBackgroundColorRId = backgroundColorRId;
            mIconDrawableRId = iconDrawableRId;
            mTextStringRId = textStringRId;
        }
    }

    private final List<OptionButtonAttributes> mAttributesList;
    private final LayoutInflater mLayoutInflater;

    public OptionButtonAdapter(@NonNull final List<OptionButtonAttributes> attributesList
            , @NonNull final Context context) {
        mAttributesList = attributesList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mAttributesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAttributesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
            currentViewHolder = (OptionButtonViewHolder) convertView.getTag();
        }
        final OptionButtonAttributes attributes = mAttributesList.get(position);
        currentViewHolder.mBackgroundCardView.setCardBackgroundColor(mLayoutInflater.getContext()
                .getResources().getColor(attributes.mBackgroundColorRId));
        currentViewHolder.mIconImageView.setImageResource(attributes.mIconDrawableRId);
        currentViewHolder.mOptionTextView.setText(attributes.mTextStringRId);
        return convertView;
    }

    private static class OptionButtonViewHolder extends RecyclerView.ViewHolder {
        private final CardView mBackgroundCardView;
        private final ImageView mIconImageView;
        private final TextView mOptionTextView;

        public OptionButtonViewHolder(@NonNull final View itemView) {
            super(itemView);
            mBackgroundCardView = itemView.findViewById(R.id.mm_views_adapter_option_button_card_view);
            mIconImageView = itemView.findViewById(R.id.mm_views_adapter_option_button_image_view);
            mOptionTextView = itemView.findViewById(R.id.mm_views_adapter_option_button_text_view);
        }
    }
}
