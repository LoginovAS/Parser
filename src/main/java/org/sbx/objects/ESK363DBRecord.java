package org.sbx.objects;

/**
 * Created by aloginov on 21.10.16.
 */
public final class ESK363DBRecord extends DBRecord {

    private int itemCount;

    public int getItemCount(){
        return itemCount;
    }

    public void setItemCount(int itemCount){
        this.itemCount = itemCount;
    }
}
