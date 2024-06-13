package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryList {
    private List history = Collections.synchronizedList(new ArrayList());
    private static HistoryList instance = new HistoryList();
    public static HistoryList getInstance() {
        return instance;
    }

    public void addCommand(String command) {
        history.add(command);
    }

    public Object undoCommand() {
        return history.removeLast();
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < history.size(); i++) {
            result.append("  ");
            result.append(history.get(i));
            result.append("\n");
        }
        return result.toString();
    }
}
