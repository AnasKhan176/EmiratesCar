package com.uae.emiratescar.viewmodel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ProductTypeDialogBinding;
import com.uae.emiratescar.ui.activities.AboutContactUsActivity;
import com.uae.emiratescar.ui.activities.DashboardActivity;
import com.uae.emiratescar.ui.activities.ProductActivity;
import com.uae.emiratescar.ui.activities.SignInActivity;
import com.uae.emiratescar.viewmodel.factory.DashboardViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class DashboardViewModel extends ViewModel {

    private Context context;

    public DashboardViewModel(Context context) {
        this.context = context;
    }

    public void onAboutUsClick() {
        Intent intent = new Intent(context, AboutContactUsActivity.class);
        intent.putExtra("type", "0");
        context.startActivity(intent);

    }

    public void onContactUsClick() {
        Intent intent = new Intent(context, AboutContactUsActivity.class);
        intent.putExtra("type", "1");
        context.startActivity(intent);

    }

    public void onSellClick() {
        //((DashboardActivity) context).toastShort((DashboardActivity) context, "Under process");
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
        // ((Activity) context).finish();

    }

    public void onBuyClick() {
        openPrdTypeDialog();
    }

    public void onVehicleClick() {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("prdType",1);
        context.startActivity(intent);
    }

    public void onMobileClick() {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("prdType",2);
        context.startActivity(intent);
    }

    public void onVPlatesClick() {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("prdType",3);
        context.startActivity(intent);
    }

    private void openPrdTypeDialog() {

        ProductTypeDialogBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.product_type_dialog, null, false);
        DashboardViewModel myViewModel = ViewModelProviders.of((DashboardActivity) context).get(DashboardViewModel.class);
        binding.setDashViewModel(myViewModel);

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(binding.getRoot());
        dialog.show();

    }

}