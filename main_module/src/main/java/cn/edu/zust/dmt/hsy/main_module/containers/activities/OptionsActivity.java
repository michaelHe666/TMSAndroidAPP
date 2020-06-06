package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zust.dmt.hsy.common_module.containers.activities.MyActivity;
import cn.edu.zust.dmt.hsy.common_module.views.combined.cm_MyTopBar;
import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.OptionButtonAdapter;
import cn.edu.zust.dmt.hsy.my_annotations_library.annotations.MyRouter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.listeners.NullViewModelListener;
import cn.edu.zust.dmt.hsy.my_base_library.viewmodels.NullViewModel;

@MyRouter(path = MyRouterPaths.OPTIONS_PATH)
public final class OptionsActivity extends MyActivity<NullViewModelListener, NullViewModel> {
    private cm_MyTopBar mTopBar;
    private ListView mOptionsListView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_activity_options;
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
        mTopBar = findViewById(R.id.mm_activity_options_my_top_bar);
        mOptionsListView = findViewById(R.id.mm_activity_options_my_list_view);
    }

    @Override
    protected void loadActorsToViews() {
        mTopBar.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final List<OptionButtonAdapter.OptionButtonAttributes> attributesList = new ArrayList<>();
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_order, R.drawable.mm_icon_universal_order,
                R.string.mm_string_universal_order_search));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_entry, R.drawable.mm_icon_universal_entry,
                R.string.mm_string_universal_order_apply));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_delivery, R.drawable.mm_icon_universal_delivery,
                R.string.mm_string_universal_order_trail_first));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_repair, R.drawable.mm_icon_universal_repair,
                R.string.mm_string_universal_order_trail_last));

        mOptionsListView.setAdapter(new OptionButtonAdapter(attributesList, this));
    }
}
