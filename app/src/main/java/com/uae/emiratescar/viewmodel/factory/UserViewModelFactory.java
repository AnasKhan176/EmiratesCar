package com.uae.emiratescar.viewmodel.factory;

import android.content.Context;

import com.uae.emiratescar.ui.model.User;
import com.uae.emiratescar.viewmodel.UserViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private User user;
    private Context context;


    public UserViewModelFactory(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new UserViewModel(context, user);
    }
}