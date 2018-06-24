package com.simulador.GUI;

import com.simulador.dbms.DBMS;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Interface extends JFrame {


    private JTextField txtNumberOfSimulations;
    private JTextField txtMaxTimePerSimulation;
    private JTextField txtDelay;
    private JTextField txtKConnections;
    private JTextField txtNAvailableProcesses;
    private JTextField txtPAvailableProcesses;
    private JTextField txtMAvailableProcesses;
    private JTextField txtTimeout;

    private JCheckBox chkSlowMode;

    private JTextArea txtDataDisplay;

    private DBMS system;
    private int numberOfSimulations;

    public Interface() {
        super.setTitle("Simulation");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lblNumberOfSimulations = new JLabel("Number of simulations");
        lblNumberOfSimulations.setForeground(Color.WHITE);
        lblNumberOfSimulations.setFont(new Font("Normal", Font.BOLD, 18));
        txtNumberOfSimulations = new JTextField();
        txtNumberOfSimulations.setFont(new Font("Normal", Font.BOLD, 18));
        txtNumberOfSimulations.setColumns(20);
        JPanel panelNumberOfSimulations = new JPanel();
        panelNumberOfSimulations.setLayout(new BorderLayout());
        panelNumberOfSimulations.add(lblNumberOfSimulations, BorderLayout.WEST);
        panelNumberOfSimulations.add(txtNumberOfSimulations, BorderLayout.EAST);

        JLabel lblMaxTimePerSimulation = new JLabel("Time per simulation(seconds)");
        lblMaxTimePerSimulation.setForeground(Color.WHITE);
        lblMaxTimePerSimulation.setFont(new Font("Normal", Font.BOLD, 18));
        txtMaxTimePerSimulation = new JTextField();
        txtMaxTimePerSimulation.setColumns(20);
        txtMaxTimePerSimulation.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelMaxTimePerSimulation = new JPanel();
        panelMaxTimePerSimulation.setLayout(new BorderLayout());
        panelMaxTimePerSimulation.add(lblMaxTimePerSimulation, BorderLayout.WEST);
        panelMaxTimePerSimulation.add(txtMaxTimePerSimulation, BorderLayout.EAST);

        JLabel lblSlowMode = new JLabel("Do you want slow mode?");
        lblSlowMode.setForeground(Color.WHITE);
        lblSlowMode.setFont(new Font("Normal", Font.BOLD, 18));
        chkSlowMode = new JCheckBox("Select YES to edit the seconds.Otherwise don't touch this.");
        chkSlowMode.setSelected(false);
        chkSlowMode.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (chkSlowMode.isSelected()) {
                    txtDelay.setEnabled(true);
                } else {
                    txtDelay.setEnabled(false);
                }
            }
        });
        JPanel panelSlowMode = new JPanel();
        panelSlowMode.setLayout(new BorderLayout());
        panelSlowMode.add(lblSlowMode, BorderLayout.WEST);
        panelSlowMode.add(chkSlowMode, BorderLayout.EAST);

        JLabel lblDelay = new JLabel("Delay(seconds)");
        lblDelay.setForeground(Color.WHITE);
        lblDelay.setFont(new Font("Normal", Font.BOLD, 18));
        txtDelay = new JTextField();
        txtDelay.setFont(new Font("Normal", Font.BOLD, 18));
        txtDelay.setColumns(20);
        txtDelay.setEnabled(false);
        JPanel panelDelay = new JPanel();
        panelDelay.setLayout(new BorderLayout());
        panelDelay.add(lblDelay, BorderLayout.WEST);
        panelDelay.add(txtDelay, BorderLayout.EAST);

        JLabel lblKConnections = new JLabel("Number of connection(k)");
        lblKConnections.setForeground(Color.WHITE);
        lblKConnections.setFont(new Font("Normal", Font.BOLD, 18));
        txtKConnections = new JTextField();
        txtKConnections.setColumns(20);
        txtKConnections.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelKConnections = new JPanel();
        panelKConnections.setLayout(new BorderLayout());
        panelKConnections.add(lblKConnections, BorderLayout.WEST);
        panelKConnections.add(txtKConnections, BorderLayout.EAST);


        JLabel lblNAvailableProcesses = new JLabel("Number of processes in process module(n)");
        lblNAvailableProcesses.setForeground(Color.WHITE);
        lblNAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        txtNAvailableProcesses = new JTextField();
        txtNAvailableProcesses.setColumns(20);
        txtNAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelNAvailableProcesses = new JPanel();
        panelNAvailableProcesses.setLayout(new BorderLayout());
        panelNAvailableProcesses.add(lblNAvailableProcesses, BorderLayout.WEST);
        panelNAvailableProcesses.add(txtNAvailableProcesses, BorderLayout.EAST);

        JLabel lblPAvailableProcesses = new JLabel("Number of processes in query process module(p)");
        lblPAvailableProcesses.setForeground(Color.white);
        lblPAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        txtPAvailableProcesses = new JTextField();
        txtPAvailableProcesses.setColumns(20);
        txtPAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelPAvailableProcesses = new JPanel();
        panelPAvailableProcesses.setLayout(new BorderLayout());
        panelPAvailableProcesses.add(lblPAvailableProcesses, BorderLayout.WEST);
        panelPAvailableProcesses.add(txtPAvailableProcesses, BorderLayout.EAST);

        JLabel lblMAvailableProcesses = new JLabel("Number of sentences for executioner(m)");
        lblMAvailableProcesses.setForeground(Color.WHITE);
        lblMAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        txtMAvailableProcesses = new JTextField();
        txtMAvailableProcesses.setColumns(20);
        txtMAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelMAvailableProcesses = new JPanel();
        panelMAvailableProcesses.setLayout(new BorderLayout());
        panelMAvailableProcesses.add(lblMAvailableProcesses, BorderLayout.WEST);
        panelMAvailableProcesses.add(txtMAvailableProcesses, BorderLayout.EAST);

        JLabel lblTimeout = new JLabel("Timeout");
        lblTimeout.setForeground(Color.WHITE);
        lblTimeout.setFont(new Font("Normal", Font.BOLD, 18));
        txtTimeout = new JTextField();
        txtTimeout.setColumns(20);
        txtTimeout.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelTimeout = new JPanel();
        panelTimeout.setLayout(new BorderLayout());
        panelTimeout.add(lblTimeout, BorderLayout.WEST);
        panelTimeout.add(txtTimeout, BorderLayout.EAST);


        JButton btnStart = new JButton("Run");
        btnStart.setSize(100, 100);
        btnStart.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelStart = new JPanel();
        panelStart.add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Empieza

                numberOfSimulations = Integer.parseInt(txtNumberOfSimulations.getText());
                double maxTimePerSimulation = Double.parseDouble(txtMaxTimePerSimulation.getText());
                double delay = 0;
                if (chkSlowMode.isSelected())
                    delay = Double.parseDouble(txtDelay.getText());

                final double toUseDelay = delay;
                int kConnections = Integer.parseInt(txtKConnections.getText());

                int nAvailableProcesses = Integer.parseInt(txtNAvailableProcesses.getText());
                int pAvailableProcesses = Integer.parseInt(txtPAvailableProcesses.getText());
                int mAvailableProcesses = Integer.parseInt(txtMAvailableProcesses.getText());
                double timeout = Double.parseDouble(txtTimeout.getText());
                Runnable toRun = new Runnable() {
                    @Override
                    public void run() {
                        system = new DBMS(kConnections, toUseDelay, 1, nAvailableProcesses, pAvailableProcesses, mAvailableProcesses,
                                timeout, maxTimePerSimulation, numberOfSimulations);
                        displayLiveStatistics();
                        system.runForestRun(txtDataDisplay);
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
        panelDelay.setBackground(Color.BLACK);
        mainPanel.add(panelDelay);
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
        //super.setResizable(false);
        super.setVisible(true);
    }

    private void changeLayout(JComponent component) {
        Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        component.setBorder(padding);
        super.getContentPane().removeAll();
        super.add(component);
        super.revalidate();
        super.repaint();
    }

    private void displayLiveStatistics() {
        txtDataDisplay = new JTextArea();
        txtDataDisplay.setFont(new Font("Normal", Font.BOLD, 12));
        txtDataDisplay.setEditable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.BLACK);
        JButton btnNext = new JButton("Get Statistics");
        btnNext.setFont(new Font("Normal", Font.BOLD, 18));

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //displayStatistics
            }
        });

        JScrollPane jsp = new JScrollPane(txtDataDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(jsp);
        panel.add(btnNext,BorderLayout.CENTER);
        changeLayout(panel);
    }


}
