package com.epicodus.bookview.util;

/**
 * Created by Guest on 12/14/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
