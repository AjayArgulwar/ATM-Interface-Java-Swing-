import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM_GUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JPasswordField passwordField;
    private JButton button;
    private JLabel balanceLabel;
    private JTextField withdrawField;
    private JTextField depositField;
    private JButton balanceButton;
    private JButton withdrawButton;
    private JButton depositButton;

    private int pin = 1234;
    private double balance = 10000.00;
    private int attempts = 0;

    public ATM_GUI() {
        frame = new JFrame("MyBank ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        label = new JLabel("Enter your PIN:");
        panel.add(label);

        passwordField = new JPasswordField();
        passwordField.addActionListener(this);
        panel.add(passwordField);

        button = new JButton("Enter");
        button.addActionListener(this);
        panel.add(button);

        frame.add(panel);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button || e.getSource() == passwordField) {
            
            int userPin = Integer.parseInt(passwordField.getText());

           
            if (userPin != pin) {
                attempts++;
                if (attempts == 3) {
                    JOptionPane.showMessageDialog(frame, "Maximum attempts reached, please try again later.");
                    System.exit(0);
                }
                JOptionPane.showMessageDialog(frame, "Incorrect PIN. Please try again.");
                passwordField.setText("");
                return;
            }

            
            panel.removeAll();

            
            balanceLabel = new JLabel("Balance: $" + balance);
            panel.add(balanceLabel);

            
            balanceButton = new JButton("Check Balance");
            balanceButton.addActionListener(this);
            panel.add(balanceButton);

            
            withdrawField = new JTextField();
            panel.add(withdrawField);

           
            withdrawButton = new JButton("Withdraw");
            withdrawButton.addActionListener(this);
            panel.add(withdrawButton);

            
            depositField = new JTextField();
            panel.add(depositField);

            
            depositButton = new JButton("Deposit");
            depositButton.addActionListener(this);
            panel.add(depositButton);

            panel.revalidate();
            panel.repaint();
        }
        if (e.getSource() == balanceButton) {
          
            JOptionPane.showMessageDialog(frame, "Your balance is $" + balance);
        } else if (e.getSource() == withdrawButton) {
            try {
                double amount = Double.parseDouble(withdrawField.getText());
                if (amount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient funds.");
                } else {
                    balance -= amount;
                    JOptionPane.showMessageDialog(frame, "Please take your cash.");
                    balanceLabel.setText("Balance: $" + balance);
                    withdrawField.setText("");
                }
            }

            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount");
            }

        }

        else if (e.getSource() == depositButton) {
            try {
                double amount = Double.parseDouble(depositField.getText());

                balance += amount;
                JOptionPane.showMessageDialog(frame, "You have sucessfully deposited " + amount);
                balanceLabel.setText("Balance: $" + balance);
                depositField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount");
            }

        }
    }
}
