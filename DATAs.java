import java.util.*;
import java.io.*;

class studentClass {
    private String studentNumber;
    private String firstName;
    private String lastName;

    public studentClass(String studentNumber, String firstName, String lastName) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String toString() {
        return this.studentNumber + " " + this.firstName + " " + this.lastName;
    }
}

public class DATAs {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java DATAs <option> <input file> [search term]");
            return;
        }

        String option = args[0];
        String fileName = args[1];
        String searchName = args.length == 3 ? args[2] : null;

        try {
            Vector<studentClass> students = fileReading(fileName);

            switch (option) {
                case "-n":
                    bubbleSort(students, Comparator.comparing(studentClass::getStudentNumber));
                    break;
                case "-f":
                    bubbleSort(students, Comparator.comparing(studentClass::getFirstName));
                    break;
                case "-l":
                    bubbleSort(students, Comparator.comparing(studentClass::getLastName));
                    break;
                case "-s":
                    if (searchName == null) {
                        System.out.println("Usage for search: java DATAs -s <input file> <search term>");
                        return;
                    }
                    int index = linearSearch(students, searchName);
                    if (index != -1) {
                        System.out.println("Found at index " + index);
                    } else {
                        System.out.println("Name not found: " + searchName);
                    }
                    return;
                default:
                    System.out.println("Invalid option. Use -n, -f, -l, or -s.");
                    return;
            }

            // Print sorted students
            for (studentClass student : students) {
                System.out.println(student);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static Vector<studentClass> fileReading(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        // ข้าม 7 บรรทัดแรก
        for (int i = 0; i < 7 && scanner.hasNextLine(); i++) {
            scanner.nextLine();
        }

        Vector<studentClass> students = new Vector<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",", 3);
            if (parts.length == 3) {
                String studentNumber = parts[0].trim();
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                students.add(new studentClass(studentNumber, firstName, lastName));
            }
        }
        scanner.close();
        return students;
    }

    public static void bubbleSort(Vector<studentClass> students, Comparator<studentClass> comparator) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (comparator.compare(students.get(j), students.get(j + 1)) > 0) {
            
                    studentClass temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    public static int linearSearch(Vector<studentClass> students, String searchName) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equalsIgnoreCase(searchName)) {
                return i;
            }
        }
        return -1;
    }
}
