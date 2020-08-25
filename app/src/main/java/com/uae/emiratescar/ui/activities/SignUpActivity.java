package com.uae.emiratescar.ui.activities;

import android.os.Bundle;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ActivitySignUpBinding;
import com.uae.emiratescar.ui.model.User;
import com.uae.emiratescar.viewmodel.UserViewModel;
import com.uae.emiratescar.viewmodel.factory.UserViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignUpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        UserViewModel userViewModel = ViewModelProviders.of(this, new UserViewModelFactory(this, new User())).get(UserViewModel.class);
        binding.setUserViewModel(userViewModel);

    }
}
