import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

class studentClass {
    private String SID;
    private String firstName;
    private String lastName;

    public studentClass(String SID, String firstName, String lastName) {

        this.SID = SID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getSID() {
        return SID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return SID + " " + firstName + " " + lastName;
    }
}

class StudentMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Vector<String> temp = new Vector<String>();
        String UserInputs = "";
        String Choices = "";
        String FileName = "";
        String SearchFirstName = "";

        System.out.println("(-n) : Student ID \n(-f) : First Name\n(-l) : Last Name\n(-s) : Search student by their first name");
        System.out.print("Input Command and Name's file (Ex. -f Class_Roaster 67.csv) :  ");

        
        String intp = input.nextLine();
        StringTokenizer UserInputss = new StringTokenizer(intp, " ");
        Choices = UserInputss.nextToken();
        String file = UserInputss.nextToken();

        File f = new File(file);
        Scanner in = null;
        
        try {
            in = new Scanner(f);
            
            for (int i = 0; i < 7; i++) {
                in.nextLine();
            }

            Vector<studentClass> student = new Vector<studentClass>();

            
            while (in.hasNextLine()) {
                String dataLine = in.nextLine();
                StringTokenizer stu = new StringTokenizer(dataLine.trim(), ",");
                stu.nextToken(); 
                student.addElement(new studentClass(stu.nextToken(), stu.nextToken(), stu.nextToken()));
            }

       
            if (Choices.equals("-s")) {
                SearchFirstName = UserInputss.nextToken();
            }

            switch (Choices.toLowerCase()) {
                case "-n":
                
                    for (int i = 0; i < student.size(); i++) {
                        temp.add(student.elementAt(i).getSID());
                    }
                    bubbleSort(student, temp);
                    break;
                case "-f":
                    
                    for (int i = 0; i < student.size(); i++) {
                        temp.add(student.elementAt(i).getFirstName());
                    }
                    bubbleSort(student, temp);
                    break;
                case "-l":
                   
                    for (int i = 0; i < student.size(); i++) {
                        temp.add(student.elementAt(i).getLastName());
                    }
                    bubbleSort(student, temp);
                    break;
                case "-s":
                  
                    int searchResult = linearSearch(student, SearchFirstName);
                    if (searchResult != -1) {
                        System.out.println("The name " + SearchFirstName + " was found at index " + searchResult);
                    } else {
                        System.out.println("The name " + SearchFirstName + " is either spelt incorrectly or not in the database");
                    }
                    break;
                case "-e":
                    System.out.println("Exiting Program!");
                    break;
                default:
                    System.out.println("Invalid input! Please try again");
                    break;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path.");
        } finally {
            if (in != null) {
                in.close();  
            }
        }

        System.out.println();
    }

   
    public static void bubbleSort(Vector<studentClass> vector, Vector<String> temp) {
        int boundary = temp.size() - 1;
        boolean sorted = false;

        while (boundary > 0) {
            for (int i = 0; i < boundary; i++) {
                if (temp.elementAt(i).compareTo(temp.elementAt(i + 1)) > 0) {
                    String tempValue = temp.elementAt(i);
                    temp.set(i, temp.elementAt(i + 1));
                    temp.set(i + 1, tempValue);

                    studentClass realTempValue = vector.elementAt(i);
                    vector.set(i, vector.elementAt(i + 1));
                    vector.set(i + 1, realTempValue);
                    sorted = true;
                }
            }
            boundary--;
            if (!sorted) {
                break;
            }
        }

       
        for (studentClass i : vector) {
            System.out.println(i);
        }
    }

    public static int linearSearch(Vector<studentClass> vector, String search) {
        int Index = -1;
        for (int i = 0; i < vector.size(); i++) {
            if (search.toLowerCase().trim().equals(vector.elementAt(i).getFirstName().toLowerCase().trim())) {
                Index = i;
            }
        }
        return Index;
    }
}