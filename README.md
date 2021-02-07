[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)
[![JitPack](https://jitpack.io/v/tcqq/NavigationHeaderView.svg)](https://jitpack.io/#tcqq/NavigationHeaderView)
[![Licence](https://img.shields.io/badge/Licence-Apache2-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

# NavigationHeaderView
HeaderView for NavigationView.

# Usage
#### Via XML
Create a layout named like this header_drawer.xml
```
<com.tcqq.navigationheaderview.HeaderView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/header_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:hv_add_icon="@drawable/ic_action_settings"
    app:hv_add_status_bar_height="true"
    app:hv_background_color="@color/colorPrimaryDark"
    app:hv_dialog_title="@string/account"
    app:hv_highlight_color="@color/colorAccent"
    app:hv_profile_avatar="@drawable/ic_placeholder"
    app:hv_profile_background="@drawable/ic_placeholder_bg"
    app:hv_profile_email="batman@gotham.city"
    app:hv_profile_username="Bruce Wayne"
    app:hv_show_add_button="true"
    app:hv_show_arrow="true"
    app:hv_show_gradient="true"
    app:hv_style="normal"
    app:hv_theme="light" />
```
And in your NavigationView

```
<com.google.android.material.navigation.NavigationView
    android:id="@+id/nav_view"
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    app:headerLayout="@layout/header_drawer"
    app:menu="@menu/drawer" />
```
#### Manage Profiles
The new HeaderView manage different profile and a new profile chooser inspired from YouTube android app
- Create a profile
```
Profile profile = new Profile.Builder()
        .setId(2)
        .setUsername("Raphaël Bussa")
        .setEmail("raphaelbussa@gmail.com")
        .setAvatar("https://github.com/rebus007.png?size=512")
        .setBackground("https://images.unsplash.com/photo-1473220464492-452fb02e6221?dpr=2&auto=format&fit=crop&w=767&h=512&q=80&cs=tinysrgb&crop=")
        .build();
```
- Add a profile
```
headerView.addProfile(profile);
```
- Set a profile active
```
headerView.setProfileActive(2);
```
- Remove a profile
```
headerView.removeProfile(2);
```
- Get actual active profile
```
int activeProfileId = headerView.getProfileActive();
```

#### Customize Profile Chooser
You can also customize the profile chooser
- Add bottom items
```
Item item = new Item.Builder()
        .setId(1)
        .setTitle("Remove all profile")
        .build();

headerView.addDialogItem(item);
```
- HighlightColor in dialog
```
headerView.setHighlightColor(ContextCompat.getColor(this, R.color.colorAccent));
app:hv_highlight_color="@color/colorAccent"
```
- Change dialog title
```
headerView.setDialogTitle("Choose account");
app:hv_dialog_title="Dialog title"
```
- Change dialog top icon
```
headerView.setAddIconDrawable(R.drawable.ic_action_settings);
app:hv_add_icon="@drawable/ic_action_settings"
```
- Or hide dialog top icon
```
headerView.setShowAddButton(true);
app:hv_show_add_button="true"
```
- Dismiss profile chooser dialog
```
headerView.dismissProfileChooser();
```
#### Callback
```
headerView.setCallback(new HeaderCallback() {

    @Override
    public boolean onSelect(int id, boolean isActive) {
        //return profile id selected and if is the active profile
        return true; //true for close the dialog, false for do nothing
    }

    @Override
    public boolean onItem(int id) {
        //return witch buttom item is selected
        return true; //true for close the dialog, false for do nothing
    }

    @Override
    public boolean onAdd() {
        return true; //true for close the dialog, false for do nothing
    }
});
```
#### Loading image from network
Just add this in your class Application (of course you can use your preferred libs for load images)
```
ImageLoader.init(new ImageLoader.ImageLoaderInterface() {
    @Override
    public void loadImage(Uri url, ImageView imageView, @ImageLoader.Type int type) {
        switch (type) {
            case ImageLoader.AVATAR:
                Glide.with(imageView.getContext())
                        .load(url)
                        .asBitmap()
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(imageView);
                break;
             case ImageLoader.HEADER:
                Glide.with(imageView.getContext())
                        .load(url)
                        .asBitmap()
                        .placeholder(R.drawable.ic_placeholder_bg)
                        .error(R.drawable.ic_placeholder_bg)
                        .into(imageView);
                break;
        }
    }

});
```

# Setup
#### build.gradle
```
repositories {
    maven { url 'https://jitpack.io' }
}
```
```
dependencies {
    // Using JCenter
    implementation 'com.github.tcqq:navigationheaderview:2.0.1'
}
```

License
-------

Copyright 2019 Perry Lance

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

The MIT License (MIT)

Copyright (c) 2017 Raphaël Bussa <raphaelbussa@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
