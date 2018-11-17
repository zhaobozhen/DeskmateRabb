package com.absinthe.deskmaterabb.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.absinthe.deskmaterabb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

public class ChangeColorsUtil {
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public static void changeActionBarColor(Context context, Window window, Toolbar toolbar, int inThemeId) {
        if (toolbar != null) {
            String primaryColor;
            String primaryColorDark;

            primaryColor = getThemePrimaryColor(context, inThemeId);
            primaryColorDark = getThemePrimaryColorDark(context, inThemeId);

            if (primaryColor != null && primaryColor.length() > 0) {
                toolbar.setBackground(new ColorDrawable(Color.parseColor(primaryColor)));
            }

            if (primaryColorDark != null && primaryColorDark.length() > 0) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor(primaryColorDark));
            }
        }
    }

    private static String getThemePrimaryColor(Context context, int inThemeId) {
        String result;
        int[] attrs = {R.attr.colorPrimary};
        TypedArray typedArray = context.obtainStyledAttributes(inThemeId, attrs);

        result = typedArray.getString(0);
        typedArray.recycle();

        return result;
    }

    private static String getThemePrimaryColorDark(Context context, int inThemeId) {
        String result;
        int[] attrs = {R.attr.colorPrimaryDark};
        TypedArray typedArray = context.obtainStyledAttributes(inThemeId, attrs);

        result = typedArray.getString(0);
        typedArray.recycle();

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void changeActionbarAndStatusTextColor(Window window, TextView title,  int flag) {
        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();

        switch (flag) {
            case BLACK:
                title.setTextColor(Color.BLACK);
                ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                break;
            case WHITE:
                title.setTextColor(Color.WHITE);
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                break;
        }
        decor.setSystemUiVisibility(ui);
    }

    public static void changeBottomNavigationBarColor(Context context, BottomNavigationView mBnv, int inThemeId) {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] attrs = {R.attr.colorPrimary};
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(inThemeId, attrs);

        int[] colors = new int[]{context.getResources().getColor(R.color.black),
                context.getResources().getColor(typedArray.getResourceId(0, 0))
        };
        ColorStateList csl = new ColorStateList(states, colors);
        mBnv.setItemTextColor(csl);
        mBnv.setItemIconTintList(csl);
    }
}
