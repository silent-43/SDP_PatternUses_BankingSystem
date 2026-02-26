package view;

import javax.swing.*;
import java.awt.*;
import controller.BankController;
import model.Bank;

public class GUI {
    // simple in-memory bank model accessible to GUI (Singleton)
    public static model.Bank bank = model.Bank.getInstance();

    public static class login {
        // login components exposed for controller
        public static JFrame frame = createFrame();
        public static JTextField userField;
        public static JPasswordField passField;
        public static JButton loginBtn;

        public static JFrame createFrame() {
            JFrame f = new JFrame("Banking System - Login");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(400, 250);
            f.setLocationRelativeTo(null);
            f.setLayout(new BorderLayout());

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel userLabel = new JLabel("Username:");
            userField = new JTextField(20);
            JLabel passLabel = new JLabel("Password:");
            passField = new JPasswordField(20);
            loginBtn = new JButton("Login");

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(userLabel, gbc);
            gbc.gridx = 1;
            panel.add(userField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(passLabel, gbc);
            gbc.gridx = 1;
            panel.add(passField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            panel.add(loginBtn, gbc);

            // delegate login handling to BankController
            loginBtn.addActionListener(e -> {
                String user = userField.getText();
                String pass = new String(passField.getPassword());
                BankController.handleLogin(user, pass, f);
            });

            f.getContentPane().add(panel, BorderLayout.CENTER);
            return f;
        }

        public static void showMainFrame() {
            JFrame main = new JFrame("Banking System");
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setSize(700,500);
            main.setLocationRelativeTo(null);
            main.setLayout(new BorderLayout());

            // side button panel
            JPanel btnPanel = new JPanel(new GridLayout(7,1,5,5));
            // main frame components exposed to controller
            JButton addSav = new JButton("Add Savings Account");
            JButton addCur = new JButton("Add Current Account");
            JButton addStu = new JButton("Add Student Account");
            JButton deposit = new JButton("Deposit");
            JButton withdraw = new JButton("Withdraw");
            JButton display = new JButton("Display All Accounts");
            JButton logout = new JButton("Logout");
            btnPanel.add(addSav);
            btnPanel.add(addCur);
            btnPanel.add(addStu);
            btnPanel.add(deposit);
            btnPanel.add(withdraw);
            btnPanel.add(display);
            btnPanel.add(logout);

            // expose for controller
            GUI.loginMain.addSav = addSav;
            GUI.loginMain.addCur = addCur;
            GUI.loginMain.addStu = addStu;
            GUI.loginMain.deposit = deposit;
            GUI.loginMain.withdraw = withdraw;
            GUI.loginMain.display = display;
            GUI.loginMain.logout = logout;

            // center panel with accounts display
            JPanel center = new JPanel(new BorderLayout());
            JLabel infoLabel = new JLabel("Accounts Information");
            center.add(infoLabel, BorderLayout.NORTH);

            main.getContentPane().add(btnPanel, BorderLayout.WEST);
            main.getContentPane().add(center, BorderLayout.CENTER);

            // let BankController attach listeners
            BankController.attachMainListeners();

            main.setVisible(true);
        }
    }

    // holder for main frame components exposed to controller
    public static class loginMain {
        public static JButton addSav;
        public static JButton addCur;
        public static JButton addStu;
        public static JButton deposit;
        public static JButton withdraw;
        public static JButton display;
        public static JButton logout;
    }
}
