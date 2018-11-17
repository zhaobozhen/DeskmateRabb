package com.absinthe.deskmaterabb;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.absinthe.deskmaterabb.fragment.PussFragment;
import com.absinthe.deskmaterabb.fragment.RabbFragment;
import com.absinthe.deskmaterabb.fragment.SettingsFragment;
import com.absinthe.deskmaterabb.utils.ChangeColorsUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener ,PussFragment.OnFragmentInteractionListener,RabbFragment.OnFragmentInteractionListener,SettingsFragment.OnFragmentInteractionListener{

    private ViewPager mViewpager;
    private BottomNavigationView navigation;
    private List<Fragment> fragmentList  = new ArrayList<>();
    private Toolbar toolbar;
    private Window window;
    private TextView title;

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

        toolbar = findViewById(R.id.toolbar);
        window = this.getWindow();
        title = findViewById(R.id.title);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onPageSelected(int position) {
        navigation.getMenu().getItem(position).setChecked(true);
        switch (position) {
            case 0:
                ChangeColorsUtil.changeActionBarColor(this, window, toolbar, R.style.PussTheme);
                ChangeColorsUtil.changeActionbarAndStatusTextColor(window, title, ChangeColorsUtil.WHITE);
                ChangeColorsUtil.changeBottomNavigationBarColor(this, navigation, R.style.PussTheme);
                break;
            case 1:
                ChangeColorsUtil.changeActionBarColor(this, window, toolbar, R.style.RabbTheme);
                ChangeColorsUtil.changeActionbarAndStatusTextColor(window, title, ChangeColorsUtil.WHITE);
                ChangeColorsUtil.changeBottomNavigationBarColor(this, navigation, R.style.RabbTheme);
                break;
            case 2:
                ChangeColorsUtil.changeActionBarColor(this, window, toolbar, R.style.SettingsTheme);
                ChangeColorsUtil.changeActionbarAndStatusTextColor(window, title, ChangeColorsUtil.BLACK);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
