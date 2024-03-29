package Package;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
    	//Step 1:
        File inputFile = new File("TextFile.txt");
        File outputFile = new File("SortedFile.txt");

        try {
            inputFile.createNewFile();
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Step 2:
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //Takes Data from the Console
        		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile)) //Writes data onto the file
        	) {

            System.out.println("Enter data to be sorted (Press Enter on an empty line to finish):");
            String line;
            while (!(line = reader.readLine()).isEmpty()) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush(); // Flush to ensure writing to the file immediately

            //Step 3:
            ArrayList<String> data = new ArrayList<>();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
                String dataLine;
                while (fileReader.ready()) {
                	dataLine = fileReader.readLine();
                    data.add(dataLine); //Here we add data to the ArrayList
                }
            }

            bubbleSort(data); //Algorithm to sort the data

            // Step 4: Write sorted data to the new file
            try (BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFile))) {
                for (String sortedLine : data) {
                    outputFileWriter.write(sortedLine);
                    outputFileWriter.newLine();
                }
                outputFileWriter.flush(); // Flush to ensure writing to the file immediately
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (BufferedReader fr = new BufferedReader(new FileReader(outputFile))) {
            String dataLine;
            while (fr.ready()) {
            	dataLine = fr.readLine();
                System.out.println(dataLine); //retrieving the data to print on the console
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bubble Sort Algorithm
    private static void bubbleSort(List<String> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    String temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }
}


/*
/* Steps involves:
1. Create a new file.
2. Write the data to be sorted. This data will be entered by the user via the console and will then be copied to the file we created.
3. Once the data is entered into the file, use a `BufferedReader` to read the data and sort it alphabetically.
4. Write the sorted data to a file and display the output on the console.
*/
