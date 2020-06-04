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
        final List<Integer> drawableList = new ArrayList<>();
        drawableList.add(R.drawable.mm_icon_universal_order);
        drawableList.add(R.drawable.mm_icon_universal_entry);
        drawableList.add(R.drawable.mm_icon_universal_delivery);

        final List<Integer> textList = new ArrayList<>();
        textList.add(R.string.mm_string_fragment_management_order);
        textList.add(R.string.mm_string_fragment_management_entry);
        textList.add(R.string.mm_string_fragment_management_delivery);

        mManagementGridView.setAdapter(new OptionButtonAdapter(drawableList, textList,
                Objects.requireNonNull(getContext())));
    }
}
