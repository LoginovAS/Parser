package org.sbx.objects;

import org.sbx.interfaces.Buildable;

/**
 * Created by aloginov on 20.10.16.
 */
public class DBRecord extends Record implements Buildable {
    public void save() {

        saved();
    }

    public void load() {

    }
}
