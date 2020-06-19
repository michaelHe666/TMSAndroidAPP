package cn.edu.zust.dmt.hsy.main_module.containers.activities;

import android.view.View;
import android.widget.AdapterView;
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
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyExtrasHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author JiaTao Yao
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/9/2020 20:04
 **/
@MyRouter(path = MyRouterPaths.OPTIONS_PATH)
public final class OptionsActivity extends MyActivity<NullPresenterListener, NullPresenter> {

    private cm_MyTopBar mTopBar;
    private ListView mOptionsListView;

    /**
     * @description for {@link #mOptionsListView}
     */
    private List<OptionButtonAdapter.OptionButtonAttributes> mAttributesList = new ArrayList<>();

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_activity_options;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
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

        mTopBar.setTitle(R.string.mm_string_universal_entry);

        final List<OptionButtonAdapter.OptionButtonAttributes> attributesList = new ArrayList<>();
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_fragment_management_order, R.drawable.mm_icon_activity_options_search,
                R.string.mm_string_universal_entry_search));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_fragment_management_entry, R.drawable.mm_icon_activity_options_apply,
                R.string.mm_string_universal_entry_apply));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_fragment_management_delivery, R.drawable.mm_icon_activity_options_first_audit,
                R.string.mm_string_universal_entry_confirm));

        mOptionsListView.setAdapter(new OptionButtonAdapter(attributesList, this));
        mOptionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MyExtrasHelper.MyExtras myExtras = MyExtrasHelper.buildMyExtras();
                switch (position) {
                    case 0:
                    case 1:
                        myExtras.addExtra("FORM", "0");
                        break;
                    case 2:
                        myExtras.addExtra("FORM", "1");
                        break;
                }
                callMyRouter(MyRouterPaths.FORM_PATH, myExtras);
            }
        });
    }
}
