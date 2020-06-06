package cn.edu.zust.dmt.hsy.main_module.containers.fragments;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.datas.local.MyFixtureInfo;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.FixtureCardAdapter;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.NullViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.NullViewModel;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/2/2020 15:48
 **/
public final class FrontFragment extends BaseFragment<NullViewModelListener, NullViewModel> {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_fragment_front;
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
        mRecyclerView = findViewById(R.id.mm_fragment_front_container_recycler_view);
    }

    @Override
    protected void loadActorsToViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<MyFixtureInfo> myFixtureInfoList = new ArrayList<>();
        myFixtureInfoList.add(new MyFixtureInfo("ppx", "2020/1/1", 0, 0));
        myFixtureInfoList.add(new MyFixtureInfo("hsy", "2020/3/1", 0, 1));
        myFixtureInfoList.add(new MyFixtureInfo("yjt", "2020/2/1", 1, 0));
        myFixtureInfoList.add(new MyFixtureInfo("ppx", "2020/1/1", 0, 0));
        mRecyclerView.setAdapter(new FixtureCardAdapter(myFixtureInfoList));
    }
}
