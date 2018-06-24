package com.simulador.GUI;

import com.simulador.dbms.DBMS;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {


    private JTextField textNSimul;
    private JTextField textMaxTime;
    private JTextField textKCon;
    private JTextField textNProc;
    private JTextField textPProc;
    private JTextField textMProc;
    private JTextField textTimeOut;

    private JCheckBox slowMode;

    private JTextArea textDisplay;

    private DBMS system;
    private int numberOfSimulations;

    public Interface() {
        super.setTitle("Simulation");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lblNumberOfSimulations = new JLabel("Number of simulations");
        lblNumberOfSimulations.setForeground(Color.WHITE);
        lblNumberOfSimulations.setFont(new Font("Normal", Font.BOLD, 18));
        textNSimul = new JTextField();
        textNSimul.setFont(new Font("Normal", Font.BOLD, 18));
        textNSimul.setColumns(20);
        JPanel panelNumberOfSimulations = new JPanel();
        panelNumberOfSimulations.setLayout(new BorderLayout());
        panelNumberOfSimulations.add(lblNumberOfSimulations, BorderLayout.WEST);
        panelNumberOfSimulations.add(textNSimul, BorderLayout.EAST);

        JLabel lblMaxTimePerSimulation = new JLabel("Time per simulation(seconds)");
        lblMaxTimePerSimulation.setForeground(Color.WHITE);
        lblMaxTimePerSimulation.setFont(new Font("Normal", Font.BOLD, 18));
        textMaxTime = new JTextField();
        textMaxTime.setColumns(20);
        textMaxTime.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelMaxTimePerSimulation = new JPanel();
        panelMaxTimePerSimulation.setLayout(new BorderLayout());
        panelMaxTimePerSimulation.add(lblMaxTimePerSimulation, BorderLayout.WEST);
        panelMaxTimePerSimulation.add(textMaxTime, BorderLayout.EAST);

        JLabel lblSlowMode = new JLabel("Do you want slow mode?");
        lblSlowMode.setForeground(Color.WHITE);
        lblSlowMode.setFont(new Font("Normal", Font.BOLD, 18));
        slowMode = new JCheckBox("   Select YES to slow 1 sec. Otherwise don't touch this.    ");
        slowMode.setSelected(false);

        JPanel panelSlowMode = new JPanel();
        panelSlowMode.setLayout(new BorderLayout());
        panelSlowMode.add(lblSlowMode, BorderLayout.WEST);
        panelSlowMode.add(slowMode, BorderLayout.EAST);


        JLabel lblKConnections = new JLabel("Number of connection(k)");
        lblKConnections.setForeground(Color.WHITE);
        lblKConnections.setFont(new Font("Normal", Font.BOLD, 18));
        textKCon = new JTextField();
        textKCon.setColumns(20);
        textKCon.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelKConnections = new JPanel();
        panelKConnections.setLayout(new BorderLayout());
        panelKConnections.add(lblKConnections, BorderLayout.WEST);
        panelKConnections.add(textKCon, BorderLayout.EAST);


        JLabel lblNAvailableProcesses = new JLabel("Number of processes in process module(n)");
        lblNAvailableProcesses.setForeground(Color.WHITE);
        lblNAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        textNProc = new JTextField();
        textNProc.setColumns(20);
        textNProc.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelNAvailableProcesses = new JPanel();
        panelNAvailableProcesses.setLayout(new BorderLayout());
        panelNAvailableProcesses.add(lblNAvailableProcesses, BorderLayout.WEST);
        panelNAvailableProcesses.add(textNProc, BorderLayout.EAST);

        JLabel lblPAvailableProcesses = new JLabel("Number of processes in query process module(p)");
        lblPAvailableProcesses.setForeground(Color.white);
        lblPAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        textPProc = new JTextField();
        textPProc.setColumns(20);
        textPProc.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelPAvailableProcesses = new JPanel();
        panelPAvailableProcesses.setLayout(new BorderLayout());
        panelPAvailableProcesses.add(lblPAvailableProcesses, BorderLayout.WEST);
        panelPAvailableProcesses.add(textPProc, BorderLayout.EAST);

        JLabel lblMAvailableProcesses = new JLabel("Number of sentences for executioner(m)");
        lblMAvailableProcesses.setForeground(Color.WHITE);
        lblMAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        textMProc = new JTextField();
        textMProc.setColumns(20);
        textMProc.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelMAvailableProcesses = new JPanel();
        panelMAvailableProcesses.setLayout(new BorderLayout());
        panelMAvailableProcesses.add(lblMAvailableProcesses, BorderLayout.WEST);
        panelMAvailableProcesses.add(textMProc, BorderLayout.EAST);

        JLabel lblTimeout = new JLabel("Timeout");
        lblTimeout.setForeground(Color.WHITE);
        lblTimeout.setFont(new Font("Normal", Font.BOLD, 18));
        textTimeOut = new JTextField();
        textTimeOut.setColumns(20);
        textTimeOut.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelTimeout = new JPanel();
        panelTimeout.setLayout(new BorderLayout());
        panelTimeout.add(lblTimeout, BorderLayout.WEST);
        panelTimeout.add(textTimeOut, BorderLayout.EAST);


        JButton btnStart = new JButton("Run");
        btnStart.setSize(100, 100);
        btnStart.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelStart = new JPanel();
        panelStart.add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Empieza

                numberOfSimulations = Integer.parseInt(textNSimul.getText());
                double maxTimePerSimulation = Double.parseDouble(textMaxTime.getText());
                double delay = 0;
                if (slowMode.isSelected())
                    delay = 1;
                final double delayF = delay;
                int kConnections = Integer.parseInt(textKCon.getText());

                int nAvailableProcesses = Integer.parseInt(textNProc.getText());
                int pAvailableProcesses = Integer.parseInt(textPProc.getText());
                int mAvailableProcesses = Integer.parseInt(textMProc.getText());
                double timeout = Double.parseDouble(textTimeOut.getText());
                Runnable toRun = new Runnable() {
                    @Override
                    public void run() {
                        system = new DBMS(kConnections, 1, nAvailableProcesses, pAvailableProcesses, mAvailableProcesses,
                                timeout,delayF,maxTimePerSimulation, numberOfSimulations);
                        displayStatistics();
                        system.runForestRun(textDisplay);
                    }
                };
                new Thread(toRun).start();


            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 20));
        mainPanel.setBackground(Color.BLACK);
        panelNumberOfSimulations.setBackground(Color.BLACK);
        mainPanel.add(panelNumberOfSimulations);
        panelMaxTimePerSimulation.setBackground(Color.BLACK);
        mainPanel.add(panelMaxTimePerSimulation);
        panelSlowMode.setBackground(Color.BLACK);
        mainPanel.add(panelSlowMode);

        panelKConnections.setBackground(Color.BLACK);
        mainPanel.add(panelKConnections);
        panelNAvailableProcesses.setBackground(Color.BLACK);
        mainPanel.add(panelNAvailableProcesses);
        panelPAvailableProcesses.setBackground(Color.BLACK);
        mainPanel.add(panelPAvailableProcesses);
        panelMAvailableProcesses.setBackground(Color.BLACK);
        mainPanel.add(panelMAvailableProcesses);
        panelTimeout.setBackground(Color.BLACK);
        mainPanel.add(panelTimeout);
        panelStart.setBackground(Color.BLACK);
        mainPanel.add(panelStart);
        Border padding = BorderFactory.createEmptyBorder(30, 30, 30, 30);
        mainPanel.setBorder(padding);

        super.add(mainPanel);
        super.setSize(900, 900);
        super.setVisible(true);
    }

    private void changeLayout(JComponent component) {
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        component.setBorder(padding);
        super.getContentPane().removeAll();
        super.add(component);
        super.revalidate();
        super.repaint();
    }

    private void displayStatistics() {
        textDisplay = new JTextArea();
        textDisplay.setFont(new Font("Normal", Font.BOLD, 12));
        textDisplay.setEditable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.BLACK);


        JScrollPane sp = new JScrollPane(textDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(sp);

        changeLayout(panel);
    }


}
