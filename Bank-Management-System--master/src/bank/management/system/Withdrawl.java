package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Withdrawl extends JFrame implements ActionListener {

    String pin;
    TextField textField;

    JButton b1, b2;

    Withdrawl(String pin) {
        this.pin = pin;
        ImageIcon i3 = ImageUtil.loadIcon("icon/atm2.png", 1550, 830);
        JLabel l3 = (i3 != null) ? new JLabel(i3) : new JLabel();
        l3.setBounds(0, 0, 1550, 830);
        if (i3 == null) {
            l3.setOpaque(true);
            l3.setBackground(Color.BLACK);
        }
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 700, 35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 400, 35);
        l3.add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 260, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } else {
                    Connn c = new Connn();
                    try {
                        PreparedStatement pst1 = c.prepareStatement("SELECT * FROM bank WHERE pin = ?");
                        pst1.setString(1, pin);
                        ResultSet resultSet = pst1.executeQuery();
                        int balance = 0;
                        while (resultSet.next()) {
                            if (resultSet.getString("type").equals("Deposit")) {
                                balance += Integer.parseInt(resultSet.getString("amount"));
                            } else {
                                balance -= Integer.parseInt(resultSet.getString("amount"));
                            }
                        }
                        resultSet.close();
                        pst1.close();

                        if (balance < Integer.parseInt(amount)) {
                            JOptionPane.showMessageDialog(null, "Insuffient Balance");
                            c.close();
                            return;
                        }

                        PreparedStatement pst2 = c.prepareStatement("INSERT INTO bank(pin,date,type,amount) VALUES(?,?,?,?)");
                        pst2.setString(1, pin);
                        pst2.setString(2, date.toString());
                        pst2.setString(3, "Withdrawl");
                        pst2.setString(4, amount);
                        pst2.executeUpdate();
                        pst2.close();

                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                        setVisible(false);
                        new main_Class(pin);
                    } finally {
                        c.close();
                    }

                }
            } catch (Exception E) {

            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
