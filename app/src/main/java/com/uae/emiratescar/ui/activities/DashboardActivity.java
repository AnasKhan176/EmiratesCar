package com.uae.emiratescar.ui.activities;

import android.os.Bundle;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ActivityDashboardBinding;
import com.uae.emiratescar.databinding.ProductTypeDialogBinding;
import com.uae.emiratescar.viewmodel.DashboardViewModel;
import com.uae.emiratescar.viewmodel.factory.DashboardViewModelFactory;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        DashboardViewModel myViewModel = ViewModelProviders.of(this, new DashboardViewModelFactory(this)).get(DashboardViewModel.class);
        binding.setDashViewModel(myViewModel);
    }
}
