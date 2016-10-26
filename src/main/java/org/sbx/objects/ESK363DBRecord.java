package org.sbx.objects;

import org.sbx.interfaces.Buildable;

/**
 * Created by aloginov on 21.10.16.
 */
public class ESK363DBRecord extends DBRecord implements Buildable {

    private int itemCount;

    public int getItemCount(){
        return itemCount;
    }

    public void setItemCount(int itemCount){
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return getDate() + " " + getLogLevel() + " " + getItemCount();
    }
}
