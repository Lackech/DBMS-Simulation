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

public class GUI extends JFrame{

    private final String TITLE = "Simulation";
    private final String NUMBER_OF_SIMULATIONS = "Number of simulations";
    private final String MAX_TIME_PER_SIMULATION = "Maximum time to run the simulation";
    private final String SLOW_MODE = "Slow Mode";
    private final String K_CONNECTIONS = "Number of connections to be handled (k)";
    private final String SYSTEM_CALLS = "Number of threads for system calls";
    private final String N_AVAILABLE_PROCESSES = "Number of available processes for query processing (n)";
    private final String P_AVAILABLE_PROCESSES = "Number of available processes for query transactions (p)";
    private final String M_AVAILABLE_PROCESSES = "Number of available processes for query executions (m)";
    private final String T_TIMEOUT = "Connection timeout (t)";
    private final String DELAY = "Delay (in seconds)";
    private final String START = "Start";

    private JTextField txtNumberOfSimulations;
    private JTextField txtMaxTimePerSimulation;
    private JTextField txtDelay;
    private JTextField txtKConnections;
    private JTextField txtSystemCalls;
    private JTextField txtNAvailableProcesses;
    private JTextField txtPAvailableProcesses;
    private JTextField txtMAvailableProcesses;
    private JTextField txtTimeout;

    private JCheckBox chkSlowMode;

    private JTextArea txtDataDisplay;

    private DBMS system;
    private int numberOfSimulations;

    public GUI() {
        super.setTitle(TITLE);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lblNumberOfSimulations = new JLabel(NUMBER_OF_SIMULATIONS);
        lblNumberOfSimulations.setFont(new Font("Normal", Font.BOLD, 18));
        txtNumberOfSimulations = new JTextField();
        txtNumberOfSimulations.setFont(new Font("Normal", Font.BOLD, 18));
        txtNumberOfSimulations.setColumns(20);
        JPanel panelNumberOfSimulations = new JPanel();
        panelNumberOfSimulations.setLayout(new BorderLayout());
        panelNumberOfSimulations.add(lblNumberOfSimulations, BorderLayout.WEST);
        panelNumberOfSimulations.add(txtNumberOfSimulations, BorderLayout.EAST);

        JLabel lblMaxTimePerSimulation = new JLabel(MAX_TIME_PER_SIMULATION);
        lblMaxTimePerSimulation.setFont(new Font("Normal", Font.BOLD, 18));
        txtMaxTimePerSimulation = new JTextField();
        txtMaxTimePerSimulation.setColumns(20);
        txtMaxTimePerSimulation.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelMaxTimePerSimulation = new JPanel();
        panelMaxTimePerSimulation.setLayout(new BorderLayout());
        panelMaxTimePerSimulation.add(lblMaxTimePerSimulation, BorderLayout.WEST);
        panelMaxTimePerSimulation.add(txtMaxTimePerSimulation, BorderLayout.EAST);

        JLabel lblSlowMode = new JLabel(SLOW_MODE);
        lblSlowMode.setFont(new Font("Normal", Font.BOLD, 18));
        chkSlowMode = new JCheckBox();
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

        JLabel lblDelay = new JLabel(DELAY);
        lblDelay.setFont(new Font("Normal", Font.BOLD, 18));
        txtDelay = new JTextField();
        txtDelay.setFont(new Font("Normal", Font.BOLD, 18));
        txtDelay.setColumns(20);
        txtDelay.setEnabled(false);
        JPanel panelDelay = new JPanel();
        panelDelay.setLayout(new BorderLayout());
        panelDelay.add(lblDelay, BorderLayout.WEST);
        panelDelay.add(txtDelay, BorderLayout.EAST);

        JLabel lblKConnections = new JLabel(K_CONNECTIONS);
        lblKConnections.setFont(new Font("Normal", Font.BOLD, 18));
        txtKConnections = new JTextField();
        txtKConnections.setColumns(20);
        txtKConnections.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelKConnections = new JPanel();
        panelKConnections.setLayout(new BorderLayout());
        panelKConnections.add(lblKConnections, BorderLayout.WEST);
        panelKConnections.add(txtKConnections, BorderLayout.EAST);



        JLabel lblNAvailableProcesses = new JLabel(N_AVAILABLE_PROCESSES);
        lblNAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        txtNAvailableProcesses = new JTextField();
        txtNAvailableProcesses.setColumns(20);
        txtNAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelNAvailableProcesses = new JPanel();
        panelNAvailableProcesses.setLayout(new BorderLayout());
        panelNAvailableProcesses.add(lblNAvailableProcesses, BorderLayout.WEST);
        panelNAvailableProcesses.add(txtNAvailableProcesses, BorderLayout.EAST);

        JLabel lblPAvailableProcesses = new JLabel(P_AVAILABLE_PROCESSES);
        lblPAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        txtPAvailableProcesses = new JTextField();
        txtPAvailableProcesses.setColumns(20);
        txtPAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelPAvailableProcesses = new JPanel();
        panelPAvailableProcesses.setLayout(new BorderLayout());
        panelPAvailableProcesses.add(lblPAvailableProcesses, BorderLayout.WEST);
        panelPAvailableProcesses.add(txtPAvailableProcesses, BorderLayout.EAST);

        JLabel lblMAvailableProcesses = new JLabel(M_AVAILABLE_PROCESSES);
        lblMAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        txtMAvailableProcesses = new JTextField();
        txtMAvailableProcesses.setColumns(20);
        txtMAvailableProcesses.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelMAvailableProcesses = new JPanel();
        panelMAvailableProcesses.setLayout(new BorderLayout());
        panelMAvailableProcesses.add(lblMAvailableProcesses, BorderLayout.WEST);
        panelMAvailableProcesses.add(txtMAvailableProcesses, BorderLayout.EAST);

        JLabel lblTimeout = new JLabel(T_TIMEOUT);
        lblTimeout.setFont(new Font("Normal", Font.BOLD, 18));
        txtTimeout = new JTextField();
        txtTimeout.setColumns(20);
        txtTimeout.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelTimeout = new JPanel();
        panelTimeout.setLayout(new BorderLayout());
        panelTimeout.add(lblTimeout, BorderLayout.WEST);
        panelTimeout.add(txtTimeout, BorderLayout.EAST);



        JButton btnStart = new JButton(START);
        btnStart.setSize(100, 100);
        btnStart.setFont(new Font("Normal", Font.BOLD, 18));
        JPanel panelStart = new JPanel();
        panelStart.add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (areParametersValid()) {
                    //Empieza

                    numberOfSimulations = Integer.parseInt(txtNumberOfSimulations.getText());
                    double maxTimePerSimulation = Double.parseDouble(txtMaxTimePerSimulation.getText());
                    double delay = 0;
                    if (chkSlowMode.isSelected())
                        delay = Double.parseDouble(txtDelay.getText());

                    final double toUseDelay = delay;
                    int kConnections = Integer.parseInt(txtKConnections.getText());
                    int availableSystemCalls = Integer.parseInt(txtSystemCalls.getText());
                    int nAvailableProcesses = Integer.parseInt(txtNAvailableProcesses.getText());
                    int pAvailableProcesses = Integer.parseInt(txtPAvailableProcesses.getText());
                    int mAvailableProcesses = Integer.parseInt(txtMAvailableProcesses.getText());
                    double timeout = Double.parseDouble(txtTimeout.getText());
                    Runnable toRun = new Runnable() {
                        @Override
                        public void run() {
                            system = new DBMS(kConnections,1,nAvailableProcesses,pAvailableProcesses,mAvailableProcesses,
                                    timeout,maxTimePerSimulation,numberOfSimulations);
                            displayLiveStatistics();
                            system.runForestRun(txtDataDisplay);
                        }
                    };
                    new Thread(toRun).start();
                } else {
                    JDialog nonValidParametersDialog = new JDialog();
                    JLabel message = new JLabel("One or more parameters are either missing or are not valid");
                    message.setFont(new Font("Normal", Font.BOLD, 20));
                    nonValidParametersDialog.add(message);
                    nonValidParametersDialog.pack();
                    nonValidParametersDialog.setVisible(true);
                }

            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 0, 20));

        mainPanel.add(panelNumberOfSimulations);
        mainPanel.add(panelMaxTimePerSimulation);
        mainPanel.add(panelSlowMode);
        mainPanel.add(panelDelay);
        mainPanel.add(panelKConnections);
        mainPanel.add(panelNAvailableProcesses);
        mainPanel.add(panelPAvailableProcesses);
        mainPanel.add(panelMAvailableProcesses);
        mainPanel.add(panelTimeout);
        mainPanel.add(panelStart);
        Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
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
        JButton btnNext = new JButton("Next");
        btnNext.setFont(new Font("Normal", Font.BOLD, 18));
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //displayAllFinalSimulationResults();
            }
        });

        JScrollPane jsp = new JScrollPane(txtDataDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(jsp);
        panel.add(btnNext);
        changeLayout(panel);
    }





    private boolean areParametersValid() {

        boolean validParameters = true;

        if (!digitValidation(txtNumberOfSimulations.getText(), false))
            validParameters = false;

        if (!digitValidation(txtMaxTimePerSimulation.getText(), true))
            validParameters = false;

        if (chkSlowMode.isSelected() && !digitValidation(txtDelay.getText(), true))
            validParameters = false;

        if (!digitValidation(txtKConnections.getText(), false))
            validParameters = false;

        if (!digitValidation(txtSystemCalls.getText(), false))
            validParameters = false;

        if (!digitValidation(txtNAvailableProcesses.getText(), false))
            validParameters = false;

        if (!digitValidation(txtPAvailableProcesses.getText(), false))
            validParameters = false;

        if (!digitValidation(txtMAvailableProcesses.getText(), false))
            validParameters = false;

        if (!digitValidation(txtTimeout.getText(), true))
            validParameters = false;

        if (!digitValidation(txtMaxTimePerSimulation.getText(), true))
            validParameters = false;

        return validParameters;
    }

    private boolean digitValidation(String number, boolean isDouble) {
        boolean isDigit = true;
        if (number.equals(""))
            isDigit = false;

        for (int i = 0; i < number.length() && isDigit; i++) {
            char currentCharacter = number.charAt(i);
            if (!Character.isDigit(currentCharacter)) {
                if (!isDouble) {
                    isDigit = false;
                } else {
                    if (currentCharacter != '.')
                        isDigit = false;
                }
            }
        }
        return isDigit;
    }

}
