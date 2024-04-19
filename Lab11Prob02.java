/**
 * File: Lab011Prob02.java
 * Class: CSCI 1302
 * Author: Wilson Tran, Dipen Patel, Rachael Caropreso
 * Created on: April 19, 2024
 * Last Modified: April 19, 2024
 * Description: Add person class
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab11Prob02 {
    public static void main(String[] args) {
        // Create a list to store people
        List<Person> persons = new ArrayList<>();

        try (
            // Create input stream for file
            DataInputStream input = new DataInputStream(new FileInputStream("src/people.dat"));
        ) {
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

        // Write Person information to a file
        try (PrintWriter writer = new PrintWriter("people-salary-sorted.dat")) {
            for (Person person : persons) {
                writer.println(person.toString());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static class Person implements Comparable<Person> {
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
        @Override
        public int compareTo(Person p) {
            if (getSalary() > p.getSalary())
                return 1;
            else if (getSalary() < p.getSalary())
                return -1;
            else
                return 0;
        }
    }
}
