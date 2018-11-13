package com.absinthe.deskmaterabb;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.absinthe.deskmaterabb.fragment.PussFragment;
import com.absinthe.deskmaterabb.fragment.RabbFragment;
import com.absinthe.deskmaterabb.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener ,PussFragment.OnFragmentInteractionListener,RabbFragment.OnFragmentInteractionListener,SettingsFragment.OnFragmentInteractionListener{

    private ViewPager mViewpager;
    private BottomNavigationView navigation;
    private List<Fragment> fragmentList  =new ArrayList<Fragment>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mViewpager.setCurrentItem(item.getOrder());
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewpager = findViewById(R.id.view_pager);
        mViewpager.addOnPageChangeListener(this);

        Fragment fragment1 = new PussFragment();
        Fragment fragment2 = new RabbFragment();
        Fragment fragment3 = new SettingsFragment();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navigation.getMenu().getItem(position).setChecked(true);
        switch (position) {
            case 0:
                changeActionBarColor(R.style.PussTheme);
                break;
            case 1:
                changeActionBarColor(R.style.RabbTheme);
                break;
            case 2:
                changeActionBarColor(R.style.SettingsTheme);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void changeActionBarColor(int inThemeId) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            String primaryColor;
            String primaryColorDark;
            String actionBarTextColor;

            primaryColor = this.getThemePrimaryColor(inThemeId);
            primaryColorDark = this.getThemePrimaryColorDark(inThemeId);
            actionBarTextColor = this.getThemeActionBarTextColor(inThemeId);

            if (primaryColor != null && primaryColor.length() > 0) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(primaryColor)));
            }

            if (primaryColorDark != null && primaryColorDark.length() > 0) {
                Window window = this.getWindow();

                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor(primaryColorDark));
            }

            if (actionBarTextColor != null && actionBarTextColor.length() > 0) {
                actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>" + getString(R.string.app_name) + "</font>"));
            }
        }
    }

    private String getThemePrimaryColor(int inThemeId) {
        String result;
        int[] attrs = {R.attr.colorPrimary};
        TypedArray typedArray = obtainStyledAttributes(inThemeId, attrs);

        result = typedArray.getString(0);
        typedArray.recycle();

        return result;
    }

    private String getThemePrimaryColorDark(int inThemeId) {
        String result;
        int[] attrs = {R.attr.colorPrimaryDark};
        TypedArray typedArray = obtainStyledAttributes(inThemeId, attrs);

        result = typedArray.getString(0);
        typedArray.recycle();

        return result;
    }

    private String getThemeActionBarTextColor(int inThemeId) {
        String result;
        int[] attrs = {R.attr.textColor};
        TypedArray typedArray = obtainStyledAttributes(inThemeId, attrs);

        result = typedArray.getString(0);
        typedArray.recycle();

        return result;
    }
}
