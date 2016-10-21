package org.sbx.objects;

import org.sbx.interfaces.Buildable;

/**
 * Created by aloginov on 21.10.16.
 */
public final class ESK363LogRecord extends LogRecord implements Buildable {

    private int itemCount;

    public int getItemCount(){
        return itemCount;
    }

    public void setItemCount(int itemCount){
        this.itemCount = itemCount;
    }
}
