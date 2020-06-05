package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.ManagementButtonAdapter;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.OptionButtonListAdapter;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.NullViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.NullViewModel;

@MyRouter(path = MyRouterPaths.DELIVERY_SELECT_ACTIVITY)
public class DeliverySelectActivity extends MyActivity<NullViewModelListener, NullViewModel> {
    private cm_MyTopBar mTopBar;
    private ListView mListView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_activity_delivery_select;
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
        mTopBar = findViewById(R.id.mm_activity_delivery_select_my_top_bar);
        mListView = findViewById(R.id.mm_activity_delivery_select_my_list_view);

    }

    @Override
    protected void loadActorsToViews() {
        mTopBar.setTitle(R.string.mm_string_activity_delivery_select_title);

        final List<OptionButtonListAdapter.OptionButtonAttributes> attributesList = new ArrayList<>();
        attributesList.add(new OptionButtonListAdapter.OptionButtonAttributes(R.string.mm_string_fragment_management_delivery));

        mListView.setAdapter(new OptionButtonListAdapter(attributesList,getBaseContext()));
    }
}
