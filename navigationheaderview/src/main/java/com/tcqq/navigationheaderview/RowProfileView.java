package com.tcqq.navigationheaderview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by raphaelbussa on 16/01/17.
 */

class RowProfileView extends ViewGroup {

    private CircleImageView avatar;
    private TextView username;
    private TextView email;

    private int hvAvatarRowDimen;
    private int hvRowHeightDimen;
    private int hvRowMarginDimen;
    private int hvRowMarginTextDimen;
    private int hvTextDimen;
    private int hvRowAvatarBorderDimen;

    private Profile hvProfile;
    private boolean hvActive;
    private boolean hvIsRTL;

    private int accent = Color.BLACK;

    private Typeface typeface;

    public RowProfileView(Context context) {
        super(context);
        init();
    }

    public RowProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RowProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundResource(Utils.selectableItemBackground(getContext()));
        setupResources();
        addViews();
    }

    public void setAccent(@ColorInt int accentColor) {
        accent = accentColor;
        avatar.setBorderColor(accent);
    }

    public boolean isActive() {
        return hvActive;
    }

    public Profile getProfile() {
        return hvProfile;
    }

    public void setProfile(Profile profile, boolean active) {
        hvActive = active;
        hvProfile = profile;
        avatar.setBorderWidth(active ? hvRowAvatarBorderDimen : 0);
        if (hvActive) {
            avatar.setPadding(0, 0, 0, 0);
        } else {
            avatar.setPadding(hvRowAvatarBorderDimen, hvRowAvatarBorderDimen, hvRowAvatarBorderDimen, hvRowAvatarBorderDimen);
        }
        if (typeface != null) {
            username.setTypeface(typeface);
            email.setTypeface(typeface);
        } else {
            username.setTypeface(Typeface.DEFAULT);
            email.setTypeface(Typeface.DEFAULT);
        }
        username.setText(hvProfile.getUsername());
        email.setText(hvProfile.getEmail());
        if (hvProfile.getAvatarRes() != 0)
            avatar.setImageResource(hvProfile.getAvatarRes());
        if (hvProfile.getAvatarUri() != null)
            ImageLoader.loadImage(hvProfile.getAvatarUri(), avatar, ImageLoader.AVATAR);
    }

    @SuppressLint("RtlHardcoded")
    private void addViews() {
        LayoutParams avatarLayoutParams = new LayoutParams(hvAvatarRowDimen, hvAvatarRowDimen);
        avatar = new CircleImageView(getContext());
        avatar.setBorderColor(accent);
        avatar.setBorderWidth(hvRowAvatarBorderDimen);
        username = new TextView(getContext());
        username.setTextColor(Utils.getTextColorPrimary(getContext()));
        username.setGravity(Gravity.CENTER_VERTICAL | (hvIsRTL ? Gravity.RIGHT : Gravity.LEFT));
        username.setMaxLines(1);
        username.setEllipsize(TextUtils.TruncateAt.END);
        email = new TextView(getContext());
        email.setTextColor(Utils.getTextColorSecondary(getContext()));
        email.setGravity(Gravity.CENTER_VERTICAL | (hvIsRTL ? Gravity.RIGHT : Gravity.LEFT));
        email.setMaxLines(1);
        email.setEllipsize(TextUtils.TruncateAt.END);
        addView(avatar, avatarLayoutParams);
        addView(username);
        addView(email);
    }

    private void setupResources() {
        accent = Color.BLACK;
        hvIsRTL = getResources().getBoolean(R.bool.is_right_to_left);
        hvAvatarRowDimen = getResources().getDimensionPixelSize(R.dimen.hv_row_avatar);
        hvRowHeightDimen = getResources().getDimensionPixelSize(R.dimen.hv_row_height);
        hvRowMarginDimen = getResources().getDimensionPixelSize(R.dimen.hv_row_margin);
        hvRowMarginTextDimen = getResources().getDimensionPixelSize(R.dimen.hv_row_margin_text);
        hvTextDimen = getResources().getDimensionPixelSize(R.dimen.hv_text);
        hvRowAvatarBorderDimen = getResources().getDimensionPixelSize(R.dimen.hv_row_avatar_border);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), hvRowHeightDimen);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!changed) return;
        if (hvIsRTL) {
            avatar.layout(getMeasuredWidth() - hvRowMarginDimen - hvAvatarRowDimen,
                    getMeasuredHeight() / 2 - hvAvatarRowDimen / 2,
                    getMeasuredWidth() - hvRowMarginDimen,
                    getMeasuredHeight() / 2 + hvAvatarRowDimen / 2);
            username.layout(hvRowMarginTextDimen,
                    getMeasuredHeight() / 2 - hvTextDimen,
                    avatar.getLeft() - hvRowMarginTextDimen,
                    getMeasuredHeight() / 2);
            email.layout(hvRowMarginTextDimen,
                    getMeasuredHeight() / 2,
                    avatar.getLeft() - hvRowMarginTextDimen,
                    getMeasuredHeight() / 2 + hvTextDimen);
        } else {
            avatar.layout(hvRowMarginDimen,
                    getMeasuredHeight() / 2 - hvAvatarRowDimen / 2,
                    hvRowMarginDimen + hvAvatarRowDimen,
                    getMeasuredHeight() / 2 + hvAvatarRowDimen / 2);
            username.layout(avatar.getRight() + hvRowMarginTextDimen,
                    getMeasuredHeight() / 2 - hvTextDimen,
                    getMeasuredWidth() - hvRowMarginTextDimen,
                    getMeasuredHeight() / 2);
            email.layout(avatar.getRight() + hvRowMarginTextDimen,
                    getMeasuredHeight() / 2,
                    getMeasuredWidth() - hvRowMarginTextDimen,
                    getMeasuredHeight() / 2 + hvTextDimen);
        }
    }

    public void setTypeface(Typeface tf) {
        if (tf == null) return;
        typeface = tf;
        username.setTypeface(tf);
        email.setTypeface(tf);
    }

}
