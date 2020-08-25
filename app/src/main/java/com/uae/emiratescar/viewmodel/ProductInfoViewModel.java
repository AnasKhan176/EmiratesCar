package com.uae.emiratescar.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.uae.emiratescar.ui.model.ProductList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductInfoViewModel extends ViewModel {

    private Context context;
    private ProductList currentPrdList;
    MutableLiveData<ProductList> prdLiveData;


    public ProductInfoViewModel(Context context, ProductList currentPrdDetails) {
        this.context = context;
        this.currentPrdList = currentPrdDetails;
        setInstance();
    }

    private void setInstance() {
        if (prdLiveData == null) {
            prdLiveData = new MutableLiveData<>();
        }
    }
    public MutableLiveData getCurrentProductMutableLiveData() {
        prdLiveData.setValue(currentPrdList);
        return prdLiveData;
    }

    public void onCallClick() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
        String no=prdLiveData.getValue().getMobile();
        intent.setData(Uri.parse("tel: " +no)); // Data with intent respective action on intent
        context.startActivity(intent);
    }



}