package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SingletonGui implements ActionListener {
    private JFrame mainFrame;
    private JTextArea display;
    private JButton newContact, newAppointment, undo, refresh, exit;
    private JPanel controlPanel, displayPanel;
    private static int historyCount;

    public void createGui() {
        mainFrame = new JFrame("Singleton Pattern Example");
        Container content = mainFrame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        displayPanel = new JPanel();
        display = new JTextArea(20, 60);
        display.setEditable(false);
        displayPanel.add(display);
        content.add(displayPanel);

        controlPanel = new JPanel();
        newContact = new JButton("Create Contact");
        newAppointment = new JButton("Create Appointment");
        undo = new JButton("Undo");
        refresh = new JButton("Refresh");
        exit = new JButton("Exit");
        controlPanel.add(newContact);
        controlPanel.add(newAppointment);
        controlPanel.add(undo);
        controlPanel.add(refresh);
        controlPanel.add(exit);
        content.add(controlPanel);

        newContact.addActionListener(this);
        newAppointment.addActionListener(this);
        undo.addActionListener(this);
        refresh.addActionListener(this);
        exit.addActionListener(this);

        mainFrame.addWindowListener(new WindowCloseManager());
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void refreshDisplay(String actionMessage) {
        display.setText(actionMessage + "\nCOMMAND HISTORY:\n" +
                HistoryList.getInstance().toString());
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if(source == newContact) {
            addCommand(" New Contact");
        } else if (source == newAppointment) {
            addCommand(" New Appointment");
        } else if (source == undo) {
            undoCommand();
        } else if (source == refresh) {
            refreshDisplay("");
        } else if(source == exit) {
            exitApplication();
        }
    }

    private class WindowCloseManager extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            exitApplication();
        }
    }

    private void addCommand(String message) {
        HistoryList.getInstance().addCommand((++historyCount) + message);
        refreshDisplay("Add Command: " + message);
    }

    private void undoCommand() {
        Object result = HistoryList.getInstance().undoCommand();
        historyCount--;
        refreshDisplay("Undo Command: " + result);
    }

    private void exitApplication(){
        System.exit(0);
    }
}
