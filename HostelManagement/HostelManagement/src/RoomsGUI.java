import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RoomsGUI extends JFrame implements ActionListener {

    JButton addButton;
    JButton deleteButton;
    JButton backButton;

    JLabel label;

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    private FilesHandler reader = new FilesHandler();

    public RoomsGUI() throws FileNotFoundException {

        // Set frame properties
        setTitle("JFrame with JLabel and Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create components
        label = new JLabel("Hostel Rooms Data");
        label.setFont(new Font("Times new Roman", Font.BOLD, 30));

        tableModel = new DefaultTableModel(reader.roomArray, new Object[]{"Room No", "Status", "Student Name"});
        table = new JTable(tableModel);
        table.setFont(table.getFont().deriveFont(13.0f));
        scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Add button;
        addButton = new JButton("Add Room");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        // Delete Button;
        deleteButton = new JButton("Delete Room");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        // back Button;
        backButton = new JButton("Back to Admin");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        // Add components to the frame
        this.add(label, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void saveData() throws IOException {
        Vector<Vector> data = tableModel.getDataVector();
        List<Room> list = new ArrayList<>();

        // Get table data
        for(Vector row: data) list.add(new Room(row.get(0).toString(),
                    row.get(1).toString(), row.get(2).toString()));

        // Save data
        reader.roomsData = list;
        reader.convertRoomsInfo();
        reader.writeRoomData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addButton) {
            // Add row to the table
            tableModel.addRow(new Object[]{"", "", ""});
        }

        else if(e.getSource()==deleteButton) {
            // Get the selected row and delete it
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
            }
        }

        // back button function
        else {
            try {
                saveData();
            } catch (IOException ex) {
            }
            this.dispose();
            new AdminGUI();
        }
    }
}
