package org.sbx.messages;

import org.sbx.interfaces.LoggingMessenger;

/**
 * Created by aloginov on 19.10.16.
 */
public enum ApplicationInfoMessages implements LoggingMessenger {

    STARTING_MESSAGE {
        public String getMessage() {
            return "Application has been successfully started.";
        }
    },

    FINISHING_MESSAGE {
        public String getMessage(){
            return "Application has finished work.";
        }
    };

    public String toString(){
        return getMessage();
    }
}
