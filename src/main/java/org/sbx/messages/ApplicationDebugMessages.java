package org.sbx.messages;

import org.sbx.interfaces.LoggingMessenger;

/**
 * Created by aloginov on 19.10.16.
 */
public enum ApplicationDebugMessages implements LoggingMessenger {

    METHOD_STARTED {
        public String getMessage(){
            return "Method started";
        }
    },

    METHOD_FINISHED {
        public String getMessage(){
            return "Method finished";
        }
    },

    CANNOT_PARSE_DATE {
        public String getMessage(){
            return "Cannot parse date from given string '{}'";
        }
    };

    public String toString(){
        return getMessage();
    }
}
