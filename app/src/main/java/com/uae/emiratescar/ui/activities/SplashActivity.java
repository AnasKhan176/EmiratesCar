package com.uae.emiratescar.ui.activities;

import android.os.Bundle;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ActivitySplashBinding;
import com.uae.emiratescar.utils.Constant;
//import com.uae.emiratescar.utils.PermissionUtils;
import com.uae.emiratescar.utils.PermissionUtils;
import com.uae.emiratescar.utils.Utils;
import com.uae.emiratescar.viewmodel.MyViewModel;
import com.uae.emiratescar.viewmodel.factory.MyViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        MyViewModel myViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this)).get(MyViewModel.class);
        binding.setMyViewModel(myViewModel);
        removeStatusBar();
        Utils.requestFullScreen(this);
        if (PermissionUtils.isOSVersionMorHigher()) {
            PermissionUtils.permissionGranted(this, Constant.permissionList, Constant.ALL_PERMISSION_CONSTANT);
            if (PermissionUtils.isGranted(this, Constant.permissionList)) {
                myViewModel.showHandler();
            }
        } else {
            myViewModel.showHandler();
        }
    }
}
