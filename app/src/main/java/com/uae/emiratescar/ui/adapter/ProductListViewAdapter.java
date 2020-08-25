package com.uae.emiratescar.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ItemProductBinding;
import com.uae.emiratescar.ui.activities.ProductInfoActivity;
import com.uae.emiratescar.ui.listeners.ClickListener;
import com.uae.emiratescar.ui.model.ImageDetails;
import com.uae.emiratescar.ui.model.ProductList;

import java.util.ArrayList;

public class ProductListViewAdapter
        extends RecyclerView.Adapter<ProductListViewAdapter.ProductViewHolder> {
    private ArrayList<ProductList> prdItem;
    private Context ctx;
    private int prdType;

    public ProductListViewAdapter(int prdType) {
        this.prdType = prdType;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final ItemProductBinding itemProductBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_product, viewGroup, false);

        if (prdType == 2) {
            itemProductBinding.otherLayout.setVisibility(View.VISIBLE);
            itemProductBinding.vehicleLayout.setVisibility(View.GONE);
            itemProductBinding.ivOther.setBackgroundResource(R.drawable.prd_mobile);
        } else if (prdType == 3) {
            itemProductBinding.otherLayout.setVisibility(View.VISIBLE);
            itemProductBinding.vehicleLayout.setVisibility(View.GONE);
            itemProductBinding.ivOther.setBackgroundResource(R.drawable.prd_plates);
        } else {
            itemProductBinding.otherLayout.setVisibility(View.GONE);
            itemProductBinding.vehicleLayout.setVisibility(View.VISIBLE);
        }
        itemProductBinding.setProceedListener(new ClickListener() {
            @Override
            public void onclickListener() {

                Intent intent = new Intent(ctx, ProductInfoActivity.class);
                intent.putExtra("productInfo", itemProductBinding.getProduct());
                intent.putExtra("prdType", prdType);
                ctx.startActivity(intent);
            }

            @Override
            public void onItemclickListener() {
                Intent intent = new Intent(ctx, ProductInfoActivity.class);
                intent.putExtra("productInfo", itemProductBinding.getProduct());
                intent.putExtra("prdType", prdType);
                ctx.startActivity(intent);

            }

        });

        return new ProductViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        try {
            ProductList product = prdItem.get(i);
            productViewHolder.itemProductBinding.setProduct(product);

                if (product.getImg() != null && !product.getImg().isEmpty()) {
                    productViewHolder.itemProductBinding.setProductImg(new ArrayList<ImageDetails>(product.getImg()).get(0));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (prdItem != null) {
            return prdItem.size();
        } else {
            return 0;
        }
    }

    public void setEmployeeList(ArrayList<ProductList> prdList, Context context) {
        this.prdItem = prdList;
        this.ctx = context;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ItemProductBinding itemProductBinding;

        public ProductViewHolder(@NonNull ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
        }
    }
}
