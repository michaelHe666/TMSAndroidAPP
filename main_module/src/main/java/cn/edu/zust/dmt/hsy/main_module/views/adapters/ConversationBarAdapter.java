package cn.edu.zust.dmt.hsy.main_module.views.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.datas.local.MyConversationInfo;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/5/2020 14:09
 **/
public final class ConversationBarAdapter
        extends RecyclerView.Adapter<ConversationBarAdapter.ConversationBarViewHolder> {
    private final List<MyConversationInfo> mConversationInfoList;

    public ConversationBarAdapter(@NonNull final List<MyConversationInfo> conversationInfoList) {
        mConversationInfoList = conversationInfoList;
    }

    @NonNull
    @Override
    public ConversationBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversationBarViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mm_views_adapter_conversation_bar, parent, false));
    }

    /**
     * @description get position and judge which data to return
     */
    @Override
    public void onBindViewHolder(@NonNull ConversationBarViewHolder holder, int position) {
        if (position == 0) {
            holder.mPhotoImageView.setImageResource(R.drawable.mm_icon_fragment_message_notification);
            holder.mNameTextView.setText(R.string.mm_string_universal_notification);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder
                    .mBackgroundView.getLayoutParams();
            layoutParams.bottomMargin = layoutParams.bottomMargin * 3;
            holder.mBackgroundView.setLayoutParams(layoutParams);
            holder.mTagTextView.setVisibility(View.GONE);
            holder.mHintTextView.setVisibility(View.GONE);
            holder.hideTag();
        } else {
            MyConversationInfo info = mConversationInfoList.get(position - 1);
            holder.mPhotoImageView.setImageDrawable(info.getPhotoDrawable());
            holder.mNameTextView.setText(info.getTitleString());
            switch (info.getState()) {
                case TOP:
                    holder.mTagTextView.setText(R.string.mm_string_views_adapter_conversation_bar_tag_top);
                    break;
                case NORMAL:
                    holder.mTagTextView.setVisibility(View.GONE);
                    break;
            }
            final String hint = info.getHint();
            if (TextUtils.isEmpty(hint)) {
                holder.mHintTextView.setVisibility(View.GONE);
            } else {
                holder.mHintTextView.setText(hint);
            }
        }
    }

    /**
     * @description add a model which position is 0 with special data source
     */
    @Override
    public int getItemCount() {
        return mConversationInfoList.size() + 1;
    }

    protected static final class ConversationBarViewHolder extends RecyclerView.ViewHolder {
        private final View mBackgroundView;
        private final ImageView mPhotoImageView;
        private final TextView mNameTextView;
        private final TextView mTagTextView;
        private final TextView mHintTextView;

        public ConversationBarViewHolder(@NonNull View itemView) {
            super(itemView);
            mBackgroundView = itemView.findViewById(R.id.mm_views_adapter_conversation_bar_background_view);
            mPhotoImageView = itemView.findViewById(R.id.mm_views_adapter_conversation_bar_image_view);
            mNameTextView = itemView.findViewById(R.id.mm_views_adapter_conversation_bar_title_text_view);
            mTagTextView = itemView.findViewById(R.id.mm_views_adapter_conversation_bar_tag_text_view);
            mHintTextView = itemView.findViewById(R.id.mm_views_adapter_conversation_bar_hint_text_view);
        }

        private void hideTag() {
            mTagTextView.setVisibility(View.GONE);
        }
    }
}
