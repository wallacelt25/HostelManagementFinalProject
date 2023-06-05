import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class StudentsLoginGUI extends JDialog {

    private FilesHandler handler = new FilesHandler();

    public StudentsLoginGUI() throws FileNotFoundException {

        // Dialog properties;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Students Table");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Heading label;
        JLabel heading = new JLabel("Students");
        heading.setFont(heading.getFont().deriveFont(16.0f));
        this.add(heading, BorderLayout.NORTH);

        // Table with student data;
        JTable table = new JTable(handler.studentsArray, new Object[]{"Name of student", "Password", "Room No", "Fees"});
        // Attached table to scroll Pane;
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
