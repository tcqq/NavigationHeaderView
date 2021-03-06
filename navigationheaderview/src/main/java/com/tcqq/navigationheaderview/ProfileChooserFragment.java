package com.tcqq.navigationheaderview;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raphaelbussa on 21/01/17.
 */

public class ProfileChooserFragment extends DialogFragment {

    static final String FRAGMENT_TAG = "HV_PROFILE_CHOOSER_FRAGMENT";

    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private TextView title;

    private ProfileChooserCallback callback;
    private Typeface typeface;
    private boolean hvIsRTL;

    static ProfileChooserFragment newInstance(SparseArray<Profile> profileSparseArray, ArrayList<Item> items, int accent, String titleValue) {
        Bundle bundle = new Bundle();
        bundle.putSparseParcelableArray("profileSparseArray", profileSparseArray);
        bundle.putParcelableArrayList("items", items);
        bundle.putInt("accent", accent);
        bundle.putString("titleValue", titleValue);
        ProfileChooserFragment fragment = new ProfileChooserFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hw_account_chooser, container, false);
        linearLayout = rootView.findViewById(R.id.hw_profile_container);
        linearLayout1 = rootView.findViewById(R.id.hw_action_container);
        title = rootView.findViewById(R.id.hw_dialog_title);
        hvIsRTL = getResources().getBoolean(R.bool.is_right_to_left);
        return rootView;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        SparseArray<Profile> profileSparseArray = bundle.getSparseParcelableArray("profileSparseArray");
        List<Item> items = bundle.getParcelableArrayList("items");
        int accent = bundle.getInt("accent");
        String titleValue = bundle.getString("titleValue");

        title.setTextColor(Utils.getTextColorPrimary(getActivity()));
        title.setText(titleValue);
        title.setGravity(Gravity.CENTER_VERTICAL | (hvIsRTL ? Gravity.RIGHT : Gravity.LEFT));
        if (typeface != null) title.setTypeface(typeface);
        if (profileSparseArray != null) {
            for (int i = 0; i < profileSparseArray.size(); i++) {
                Profile profile = profileSparseArray.valueAt(i);
                if (profile.getId() != 1) {
                    RowProfileView profileView = new RowProfileView(getActivity());
                    profileView.setTypeface(typeface);
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
        }
        if (items != null) {
            int padding = getActivity().getResources().getDimensionPixelSize(R.dimen.hv_item_padding);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            for (Item item : items) {
                TextView textView = new TextView(getActivity());
                textView.setText(item.getTitle());
                if (typeface != null) textView.setTypeface(typeface);
                textView.setTag(item.getId());
                textView.setBackgroundResource(Utils.selectableItemBackground(getActivity()));
                textView.setPadding(padding, padding / 2, padding, padding / 2);
                textView.setTextColor(Utils.getTextColorSecondary(getActivity()));
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
    }

    void setCallback(ProfileChooserCallback callback) {
        this.callback = callback;
    }

    void updateTypeface(Typeface tf) {
        if (tf == null) return;
        setTypeface(tf);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof RowProfileView) {
                ((RowProfileView) view).setTypeface(typeface);
            }
        }
        for (int i = 0; i < linearLayout1.getChildCount(); i++) {
            View view = linearLayout1.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            }
        }
        title.setTypeface(typeface);
    }

    void setTypeface(Typeface tf) {
        if (tf == null) return;
        typeface = tf;
    }

}
