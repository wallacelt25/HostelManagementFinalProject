import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AdminGUI extends JFrame implements ActionListener {

    private JButton studentRecordButton;
    private JButton roomsRecordButton;
    private JButton reqButton;
    private JButton monthlyReportButton;

    private GridBagConstraints c;

    public AdminGUI() {

        // set layout
        GridBagLayout layout = new GridBagLayout();
        c = new GridBagConstraints();

        // constraints of layout
        c.insets = new Insets(15, 30, 15, 30);
        c.gridy = 0;
        c.gridx = 0;

        // setting frame
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 500);

        Font font = new Font("Times new Roman", Font.BOLD, 30);

        // top label
        JLabel topHeading = new JLabel("Admin");
        topHeading.setFont(font);
        topHeading.setAlignmentX(Component.CENTER_ALIGNMENT);

        // This button show all students
        studentRecordButton = new JButton("Display Students");
        studentRecordButton.setPreferredSize(new Dimension(300, 50));
        studentRecordButton.addActionListener(this);
        studentRecordButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // this buttons show rooms info
        roomsRecordButton = new JButton("Rooms Data");
        roomsRecordButton.addActionListener(this);
        roomsRecordButton.setPreferredSize(new Dimension(300, 50));
        roomsRecordButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // This buttons show all applications
        reqButton = new JButton("Display Applications");
        reqButton.addActionListener(this);
        reqButton.setPreferredSize(new Dimension(300, 50));
        reqButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Show available rooms and monthly income;
        monthlyReportButton = new JButton("Monthly Report");
        monthlyReportButton.addActionListener(this);
        monthlyReportButton.setPreferredSize(new Dimension(300, 50));
        monthlyReportButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // adding all components on the frame
        this.add(topHeading, c);
        c.gridy++;
        this.add(studentRecordButton, c);
        c.gridy++;
        this.add(roomsRecordButton, c);
        c.gridy++;
        this.add(reqButton, c);
        c.gridy++;
        this.add(monthlyReportButton, c);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == studentRecordButton) {
            // SHow records of students;
            try {
                new StudentsLoginGUI();
            } catch (FileNotFoundException ex) {
            }
        }

        else if(e.getSource()==roomsRecordButton) {
            // Room Button Action
            this.dispose();
            try {
                new RoomsGUI();
            } catch (FileNotFoundException ex) {
            }

        }

        else if(e.getSource()==reqButton) {
            // SHow applications;
            new StudentsMessageGUI();
        }

        else {
            FilesHandler handler = null;
            try {
                handler = new FilesHandler();
            } catch (FileNotFoundException ex) {
            }
            int roomsInHostel = handler.roomsData.size();
            int filledRooms = roomsInHostel - handler.freeRooms;

            // Display data
            JOptionPane.showMessageDialog(this, String.format( "%-25s%-40s\n%-25s%-40s\n%-25s%-40s\n", "Monthly Revenue", filledRooms +
                    " x 12500 = " + (filledRooms * 12500), "Rooms in Hostel are:", roomsInHostel+"", "Free Rooms:", handler.freeRooms+""));
        }
    }
}
