package com.tcqq.navigationheaderview;

/**
 * Created by raphaelbussa on 21/01/17.
 */

interface ProfileChooserCallback {

    boolean onSelect(int id, boolean isActive);

    boolean onItem(int id);

    boolean onAdd();

}
