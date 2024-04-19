import java.io.*;
/**
 * File: Lab011Prob01.java
 * Class: CSCI 1302
 * Author: Wilson Tran, Dipen Patel, Rachael Caropreso
 * Created on: April 19, 2024
 * Last Modified: April 19, 2024
 * Description: Output to console and file
 */

public class Lab11Prob01 {

    public static void main(String[] args) {

        try (
            // Create input stream for file
            DataInputStream input = new DataInputStream(new FileInputStream("src/people.dat"));

            // Create output stream for file
            DataOutputStream output = new DataOutputStream(new FileOutputStream("people-copy"));
        ) {
            // Read data from the input file and print to console
            while (true) {
                int age = input.readInt();
                String name = input.readUTF();
                String address = input.readUTF();
                int zipCode = input.readInt();
                double salary = input.readDouble();

                // Print the read data to console
                System.out.printf("%d %s %s %d %.2f%n",
                        age, name, address, zipCode, salary);

                // Write the read data to the output file
                output.writeInt(age);
                output.writeUTF(name);
                output.writeUTF(address);
                output.writeInt(zipCode);
                output.writeDouble(salary);
            }
        } catch (EOFException ex) { // Catch end-of-file exception 

        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
}
