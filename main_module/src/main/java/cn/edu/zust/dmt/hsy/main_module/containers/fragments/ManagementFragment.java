package cn.edu.zust.dmt.hsy.main_module.containers.fragments;

import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.ManagementButtonAdapter;
import cn.edu.zust.dmt.hsy.my_annotations_library.constants.MyRouterPaths;
import cn.edu.zust.dmt.hsy.my_base_library.containers.fragments.BaseFragment;
import cn.edu.zust.dmt.hsy.my_base_library.helpers.MyExtrasHelper;
import cn.edu.zust.dmt.hsy.my_base_library.interfaces.presenter_listeners.NullPresenterListener;
import cn.edu.zust.dmt.hsy.my_base_library.presenters.NullPresenter;

/**
 * @author MR.M
 * @version 1.0
 * @projectName TMS
 * @description $
 * @since 6/2/2020 15:48
 **/
public final class ManagementFragment extends BaseFragment<NullPresenterListener, NullPresenter> {

    private final ManagementFragment THIS = this;

    private GridView mManagementGridView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_fragment_management;
    }

    @NonNull
    @Override
    protected NullPresenterListener getPresenterListener() {
        return new NullPresenterListener() {
        };
    }

    @Override
    protected void findViews() {
        mManagementGridView = findViewById(R.id.mm_fragment_management_grid_view);
    }

    @Override
    protected void loadActorsToViews() {
        final List<ManagementButtonAdapter.ManagementButtonAttributes> attributesList = new ArrayList<>();
        attributesList.add(new ManagementButtonAdapter.ManagementButtonAttributes(
                R.color.mm_color_fragment_management_order, R.drawable.mm_icon_universal_order,
                R.string.mm_string_universal_order, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.callMyRouter(MyRouterPaths.OPTIONS_PATH, MyExtrasHelper.buildMyExtras(
                        "target", "ppx1"));
            }
        }));
        attributesList.add(new ManagementButtonAdapter.ManagementButtonAttributes(
                R.color.mm_color_fragment_management_entry, R.drawable.mm_icon_universal_entry,
                R.string.mm_string_universal_entry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.callMyRouter(MyRouterPaths.OPTIONS_PATH, MyExtrasHelper.buildMyExtras(
                        "target", "ppx2"));
            }
        }));
        attributesList.add(new ManagementButtonAdapter.ManagementButtonAttributes(
                R.color.mm_color_fragment_management_delivery, R.drawable.mm_icon_universal_delivery,
                R.string.mm_string_universal_delivery, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.callMyRouter(MyRouterPaths.OPTIONS_PATH, MyExtrasHelper.buildMyExtras(
                        "target", "ppx3"));
            }
        }));
        attributesList.add(new ManagementButtonAdapter.ManagementButtonAttributes(
                R.color.mm_color_fragment_management_repair, R.drawable.mm_icon_universal_repair,
                R.string.mm_string_universal_repair, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.callMyRouter(MyRouterPaths.OPTIONS_PATH, MyExtrasHelper.buildMyExtras(
                        "target", "ppx4"));
            }
        }));
        attributesList.add(new ManagementButtonAdapter.ManagementButtonAttributes(
                R.color.mm_color_fragment_management_scrap, R.drawable.mm_icon_universal_scrap,
                R.string.mm_string_universal_scrap, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                THIS.callMyRouter(MyRouterPaths.OPTIONS_PATH, MyExtrasHelper.buildMyExtras(
                        "target", "ppx5"));
            }
        }));
        mManagementGridView.setAdapter(new ManagementButtonAdapter(attributesList,
                Objects.requireNonNull(getContext())));
    }
}
