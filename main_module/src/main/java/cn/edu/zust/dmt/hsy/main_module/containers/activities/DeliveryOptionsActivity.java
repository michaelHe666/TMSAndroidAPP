package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.OptionButtonListAdapter;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.NullViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.NullViewModel;

@MyRouter(path = MyRouterPaths.DELIVERY_OPTIONS_ACTIVITY)
public class DeliveryOptionsActivity extends MyActivity<NullViewModelListener, NullViewModel> {
    private cm_MyTopBar mTopBar;
    private ListView mListView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_activity_delivery_options;
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
        mTopBar = findViewById(R.id.mm_activity_delivery_options_my_top_bar);
        mListView = findViewById(R.id.mm_activity_delivery_options_my_list_view);
    }

    @Override
    protected void loadActorsToViews() {
        mTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final List<OptionButtonListAdapter.OptionButtonAttributes> attributesList = new ArrayList<>();
        attributesList.add(new OptionButtonListAdapter.OptionButtonAttributes(
                R.drawable.mm_icon_universal_order, R.string.mm_string_universal_order));
        attributesList.add(new OptionButtonListAdapter.OptionButtonAttributes(
                R.drawable.mm_icon_universal_scrap, R.string.mm_string_universal_scrap));
        attributesList.add(new OptionButtonListAdapter.OptionButtonAttributes(
                R.drawable.mm_icon_universal_entry, R.string.mm_string_universal_entry));
        attributesList.add(new OptionButtonListAdapter.OptionButtonAttributes(
                R.drawable.mm_icon_universal_delivery, R.string.mm_string_universal_delivery));

        mListView.setAdapter(new OptionButtonListAdapter(attributesList, this));
    }
}
