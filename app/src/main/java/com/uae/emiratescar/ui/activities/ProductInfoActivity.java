package com.uae.emiratescar.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ActivityProductInfoBinding;
import com.uae.emiratescar.ui.model.ImageDetails;
import com.uae.emiratescar.ui.model.ProductList;
import com.uae.emiratescar.viewmodel.ProductInfoViewModel;
import com.uae.emiratescar.viewmodel.factory.ProductInfoViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private ActivityProductInfoBinding binding;
    private int prdType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_info);
        showBackButton(true);
        setActivityTitle(getString(R.string.prd_details_title));


        ProductList currentPrdDetails = (ProductList) getIntent().getSerializableExtra("productInfo");
        prdType = getIntent().getIntExtra("prdType", 0);

        if (prdType == 2) {
            binding.bottomLayout.setVisibility(View.VISIBLE);
            binding.mainLayout.setVisibility(View.GONE);
            binding.setProduct(currentPrdDetails);
            binding.ivOther.setBackgroundResource(R.drawable.prd_mobile);
            ProductInfoViewModel productInfoViewModel = ViewModelProviders.of(this, new ProductInfoViewModelFactory(this, currentPrdDetails)).get(ProductInfoViewModel.class);
            binding.setProductInfoViewModel(productInfoViewModel);
            productInfoViewModel.getCurrentProductMutableLiveData();

        }
        else if (prdType == 3) {
            binding.bottomLayout.setVisibility(View.VISIBLE);
            binding.mainLayout.setVisibility(View.GONE);
            binding.setProduct(currentPrdDetails);
            binding.ivOther.setBackgroundResource(R.drawable.prd_plates);
            ProductInfoViewModel productInfoViewModel = ViewModelProviders.of(this, new ProductInfoViewModelFactory(this, currentPrdDetails)).get(ProductInfoViewModel.class);
            binding.setProductInfoViewModel(productInfoViewModel);
            productInfoViewModel.getCurrentProductMutableLiveData();

        }


        else {
            binding.bottomLayout.setVisibility(View.GONE);
            binding.mainLayout.setVisibility(View.VISIBLE);

            Observer<ProductList> prdListUpdateObserver = new Observer<ProductList>() {
                @Override
                public void onChanged(ProductList productList) {
                    binding.setProduct(productList);
                    loadImage(productList.getImg(), productList.getPrdName());
                }
            };
            ProductInfoViewModel productInfoViewModel = ViewModelProviders.of(this, new ProductInfoViewModelFactory(this, currentPrdDetails)).get(ProductInfoViewModel.class);
            binding.setProductInfoViewModel(productInfoViewModel);
            productInfoViewModel.getCurrentProductMutableLiveData().observe(this, prdListUpdateObserver);
        }
    }

    private void loadImage(List<ImageDetails> imgArr, String prdName) {
        ArrayList<ImageDetails> prdImage = new ArrayList<>(imgArr.size());
        prdImage.addAll(imgArr);
        for (ImageDetails name : prdImage) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.image(name.getPrdImg().trim())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .error(R.drawable.splash_logo)
                    .setOnSliderClickListener(this);
            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra", prdName.trim());
            binding.ivPrdPic2.addSlider(defaultSliderView);
        }
        binding.ivPrdPic2.setPresetTransformer(SliderLayout.Transformer.Accordion);
        binding.ivPrdPic2.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.ivPrdPic2.addOnPageChangeListener(this);
        binding.ivPrdPic2.stopAutoCycle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        toastLong(this, slider.getBundle().get("extra") + "");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        binding.ivPrdPic2.stopAutoCycle();
        super.onStop();
    }
}