
/**
 * File: Lab011Prob03.java
 * Class: CSCI 1302
 * Author: Wilson Tran, Dipen Patel, Rachael Caropreso
 * Created on: April 19, 2024
 * Last Modified: April 19, 2024
 * Description: Write object instances to binary file
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab11Prob03 {

    public static void main(String[] args) {
        // Create a list to store people
        List<Person> persons = new ArrayList<>();

        try (
                // Create input stream for file
                DataInputStream input = new DataInputStream(new FileInputStream("src/people.dat"));) {
            // Read data from the input file and add to list
            while (true) {
                int age = input.readInt();
                String name = input.readUTF();
                String address = input.readUTF();
                int zipCode = input.readInt();
                double salary = input.readDouble();

                // Create person instance
                persons.add(new Person(age, name, address, zipCode, salary));

            }
        } catch (EOFException ex) { // Catch end-of-file exception 

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Sort the ArrayList
        Collections.sort(persons);

        try (PrintWriter writer = new PrintWriter("people-salary-sorted.dat")) {
            for (Person person : persons) {
                writer.println(person.toString());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        // Write Person information to a binary file
        try ( // Create an output stream for people-salary-sorted-objects.dat
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("people-salary-sorted-objects.dat"));) {
            // Write arrays to the object output stream
            for (Person person : persons) {
                output.writeObject(person);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Person implements Serializable, Comparable<Object> {

    // Data Members
    int age;
    String name;
    String address;
    int zipCode;
    double salary;

    // Default Constructor
    public Person() {
    }

    // Convenience Constructor
    public Person(int age, String name, String address, int zipCode, double salary) {
        setAge(age);
        setName(name);
        setAddress(address);
        setZipCode(zipCode);
        setSalary(salary);
    }

    // Accessors
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public double getSalary() {
        return salary;
    }

    // Mutators
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // toString
    public String toString() {
        return String.format("%d %s %s %d $%,.2f", age, name, address, zipCode, salary);
    }

    // comparable interface
    public int compareTo(Object p) {
        if (this.getSalary() > ((Person)p).getSalary()) {
            return 1;
        } else if (getSalary() < ((Person)p).getSalary()) {
            return -1;
        } else {
            return 0;
        }
    }
}
