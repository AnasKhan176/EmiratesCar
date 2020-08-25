package com.uae.emiratescar.viewmodel.factory;

import android.content.Context;
import com.uae.emiratescar.viewmodel.ProductViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;

    public ProductViewModelFactory(Context context) {
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ProductViewModel(context);
    }
}