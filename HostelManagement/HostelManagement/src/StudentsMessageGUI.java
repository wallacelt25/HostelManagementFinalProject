import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class StudentsMessageGUI extends JDialog implements ActionListener {

    public StudentsMessageGUI() {

        Font font = new Font("Times new Roman", Font.BOLD, 20);

        // Frame properties;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Student Applications");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Heading label
        JLabel heading = new JLabel("Student Applications");
        heading.setPreferredSize(new Dimension(0, 40));
        heading.setFont(font);
        this.add(heading, BorderLayout.NORTH);
        heading.setHorizontalAlignment(JLabel.CENTER);

        // Table shows Applications
        try {
            FilesHandler handler = new FilesHandler();
            JTable table = new JTable(handler.studentMessagesArray, new Object[]{"Student name", "Application"});
            table.setFont(new Font("Consolas", Font.PLAIN, 14));
            this.add(table, BorderLayout.CENTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // close button to close this dialog
        JButton closeButton = new JButton("Close");
        this.add(closeButton, BorderLayout.PAGE_END);
        closeButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
