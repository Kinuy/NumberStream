package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        /* TASKS

        * Step 1: Filter a list of numbers and only output the even numbers.

        Step 2: Use 'map' and double each number in the list.

        Step 3: Sort the list in ascending order.

        Step 4: Perform a 'reduce' operation to calculate the sum of all numbers in the list.

        Step 5: Use 'forEach' and output each processed number.

        Step 6: Collect the processed numbers into a new list using 'collect'.

        //Bonus
        Take a look at the file students.csv. Copy the file to the root directory of your project.

        Use streams to read and output the file line by line (Hint: Files.lines(Path.of("students.csv")))

        Remove the header

        Convert each line into the class Student

        Remove invalid lines and duplicates
        * */

        List<Integer> numberList = new ArrayList<>(List.of(1,2,6,9,3,5,7));
        List<Integer> newList = numberList.stream()
                .filter(number -> number % 2 == 0) // Task 1
                .peek(number -> System.out.println(number))
                .map(number -> number * 2) // Task 2
                .sorted() // Task 3
                .collect(Collectors.toUnmodifiableList()); //Task 6

        // Task 4
        int sum = numberList.stream().reduce(0, Integer::sum);

        System.out.println(sum);

        // Task 5
        numberList.stream().forEach((number)->{
            System.out.println(number);
        });

        // Bonus
        String COMMA_DELIMITER = ",";

        try (Stream<String> lines = Files.lines(Path.of("students.csv")) ){

            // read and clean data
            List<List<String>> records = lines.map(line -> Arrays.asList(line.split(COMMA_DELIMITER)))
                    .skip(1) // skip header
                    .filter(line -> !line.isEmpty()) // skip empy lines
                    .filter(line -> line.size() > 1) // skip empty array to
                    .distinct() // drop dublicate lines
                    .peek(line -> System.out.println(line)) // check by print
                    .collect(Collectors.toList());

            // pipe data in List of student objects
            List<Student> studentList = new ArrayList<>();
            for (List<String> record : records) {
                Student newStudent = new Student(record.get(0),record.get(1),record.get(2),record.get(3));
                studentList.add(newStudent);

                System.out.println(record);
            }
        }


        catch (IOException e) {
            e.printStackTrace();
        }

    }
}