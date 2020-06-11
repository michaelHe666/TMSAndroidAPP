package cn.edu.zust.dmt.hsy.main_module.containers.fragments;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.ConversationBarAdapter;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/2/2020 15:48
 **/
public final class MessageFragment extends BaseFragment<NullPresenterListener, NullPresenter> {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_fragment_message;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {
        mRecyclerView = findViewById(R.id.mm_fragment_message_recycler_view);
    }

    @Override
    protected void loadActorsToViews() {
        List<ConversationBarAdapter.MyConversationInfo> infoList = new ArrayList<>();
        infoList.add(new ConversationBarAdapter.MyConversationInfo("yjt"));
        infoList.add(new ConversationBarAdapter.MyConversationInfo("yjt"));
        infoList.add(new ConversationBarAdapter.MyConversationInfo("yjt"));
        infoList.add(new ConversationBarAdapter.MyConversationInfo("yjt"));
        infoList.add(new ConversationBarAdapter.MyConversationInfo("yjt"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new ConversationBarAdapter(infoList));
    }
}
