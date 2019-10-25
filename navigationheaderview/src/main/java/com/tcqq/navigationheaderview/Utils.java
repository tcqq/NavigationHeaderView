package com.tcqq.navigationheaderview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

/**
 * Created by raphaelbussa on 13/01/17.
 */

class Utils {

    static int getTextColorPrimary(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[]{16842806});
        int value = typedArray.getColor(0, 0);
        typedArray.recycle();
        return value;
    }

    static int getTextColorSecondary(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[]{16842808});
        int value = typedArray.getColor(0, 0);
        typedArray.recycle();
        return value;
    }

    static int selectableItemBackgroundBorderless(Context context) {
        TypedValue outValue = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
        } else {
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        }
        return outValue.resourceId;
    }

    static int selectableItemBackground(Context context) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        return outValue.resourceId;
    }

    static int getStatusBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    static int getColor(Context context, @ColorRes int color) {
        if (color == 0) return -1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(color, context.getTheme());
        } else {
            return context.getResources().getColor(color);
        }
    }

    @SuppressWarnings("deprecation")
    static Drawable getDrawable(Context context, @DrawableRes int drawable) {
        if (drawable == 0) return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getDrawable(drawable, context.getTheme());
        } else {
            return context.getResources().getDrawable(drawable);
        }
    }

    @SuppressWarnings("deprecation")
    static Spanned fromHtml(String value) {
        if (value == null || value.isEmpty()) {
            value = "";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(value);
        }
    }

}
