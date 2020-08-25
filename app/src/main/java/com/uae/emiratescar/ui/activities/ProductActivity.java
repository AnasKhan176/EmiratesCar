package com.uae.emiratescar.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ActivityProductBinding;
import com.uae.emiratescar.ui.adapter.ProductListViewAdapter;
import com.uae.emiratescar.ui.model.ProductList;
import com.uae.emiratescar.viewmodel.ProductViewModel;
import com.uae.emiratescar.viewmodel.factory.ProductViewModelFactory;
//https://androidwave.com/android-data-binding-recyclerview/
import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductActivity extends BaseActivity {
    ProductListViewAdapter adapter;
    private RecyclerView recyclerViewList;
    private ActivityProductBinding binding;
    private int prdType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        showBackButton(true);
        setActivityTitle(getString(R.string.prd_title));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
             prdType = bundle.getInt("prdType");
        }
        initRecycleView();
        ProductViewModel productViewModel = ViewModelProviders.of(this, new ProductViewModelFactory(this)).get(ProductViewModel.class);
        productViewModel.getProductsMutableLiveData(prdType).observe(this, prdListUpdateObserver);
    }

    private void initRecycleView() {
        // bind RecyclerView
        recyclerViewList = binding.recyclerView;
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.setHasFixedSize(true);

    }

    Observer<ArrayList<ProductList>> prdListUpdateObserver = new Observer<ArrayList<ProductList>>() {
        @Override
        public void onChanged(ArrayList<ProductList> userArrayList) {
            adapter = new ProductListViewAdapter(prdType);
            recyclerViewList.setAdapter(adapter);
            adapter.setEmployeeList(userArrayList,ProductActivity.this);
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}