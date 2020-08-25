package com.uae.emiratescar.viewmodel.factory;

import android.content.Context;

import com.uae.emiratescar.ui.activities.ProductInfoActivity;
import com.uae.emiratescar.ui.model.ProductList;
import com.uae.emiratescar.viewmodel.ProductInfoViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductInfoViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private ProductList currentPrdDetails;
    public ProductInfoViewModelFactory(Context context,ProductList currentPrdDetails) {
        this.context = context;
        this.currentPrdDetails=currentPrdDetails;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ProductInfoViewModel(context,currentPrdDetails);
    }
}