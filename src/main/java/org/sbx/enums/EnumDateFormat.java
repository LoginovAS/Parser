package org.sbx.enums;

/**
 * Created by aloginov on 26.10.16.
 */
public enum EnumDateFormat {

    DATABASE {
        public String getFormat() {
            return "yyyy-MM-dd HH:mm:ss";
        }
    };

    public abstract String getFormat();
}
