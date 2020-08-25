package com.uae.emiratescar.viewmodel.factory;

import android.content.Context;

import com.uae.emiratescar.viewmodel.DashboardViewModel;
import com.uae.emiratescar.viewmodel.MyViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class DashboardViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;


    public DashboardViewModelFactory(Context context) {
        this.context = context;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DashboardViewModel(context);
    }


}