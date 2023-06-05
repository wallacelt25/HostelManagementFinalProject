import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class StudentsGUI extends JFrame implements ActionListener {
    JLabel label;
    JButton studentApplicationButton;
    JButton freeRoomsButton;
    JButton personalInfoButton;
    JButton feePayButton;

    private GridBagConstraints c;

    FilesHandler handler = new FilesHandler();
    Student student = null;

    public StudentsGUI(Student student) throws FileNotFoundException {
        this.student = student;

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

        // Set frame properties
        this.setTitle("Students Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setSize(550, 500);
        this.setResizable(false);

        // Heading label
        label = new JLabel("Student Page");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(font);

        // Button sto set applications;
        studentApplicationButton = new JButton("Create Application");
        studentApplicationButton.addActionListener(this);
        studentApplicationButton.setPreferredSize(new Dimension(300, 50));
        studentApplicationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // THis button used to check free rooms;
        freeRoomsButton = new JButton("Available Rooms");
        freeRoomsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        freeRoomsButton.addActionListener(this);
        freeRoomsButton.setPreferredSize(new Dimension(300, 50));

        // Anyone can check his own details;
        personalInfoButton = new JButton("Personal Record");
        personalInfoButton.addActionListener(this);
        personalInfoButton.setPreferredSize(new Dimension(300, 50));
        personalInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pay the fees
        feePayButton = new JButton("Fee Payment");
        feePayButton.setPreferredSize(new Dimension(300, 50));
        feePayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        feePayButton.addActionListener(this);

        // adding components to the frame
        this.add(label, c);
        c.gridy++;
        this.add(studentApplicationButton, c);
        c.gridy++;
        this.add(freeRoomsButton, c);
        c.gridy++;
        this.add(personalInfoButton, c);
        c.gridy++;
        this.add(feePayButton, c);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== studentApplicationButton) {
            // Get input application;
            String req = JOptionPane.showInputDialog(this,
                    "Application", "Application", JOptionPane.INFORMATION_MESSAGE);

            // Save on file;
            handler.studentMessagesData.add(new StudentsMessage(student.studentName, req));
            try {
                handler.writeApplicationData();
            } catch (IOException ex) {
            }
            handler.convertMessagesInfo();

            // Save message;
            JOptionPane.showMessageDialog(this, "Your Application has been submitted");
        }

        else if(e.getSource()== freeRoomsButton) {
            StringBuilder sb = new StringBuilder("");

            // checking for available rooms
            List<Room> list = handler.roomsData;
            for(Room room: list) if(room.living.equals("available")) {
                    sb.append(room.roomNo + "   ");
            }

            // SHow to student;
            JOptionPane.showMessageDialog(this,
                    "Available Rooms Are: " + sb.toString());
        }

        // student data
        else if(e.getSource()== personalInfoButton) {
            JOptionPane.showMessageDialog(this, String.format("%-25s%-40s\n%-25s%-40s\n%-25s%-40s\n", "Name:",
                    student.studentName+"", "Password:", student.password+"", "Room No:", student.roomNo, "Fees Status:", student.fee
            ));
        }

        // pay fee of hostel
        else {
            handler.studentsData.remove(student);
            student.fee = "paid";

            // save data
            handler.studentsData.add(student);
            handler.convertHostelStudentsInfo();

            JOptionPane.showMessageDialog(this, "Fees has been successfully paid");
        }
    }
}
