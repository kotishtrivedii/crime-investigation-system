import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrimeTrackGUI {

    private JTextArea logArea;
    private JTextArea verdictArea;
    private JLabel alertDot;
    private Timer blinkTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CrimeTrackGUI::new);
    }

    public CrimeTrackGUI() {

        Detective detective = new Detective("Kotish");
        CrimeCase crimeCase = new CrimeCase();

        JFrame frame = new JFrame("CRIMETRACK | Police Investigation System");
        frame.setSize(1200, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Color bg = new Color(11, 15, 20);
        Color panelBg = new Color(20, 25, 35);
        Color green = new Color(0, 200, 120);
        Color red = new Color(220, 50, 50);

        frame.getContentPane().setBackground(bg);

        // ===== HEADER =====
        JLabel header = new JLabel("  CRIMETRACK | POLICE INVESTIGATION SYSTEM");
        header.setForeground(green);
        header.setBackground(bg);
        header.setOpaque(true);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        frame.add(header, BorderLayout.NORTH);

        // ===== CASE DETAILS =====
        JTextArea caseArea = new JTextArea(crimeCase.getCaseSummary());
        styleText(caseArea, panelBg, Color.WHITE);

        // ===== SUSPECT LIST =====
        DefaultListModel<String> suspects = new DefaultListModel<>();
        suspects.addElement("Aman – Childhood Friend");
        suspects.addElement("Rohan – Business Partner");
        suspects.addElement("Suresh – Neighbour");
        suspects.addElement("Mehul – Warehouse Guard");
        suspects.addElement("Karan – Financial Advisor");
        suspects.addElement("Vikram – Former Employee");

        JList<String> suspectList = new JList<>(suspects);
        suspectList.setBackground(panelBg);
        suspectList.setForeground(Color.WHITE);
        suspectList.setFont(new Font("Consolas", Font.PLAIN, 14));

        // ===== GLOBE PANEL =====
        JPanel globePanel = new JPanel(new BorderLayout());
        globePanel.setBackground(panelBg);
        globePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JLabel globeTitle = new JLabel(" GLOBAL INTELLIGENCE MAP", JLabel.CENTER);
        globeTitle.setForeground(green);
        globeTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel globeIcon = new JLabel("🌍", JLabel.CENTER);
        globeIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 120));
        globeIcon.setForeground(Color.LIGHT_GRAY);

        JLabel globeInfo = new JLabel(
                "<html><center>Surveillance<br>Location Tracking<br>Intel Feeds</center></html>",
                JLabel.CENTER
        );
        globeInfo.setForeground(Color.LIGHT_GRAY);

        globePanel.add(globeTitle, BorderLayout.NORTH);
        globePanel.add(globeIcon, BorderLayout.CENTER);
        globePanel.add(globeInfo, BorderLayout.SOUTH);

        // ===== CENTER LAYOUT =====
        JPanel centerPanel = new JPanel(new GridLayout(1,3,10,10));
        centerPanel.setBackground(bg);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        centerPanel.add(wrapPanel("Case Details", new JScrollPane(caseArea)));
        centerPanel.add(globePanel);
        centerPanel.add(wrapPanel("Suspect Database", new JScrollPane(suspectList)));

        frame.add(centerPanel, BorderLayout.CENTER);

        // ===== LOG AREA =====
        logArea = new JTextArea();
        styleText(logArea, Color.BLACK, green);
        logArea.append("> Detective Kotish initiated investigation\n");

        // ===== VERDICT AREA =====
        verdictArea = new JTextArea("Select a suspect and click ANALYZE.");
        verdictArea.setEditable(false);
        verdictArea.setFont(new Font("Consolas", Font.BOLD, 14));
        verdictArea.setBackground(new Color(15,20,25));
        verdictArea.setForeground(Color.LIGHT_GRAY);
        verdictArea.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        // ===== ALERT DOT =====
        alertDot = new JLabel("●");
        alertDot.setFont(new Font("Segoe UI", Font.BOLD, 22));
        alertDot.setForeground(Color.GRAY);
        alertDot.setHorizontalAlignment(JLabel.CENTER);

        JPanel alertPanel = new JPanel(new BorderLayout());
        alertPanel.setBackground(bg);
        alertPanel.add(new JLabel(" ALERT STATUS", JLabel.CENTER), BorderLayout.NORTH);
        alertPanel.add(alertDot, BorderLayout.CENTER);

        // ===== BOTTOM PANEL =====
        JPanel bottomPanel = new JPanel(new GridLayout(1,3,5,5));
        bottomPanel.setBackground(bg);
        bottomPanel.add(wrapPanel("Investigation Log", new JScrollPane(logArea)));
        bottomPanel.add(wrapPanel("Investigation Verdict", new JScrollPane(verdictArea)));
        bottomPanel.add(alertPanel);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // ===== BLINK TIMER =====
        blinkTimer = new Timer(500, e -> {
            alertDot.setVisible(!alertDot.isVisible());
        });

        // ===== ANALYZE BUTTON =====
        JButton analyze = new JButton("ANALYZE SELECTED SUSPECT");
        analyze.setBackground(Color.DARK_GRAY);
        analyze.setForeground(green);
        analyze.setFont(new Font("Segoe UI", Font.BOLD, 15));

        analyze.addActionListener(e -> {

            String selected = suspectList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(frame, "Select a suspect first.");
                return;
            }

            String suspectName = selected.split("–")[0].trim();

            logArea.append("> Interrogating " + suspectName + "\n");

            String result = crimeCase.analyzeSuspect(suspectName);
            verdictArea.setText(result);

            if (result.contains("KILLER")) {
                verdictArea.setForeground(red);
                alertDot.setForeground(red);
                blinkTimer.start();
            } else {
                verdictArea.setForeground(green);
                alertDot.setForeground(green);
                blinkTimer.stop();
                alertDot.setVisible(true);
            }

            logArea.append("> Analysis completed\n\n");
        });

        frame.add(analyze, BorderLayout.WEST);
        frame.setVisible(true);
    }

    // ===== UTIL METHODS =====
    private void styleText(JTextArea area, Color bg, Color fg) {
        area.setEditable(false);
        area.setBackground(bg);
        area.setForeground(fg);
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        area.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private JPanel wrapPanel(String title, Component c) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(20,25,35));
        JLabel label = new JLabel(" " + title);
        label.setForeground(new Color(0,200,120));
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);
        panel.add(c, BorderLayout.CENTER);
        return panel;
    }
}
//The CrimeTrackGUI class creates a Swing-based GUI for a police investigation system.
//It integrates components like case details, suspect list, and analysis results,