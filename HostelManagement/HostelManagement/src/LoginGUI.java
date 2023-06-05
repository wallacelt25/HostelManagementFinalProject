import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.List;

public class LoginGUI extends JFrame implements ActionListener {

    private JLabel titleLabel;

    JLabel label1;
    JLabel label2;

    JTextField userTextField;
    JTextField passTextField;

    JButton adminButton;
    JButton studentButton;

    FilesHandler handler;

    GridBagConstraints c;

    public LoginGUI() throws Exception {

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
        this.setTitle("Login Page");
        this.setLayout(layout);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set title label;
        titleLabel = new JLabel("Login");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(font);

        // username
        label1 = new JLabel("Username:");
        userTextField = new JTextField();
        userTextField.setPreferredSize(new Dimension(200, 40));

        // password
        label2 = new JLabel("Password:");
        passTextField = new JPasswordField();
        passTextField.setPreferredSize(new Dimension(200, 40));

        // Admin login button;
        adminButton = new JButton("Admin Login");
        adminButton.addActionListener(this);

        // Student login button;
        studentButton = new JButton("Student Login");
        studentButton.addActionListener(this);

        // Create account button;
        JLabel accountButton = new JLabel("Create Account");
        accountButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        accountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginGUI.this.dispose();
                try {
                    new CreateAccountGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
        c.insets = new Insets(10, 30, 10, 30);
        this.add(adminButton, c);
        c.gridy++;
        c.insets = new Insets(5, 30, 5, 30);
        this.add(studentButton, c);
        c.gridy++;
        c.insets = new Insets(10, 30, 10, 30);
        this.add(accountButton, c);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==adminButton) {
            String username = userTextField.getText();
            String password = passTextField.getText();

            // check data
            if (username.equals("admin") && password.equals("password")) {
                this.dispose();
                new AdminGUI();
            }

            // incorrect
            else {
                JOptionPane.showMessageDialog(LoginGUI.this,
                        "Invalid username or password. Please try again.");
            }
        }

        else {
            String username = userTextField.getText();
            String password = passTextField.getText();

            Student student = null;
            List<Student> list = handler.studentsData;

            for(Student std: list) {
                // Validate the login;
                if (username.equalsIgnoreCase(std.studentName) && password.equals(std.password)) {
                    this.dispose();
                    try {
                        new StudentsGUI(std);
                    } catch (FileNotFoundException ex) {
                    }
                    return;
                }
            }

            // If invalid user
            JOptionPane.showMessageDialog(this,
                    "Wrong username or password");
        }
    }
}
