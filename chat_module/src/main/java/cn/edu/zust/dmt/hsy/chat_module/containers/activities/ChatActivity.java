package cn.edu.zust.dmt.hsy.chat_module.containers.activities;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.edu.zust.dmt.hsy.chat_module.R;
import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.NullViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.NullViewModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/6/2020 17:55
 **/
@MyRouter(path = MyRouterPaths.CHAT_PATH)
public final class ChatActivity extends MyActivity<NullViewModelListener, NullViewModel> {

    private cm_MyTopBar mTopBar;
    /**
     * @description show chat bubbles
     */
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutRId() {
        return R.layout.cm1_activity_chat;
    }

    @NonNull
    @Override
    protected Class<NullViewModel> getViewModelClass() {
        return NullViewModel.class;
    }

    @NonNull
    @Override
    protected NullViewModelListener getViewModelListener() {
        return new NullViewModelListener() {
        };
    }

    @Override
    protected void findViews() {
        mTopBar = findViewById(R.id.cm1_activity_chat_my_top_bar);
        mRecyclerView = findViewById(R.id.cm1_activity_chat_recycler_view);
    }

    @Override
    protected void loadActorsToViews() {
        mTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL
                , true));
    }
}
