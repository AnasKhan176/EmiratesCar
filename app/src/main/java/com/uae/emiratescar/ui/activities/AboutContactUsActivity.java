package com.uae.emiratescar.ui.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uae.emiratescar.R;
import com.uae.emiratescar.databinding.ActivityAboutContactusBinding;
import com.uae.emiratescar.viewmodel.MyViewModel;
import com.uae.emiratescar.viewmodel.factory.MyViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class AboutContactUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutContactusBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_contactus);
        MyViewModel myViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this)).get(MyViewModel.class);
        binding.setMyViewModel(myViewModel);
        showBackButton(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String value = bundle.getString("type");
            if (value.equals("0")) {
                setActivityTitle(getString(R.string.dash_about));
                binding.tvAddress.setVisibility(View.GONE);
                binding.tvAddressHint.setVisibility(View.GONE);
                binding.tvContact.setVisibility(View.GONE);
                binding.tvContactHint.setVisibility(View.GONE);
                binding.tvWeb.setVisibility(View.GONE);
                binding.tvWebHint.setVisibility(View.GONE);
            } else {
                setActivityTitle(getString(R.string.dash_contact));
                binding.tvAboutUsHint.setVisibility(View.GONE);
                String contact_color = "For more details and lower price call upon below no." +
                        " <font color='" + getColor(R.color.watermelon_red) + "'>" + "0507777161" + "</font>"
                        + "or" +
                        " <font color='" + getColor(R.color.watermelon_red) + "'>" + "0555555634" + "</font>";

                String web_color = " <font color='" + getColor(R.color.watermelon_red) + "'>" + getString(R.string.p_website) + "</font>";


                binding.tvContact.setText(Html.fromHtml(contact_color),
                        TextView.BufferType.SPANNABLE);
                binding.tvWeb.setText(Html.fromHtml(web_color),
                        TextView.BufferType.SPANNABLE);


            }
        }
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

}
