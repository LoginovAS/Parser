package org.sbx.service;

/**
 * Created by aloginov on 19.10.16.
 */
public class StringService {

    public static String getExecutionListenerRegExp(){
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("((\\d{2})\\.(\\d{2})\\.(\\d{4})\\s+");
        stringBuilder.append("(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3}))\\s+");
        stringBuilder.append("(.+)\\s+(INFO)\\s+");
        stringBuilder.append("(com.\\w+.batch.listener.ExecutionListener -)");
        stringBuilder.append("\\s+(\\d+)");

        return stringBuilder.toString();
    }
}
