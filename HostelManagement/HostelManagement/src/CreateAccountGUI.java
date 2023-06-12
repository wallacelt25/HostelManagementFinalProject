import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateAccountGUI extends JFrame implements ActionListener {

    JLabel label1;
    JLabel label2;

    JTextField userTextField;
    JTextField passTextField;

    JButton createStudentAccount;

    FilesHandler handler;

    GridBagConstraints c;


    public CreateAccountGUI() throws Exception {
        Font font = new Font("Times new Roman", Font.BOLD, 30);

        // set layout
        GridBagLayout layout = new GridBagLayout();
        c = new GridBagConstraints();

        // constraints of layout
        c.insets = new Insets(10, 30, 10, 30);
        c.gridy = 0;
        c.gridx = 0;

        handler = new FilesHandler();

        // Set frame properties
        this.setTitle("Sign up");
        this.setLayout(layout);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set title label;
        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(font);

        // username
        label1 = new JLabel("Username:");
        userTextField = new JTextField();
        userTextField.setPreferredSize(new Dimension(300, 40));

        // password
        label2 = new JLabel("Password:");
        passTextField = new JPasswordField();
        passTextField.setPreferredSize(new Dimension(200, 40));

        // Student login button
        createStudentAccount = new JButton("Create Account");
        createStudentAccount.addActionListener(this);

        // label with action to back to Login
        JLabel haveAccount = new JLabel("Already have an account");
        haveAccount.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        haveAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateAccountGUI.this.dispose();
                try {
                    new LoginGUI();
                } catch (Exception ex) {
                }
            }
        });

        // adding components on frame and setting their positions
        this.add(titleLabel, c);
        c.gridy++;
        this.add(label1, c);
        c.gridy++;
        c.insets = new Insets(5, 30, 5, 30);
        this.add(userTextField, c);
        c.gridy++;
        c.insets = new Insets(10, 30, 10, 30);
        this.add(label2, c);
        c.gridy++;
        c.insets = new Insets(5, 30, 5, 30);
        this.add(passTextField, c);
        c.gridy++;
        c.insets = new Insets(5, 30, 5, 30);
        this.add(createStudentAccount, c);
        c.gridy++;
        c.insets = new Insets(10, 30, 10, 30);
        this.add(haveAccount, c);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // getting data from text fields
        String username = userTextField.getText();
        String password = passTextField.getText();

        if(username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Username and password are required");
            return;
        }

        int option = JOptionPane.showConfirmDialog(null,
                "Pay the hostel fees to get room from Admin.",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (option != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "You need to pay the fees to get the room");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "12500 has been paid in hostel fees\n" +
                    "You can get the room from admin");
        }

        FilesHandler readerAndWriter = null;
        try {
            readerAndWriter = new FilesHandler();
            // Adding a new student into text file
            readerAndWriter.addNewStudent(username + "," + password + "," + "none,paid");
        } catch (Exception ex) {
        }

        // Back to login
        try {
            new LoginGUI();
        } catch (Exception ex) {
        }
    }
}
