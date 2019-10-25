package com.tcqq.navigationheaderview;

/**
 * Created by raphaelbussa on 18/01/17.
 */

public class HeaderCallback implements ProfileChooserCallback {

    @Override
    public boolean onSelect(int id, boolean isActive) {
        return true;
    }

    @Override
    public boolean onItem(int id) {
        return true;
    }

    @Override
    public boolean onAdd() {
        return true;
    }

}
