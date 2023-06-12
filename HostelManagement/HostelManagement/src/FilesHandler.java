import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesHandler {

    private Scanner sc;

    // Lists to store data of files
    List<Room> roomsData = new ArrayList<>();
    List<StudentsMessage> studentMessagesData = new ArrayList<>();
    List<Student> studentsData = new ArrayList<>();

    // two-dimensional arrays to display data on JTables in GUI
    String[][] roomArray;
    String[][] studentMessagesArray;
    String[][] studentsArray;

    int freeRooms = 0;

    // Constructor;
     public FilesHandler() throws FileNotFoundException {

         // read text files
         getRoomsInfo();
         getMessagesInfo();
         getHostelStudentsInfo();

         // storing data of ArrayLists into two-D arrays
         convertRoomsInfo();
         convertMessagesInfo();
         convertHostelStudentsInfo();
    }

    private void getRoomsInfo() throws FileNotFoundException {
        // Reading data of rooms
        sc = new Scanner(new File("C://Users//62819//Desktop//HostelManagement//HostelManagement//src//rooms.txt"));
        while (sc.hasNextLine()) {
            String data[] = sc.nextLine().split(",");
            roomsData.add(new Room(data[0], data[1], data[2]));

            // Storing available rooms in freeRooms
            if(data[1].equals("available")) freeRooms++;
        }

        sc.close();
    }

    // Converting room list data into 2D array for table
    public void convertRoomsInfo() {

        roomArray = new String[roomsData.size()][3];
        int i=0;

        // store data into 2-D array
        for(Room room: this.roomsData) {
            roomArray[i][0] = room.roomNo;
            roomArray[i][1] = room.living;
            roomArray[i][2] = room.studentName;

            i++;
        }
    }

    public void writeRoomData() throws IOException {

        StringBuilder sb = new StringBuilder("");

        // Store data into sb
        for(Room room : roomsData) sb.append(room.roomNo + "," +
                room.living + "," + room.studentName + "\n");

        // store data into file
        FileWriter writer = new FileWriter("rooms.txt");
        writer.write(sb.toString());
        writer.close();

    }

    private void getMessagesInfo() throws FileNotFoundException {

        // Student Applications data
        sc = new Scanner(new File("C://Users//62819//Desktop//HostelManagement//HostelManagement//src//application.txt"));

        String stName = "", req = "";
        while (true) {
            if(sc.hasNextLine()) stName = sc.nextLine();
            else break;
            if(sc.hasNextLine()) req = sc.nextLine();
            else break;
            studentMessagesData.add(new StudentsMessage(stName, req));
        }
    }

    // Convert into Two-D array
    public void convertMessagesInfo() {

        studentMessagesArray = new String[studentMessagesData.size()][2];

        int i=0;
        for(StudentsMessage message: studentMessagesData) {
            studentMessagesArray[i][0] = message.student;
            studentMessagesArray[i][1] = message.message;
            i++;
        }
    }

    // Converting student list data into 2D array for table;
    public void convertHostelStudentsInfo() {

        studentsArray = new String[studentsData.size()][4];

        int i=0;
        for(Student student: studentsData) {
            studentsArray[i][0] = student.studentName;
            studentsArray[i][1] = student.password;
            studentsArray[i][2] = student.roomNo;
            studentsArray[i][3] = student.fee;

            i++;
        }
    }

    public void getHostelStudentsInfo() throws FileNotFoundException {
            // Reading data from student file
            sc = new Scanner(new File("C://Users//62819//Desktop//HostelManagement//HostelManagement//src//students.txt"));
            while (sc.hasNextLine()) {
                String data[] = sc.nextLine().split(",");
                studentsData.add(new Student(data[0], data[1], data[2], data[3]));
            }
            sc.close();
    }

    public void addNewStudent(String data) throws IOException {

        // Add data to text file
        FileWriter writer = new FileWriter("C://Users//62819//Desktop//HostelManagement//HostelManagement//src//students.txt", true);
        writer.append(data + "\n");
        writer.close();

        // update data into List and array
        getHostelStudentsInfo();
        convertHostelStudentsInfo();
    }

    public void writeApplicationData() throws IOException {

        StringBuilder sb = new StringBuilder("");
        // Store all data into sb
        for(StudentsMessage application : studentMessagesData) sb.append(application.student +
                "\n" + application.message + "\n");

        // Writing data on file;
        FileWriter writer = new FileWriter("C://Users//62819//Desktop//HostelManagement//HostelManagement//src//application.txt");
        writer.write(sb.toString());
        writer.close();

    }

}
