package cn.edu.zust.dmt.hsy.main_module.containers.fragments;

import android.widget.GridView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.edu.zust.dmt.hsy.main_module.R;
import cn.edu.zust.dmt.hsy.main_module.views.adapters.OptionButtonAdapter;
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
public final class ManagementFragment extends BaseFragment<NullViewModelListener, NullViewModel> {

    private GridView mManagementGridView;

    @Override
    protected int getLayoutRId() {
        return R.layout.mm_fragment_management;
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
        mManagementGridView = findViewByRId(R.id.mm_fragment_management_grid_view);
    }

    @Override
    protected void loadActorsToViews() {
        final List<OptionButtonAdapter.OptionButtonAttributes> attributesList = new ArrayList<>();
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_order, R.drawable.mm_icon_universal_order,
                R.string.mm_string_fragment_management_order, 80));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_entry, R.drawable.mm_icon_universal_entry,
                R.string.mm_string_fragment_management_entry, 80));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_delivery, R.drawable.mm_icon_universal_delivery,
                R.string.mm_string_fragment_management_delivery, 80));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_repair, R.drawable.mm_icon_universal_repair,
                R.string.mm_string_fragment_management_repair, 80));
        attributesList.add(new OptionButtonAdapter.OptionButtonAttributes(
                R.color.mm_color_universal_scrap, R.drawable.mm_icon_universal_scrap,
                R.string.mm_string_fragment_management_scrap, 80));

        mManagementGridView.setAdapter(new OptionButtonAdapter(attributesList,
                Objects.requireNonNull(getContext())));
    }
}
