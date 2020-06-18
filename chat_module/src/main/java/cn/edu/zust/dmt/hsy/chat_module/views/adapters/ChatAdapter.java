package cn.edu.zust.dmt.hsy.chat_module.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.zust.dmt.hsy.chat_module.R;
import cn.edu.zust.dmt.hsy.chat_module.data.local.MyChatData;
import cn.edu.zust.dmt.hsy.chat_module.views.customized.cm1_MyChatView;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/6/2020 18:43
 **/
public final class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyChatViewHolder> {
    private final List<MyChatData> mChatDataList;

    public ChatAdapter(@NonNull final List<MyChatData> chatDataList) {
        mChatDataList = chatDataList;
    }

    @NonNull
    @Override
    public MyChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cm1_views_adapter_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatViewHolder holder, int position) {
        MyChatData currentData = mChatDataList.get(position);
        //set chat data
        holder.mChatView.setPhotoDrawable(currentData.getPhotoDrawable());
        holder.mChatView.setNameString(currentData.getNameString());
        holder.mChatView.setTagString(currentData.getTagString());
        holder.mChatView.setWordsString(currentData.getWordsString());
        holder.mChatView.setBubbleStyle(currentData.getStyle());
    }

    @Override
    public int getItemCount() {
        return mChatDataList.size();
    }

    /**
     * @description {@link androidx.recyclerview.widget.RecyclerView.ViewHolder} for {@link cm1_MyChatView}
     */
    protected final static class MyChatViewHolder extends RecyclerView.ViewHolder {
        private final cm1_MyChatView mChatView;

        public MyChatViewHolder(@NonNull final View itemView) {
            super(itemView);
            mChatView = itemView.findViewById(R.id.cm1_views_adapter_chat_my_chat_view);
        }
    }
}
