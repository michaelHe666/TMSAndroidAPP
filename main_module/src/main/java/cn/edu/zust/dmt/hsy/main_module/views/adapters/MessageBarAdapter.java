package cn.edu.zust.dmt.hsy.main_module.views.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/5/2020 14:09
 **/
public class MessageBarAdapter extends RecyclerView.Adapter<MessageBarAdapter.MessageBarViewHolder> {

    @NonNull
    @Override
    public MessageBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageBarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected static final class MessageBarViewHolder extends RecyclerView.ViewHolder {
        public MessageBarViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
