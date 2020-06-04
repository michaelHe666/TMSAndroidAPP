package cn.edu.zust.dmt.hsy.main_module.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyErrorHelper;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/3/2020 20:04
 **/
public final class OptionButtonAdapter extends BaseAdapter {
    private List<Integer> mDrawableRIdList;
    private List<Integer> mOptionTextRIdList;
    private LayoutInflater mLayoutInflater;

    public OptionButtonAdapter(@NonNull final List<Integer> drawableRIdList,
                               @NonNull final List<Integer> optionTextRIdList,
                               @NonNull final Context context) {
        if (drawableRIdList.size() == optionTextRIdList.size()) {
            mDrawableRIdList = drawableRIdList;
            mOptionTextRIdList = optionTextRIdList;
            mLayoutInflater = LayoutInflater.from(context);
        } else {
            MyErrorHelper.showMyArgumentException("Option info illegal!");
        }
    }

    @Override
    public int getCount() {
        return mDrawableRIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDrawableRIdList.get(position);
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
            currentViewHolder = (OptionButtonViewHolder) convertView.getTag();
        }
        currentViewHolder.mIconImageView.setImageResource(mDrawableRIdList.get(position));
        currentViewHolder.mOptionTextView.setText(mOptionTextRIdList.get(position));
        return convertView;
    }

    private static class OptionButtonViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIconImageView;
        private final TextView mOptionTextView;

        public OptionButtonViewHolder(@NonNull final View itemView) {
            super(itemView);
            mIconImageView = itemView.findViewById(R.id.mm_views_adapter_option_button_image_view);
            mOptionTextView = itemView.findViewById(R.id.mm_views_adapter_option_button_text_view);
        }
    }
}
