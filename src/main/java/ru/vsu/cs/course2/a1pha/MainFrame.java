package ru.vsu.cs.course2.a1pha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel paintAreaPanel;
    private DrawPanel drawPanel;
    private int frameWidth, frameHeight;

    private Timer timer = new Timer(40, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            drawPanel.motion();
        }
    });

    public MainFrame() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        frameWidth = 1600;
        frameHeight = 900;
//        frameWidth = 800;
//        frameHeight = 600;
        drawPanel = new DrawPanel(frameWidth, frameHeight);
        paintAreaPanel.add(drawPanel);
        setMinimumSize(new Dimension(frameWidth, frameHeight));
        setMaximumSize(new Dimension(frameWidth, frameHeight));
        setResizable(false);
        timer.start();
        buttonOK.addActionListener(action ->{
            drawPanel.toggleInvasion();
        });
    }

    public static void main(String[] args) {
        MainFrame dialog = new MainFrame();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
