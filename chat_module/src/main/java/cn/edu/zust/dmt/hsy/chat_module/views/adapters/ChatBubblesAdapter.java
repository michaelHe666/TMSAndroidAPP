package cn.edu.zust.dmt.hsy.chat_module.views.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.edu.zust.dmt.hsy.chat_module.R;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/6/2020 18:43
 **/
public final class ChatBubblesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final class MyChatBubbleInfo {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected final static class MyChatViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mPhotoImageView;
        private final TextView mTextView;

        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.cm1_views_adapter_chat_bubble_me_image_view);
            mTextView = itemView.findViewById(R.id.cm1_views_adapter_chat_bubble_me_text_view);
        }
    }

    protected final static class OtherChatViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mPhotoImageView;
        private final TextView mTextView;

        public OtherChatViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.cm1_views_adapter_chat_bubble_others_image_view);
            mTextView = itemView.findViewById(R.id.cm1_views_adapter_chat_bubble_others_text_view);
        }
    }
}
