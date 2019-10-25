package com.tcqq.navigationheaderview;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by raphaelbussa on 17/01/17.
 */

class ProfileChooser extends Dialog {

    private ProfileChooserCallback callback;

    @SuppressLint("RtlHardcoded")
    ProfileChooser(Context context, SparseArray<Profile> profileSparseArray, ArrayList<Item> items, int accent, boolean showAdd, String titleValue, int icon, Typeface tf) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.hw_account_chooser);
        boolean hvIsRTL = context.getResources().getBoolean(R.bool.is_right_to_left);
        LinearLayout linearLayout = findViewById(R.id.hw_profile_container);
        LinearLayout linearLayout1 = findViewById(R.id.hw_action_container);
        TextView title = findViewById(R.id.hw_dialog_title);
        ImageView add = findViewById(R.id.hv_add_profile);
        add.setImageResource(icon);
        title.setTextColor(Utils.getTextColorPrimary(context));
        title.setText(titleValue);
        title.setGravity(Gravity.CENTER_VERTICAL | (hvIsRTL ? Gravity.RIGHT : Gravity.LEFT));
        if (tf != null) title.setTypeface(tf);
        add.setVisibility(showAdd ? View.VISIBLE : View.INVISIBLE);
        add.setColorFilter(Utils.getTextColorPrimary(context));
        add.setBackgroundResource(Utils.selectableItemBackgroundBorderless(context));
        add.setOnClickListener(v -> {
            if (callback != null) {
                if (callback.onAdd()) {
                    dismiss();
                }
            }
        });
        for (int i = 0; i < profileSparseArray.size(); i++) {
            Profile profile = profileSparseArray.valueAt(i);
            if (profile.getId() != 1) {
                RowProfileView profileView = new RowProfileView(context);
                profileView.setTypeface(tf);
                profileView.setProfile(profile, i == 0);
                profileView.setAccent(accent);
                profileView.setOnClickListener(v -> {
                    RowProfileView rowProfileView = (RowProfileView) v;
                    if (callback != null) {
                        if (callback.onSelect(rowProfileView.getProfile().getId(), rowProfileView.isActive())) {
                            dismiss();
                        }
                    }
                });
                linearLayout.addView(profileView);
            }
        }
        int padding = context.getResources().getDimensionPixelSize(R.dimen.hv_item_padding);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (Item item : items) {
            TextView textView = new TextView(context);
            textView.setText(item.getTitle());
            if (tf != null) textView.setTypeface(tf);
            textView.setTag(item.getId());
            textView.setBackgroundResource(Utils.selectableItemBackground(context));
            textView.setPadding(padding, padding / 2, padding, padding / 2);
            textView.setTextColor(Utils.getTextColorSecondary(context));
            textView.setGravity(Gravity.CENTER_VERTICAL | (hvIsRTL ? Gravity.RIGHT : Gravity.LEFT));
            textView.setOnClickListener(v -> {
                int id = (int) v.getTag();
                if (callback != null) {
                    if (callback.onItem(id)) {
                        dismiss();
                    }
                }
            });
            linearLayout1.addView(textView, layoutParams);
        }
    }

    void setCallback(ProfileChooserCallback callback) {
        this.callback = callback;
    }

}
