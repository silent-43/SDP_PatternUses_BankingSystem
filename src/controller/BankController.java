package controller;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Bank;
import dao.FileAccountDAO;
import dao.AccountDAO;
import view.GUI;
import java.io.File;

public class BankController {
    private static final Bank bank = Bank.getInstance();
    private static final AccountDAO dao = new FileAccountDAO();

    public static void init() {
        // attempt to load persisted bank if file exists
        try {
            File f = new File(bank.getFilepath());
            if (f.exists()) {
                dao.load(bank.getFilepath());
            }
        } catch (Exception e) {
            System.err.println("Bank load failed: " + e.getMessage());
        }
    }

    public static void handleLogin(String user, String pass, JFrame loginFrame) {
        // simple auth; can be replaced with proper User/Session later
        if ("admin".equals(user) && ("password".equals(pass) || "admin".equals(pass))) {
            JOptionPane.showMessageDialog(loginFrame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.dispose();
            GUI.login.showMainFrame();
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void attachMainListeners() {
        // ensure components are present
        JButton addSav = GUI.loginMain.addSav;
        JButton addCur = GUI.loginMain.addCur;
        JButton addStu = GUI.loginMain.addStu;
        JButton deposit = GUI.loginMain.deposit;
        JButton withdraw = GUI.loginMain.withdraw;
        JButton display = GUI.loginMain.display;
        JButton logout = GUI.loginMain.logout;

        ActionListener addSavL = ev -> {
            JFrame parent = new JFrame();
            String name = JOptionPane.showInputDialog(parent, "Name:");
            String bal = JOptionPane.showInputDialog(parent, "Initial balance:");
            String lim = JOptionPane.showInputDialog(parent, "Max withdraw limit:");
            try {
                double b = Double.parseDouble(bal);
                double l = Double.parseDouble(lim);
                int idx = bank.addAccount(name, b, l);
                JOptionPane.showMessageDialog(parent, "Savings account added at index " + idx);
                try { dao.save(bank); } catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Save failed: "+ex.getMessage()); }
            } catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Invalid input"); }
        };

        ActionListener addCurL = ev -> {
            JFrame parent = new JFrame();
            String name = JOptionPane.showInputDialog(parent, "Name:");
            String bal = JOptionPane.showInputDialog(parent, "Initial balance:");
            String lic = JOptionPane.showInputDialog(parent, "Trade license:");
            try{
                double b=Double.parseDouble(bal);
                int idx = bank.addAccount(name,b,lic);
                JOptionPane.showMessageDialog(parent, "Current account added at index " + idx);
                try { dao.save(bank); } catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Save failed: "+ex.getMessage()); }
            }catch(Exception ex){JOptionPane.showMessageDialog(parent, "Invalid input");}
        };

        ActionListener addStuL = ev -> {
            JFrame parent = new JFrame();
            String name = JOptionPane.showInputDialog(parent, "Name:");
            String bal = JOptionPane.showInputDialog(parent, "Initial balance:");
            String inst = JOptionPane.showInputDialog(parent, "Institution name:");
            try{
                double b=Double.parseDouble(bal);
                int idx = bank.addAccount(name, inst, b, 0);
                JOptionPane.showMessageDialog(parent, "Student account added at index " + idx);
                try { dao.save(bank); } catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Save failed: "+ex.getMessage()); }
            }catch(Exception ex){JOptionPane.showMessageDialog(parent, "Invalid input");}
        };

        ActionListener depositL = ev -> {
            JFrame parent = new JFrame();
            String id = JOptionPane.showInputDialog(parent, "Account ID:");
            String amt = JOptionPane.showInputDialog(parent, "Amount:");
            try{
                double a = Double.parseDouble(amt);
                bank.deposit(id,a);
                JOptionPane.showMessageDialog(parent, "Deposited " + a + " to " + id);
                try { dao.save(bank); } catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Save failed: "+ex.getMessage()); }
            }catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Deposit error: " + ex.getMessage()); }
        };

        ActionListener withdrawL = ev -> {
            JFrame parent = new JFrame();
            String id = JOptionPane.showInputDialog(parent, "Account ID:");
            String amt = JOptionPane.showInputDialog(parent, "Amount:");
            try{
                double a=Double.parseDouble(amt);
                bank.withdraw(id,a);
                JOptionPane.showMessageDialog(parent, "Withdrew " + a + " from " + id);
                try { dao.save(bank); } catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Save failed: "+ex.getMessage()); }
            }catch(Exception ex){ JOptionPane.showMessageDialog(parent, "Withdraw error: " + ex.getMessage()); }
        };

        ActionListener displayL = ev -> {
            javax.swing.DefaultListModel<String> list = bank.display();
            StringBuilder sb = new StringBuilder();
            sb.append("Accounts:\n");
            for(int i=0;i<list.size();i++) sb.append(list.get(i)+"\n");
            JOptionPane.showMessageDialog(null, sb.toString());
        };

        ActionListener logoutL = ev -> {
            Window w = SwingUtilities.getWindowAncestor(logout);
            if (w != null) w.dispose();
            GUI.login.frame = GUI.login.createFrame();
            GUI.login.frame.setVisible(true);
        };

        addSav.addActionListener(addSavL);
        addCur.addActionListener(addCurL);
        addStu.addActionListener(addStuL);
        deposit.addActionListener(depositL);
        withdraw.addActionListener(withdrawL);
        display.addActionListener(displayL);
        logout.addActionListener(logoutL);
    }
}
