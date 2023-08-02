import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File diploma = new File("diploma.csv");

        int index = 0;

        ArrayList<DiplomaData> diplomaData = new ArrayList<>();
        ArrayList<String> programs = new ArrayList<>();
        ArrayList<String> DiplomaLanjutans = new ArrayList<>();
        ArrayList<String> KursusPengkhususans = new ArrayList<>();

        if(diploma.exists()) {
            System.out.println("File exists");

            if (diploma.canRead()) {
                System.out.println("File is readable");
            } else {
                System.out.println("File is unreadable");
            }

            try(Scanner reader = new Scanner(diploma)) {
                while (reader.hasNext()) {

                    int totalSum = 0;
                    String line = reader.nextLine();

                    if (index > 0) {

                        int[] item = new int[6];

                        String[] items = line.split(",");

                        //[String convert to Integer] all the data from 2014 to 2019
                        for (int i = 3; i <= 8; i++) {
                            item[i - 3] = Integer.parseInt(items[i]);
                        }

                        int minimum = item[0], maximum = item[0];

                        //finding total, max, min for each kursus
                        for (int i = 0; i <= 5; i++) {
                            if (item[i] > maximum) {
                                maximum = item[i];
                            }
                            if (item[i] < minimum) {
                                minimum = item[i];
                            }
                            totalSum += item[i];
                        }

                        //Assigning all the collected max,min,total to each String
                        String max = maximum + "";
                        String min = minimum + "";
                        String total = totalSum + "";

                        DiplomaData data = new DiplomaData(items[1], items[2], total, max, min);

                        diplomaData.add(data);

                        System.out.print(index + " ");
                        System.out.println(data);
                        System.out.println();
                    }
                    index++;
                }

                //Store each program
                for (DiplomaData row : diplomaData) {
                    if (!(programs.contains(row.getCatagory()))) {
                        programs.add(row.getCatagory());
                    }
                }

                //An arraylist that collects all kursus based on their program
                for (DiplomaData row : diplomaData) {
                    if (row.getCatagory().contains("Diploma Lanjutan")) {
                        DiplomaLanjutans.add(row.getName());
                    }
                    if (row.getCatagory().contains("Kursus Pengkhususan")) {
                        KursusPengkhususans.add(row.getName());
                    }
                }

                System.out.println("==============================================================================================================");

                //print out each arraylist of the program(category)
                int number=0;
                System.out.println("\nDiploma Lanjutan:");
                for(String DiplomaLanjutan: DiplomaLanjutans){
                    number++;
                    System.out.println(number+". "+DiplomaLanjutan);
                }
                int numbering=0;
                System.out.println("\nKursus Pengkhususan:");
                for(String KursusPengkhususan: KursusPengkhususans){
                    numbering++;
                    System.out.println(numbering+". "+KursusPengkhususan);
                }

                //Create a .txt file for each program category
                for (String program : programs) {
                    try (PrintWriter writer = new PrintWriter(new File(program + ".txt"))) {
                        for (DiplomaData row : diplomaData)
                            if (program.equals(row.getCatagory())) {
                                writer.println(row);
                            }
                    }
                    catch (FileNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            } catch (FileNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        }else{
            System.out.println("File does not exists");
        }
    }
}