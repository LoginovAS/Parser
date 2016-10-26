package org.sbx.service;

/**
 * Created by aloginov on 19.10.16.
 */
public class StringService {

    public static String getExecutionListenerRegExp(){
        String string = "((\\d{2})\\.(\\d{2})\\.(\\d{4})\\s+(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3}))\\s+" +
                "(.+)\\s+(INFO)\\s+(com.\\w+.batch.listener.ExecutionListener -)\\s+(\\d+)";

        return string;
    }
}
