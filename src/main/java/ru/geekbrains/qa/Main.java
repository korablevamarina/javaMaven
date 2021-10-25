package ru.geekbrains.qa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new StudentImpl("IVAN", Arrays.asList(CourseEnum.ECONOMIC, CourseEnum.MATH)));
        students.add(new StudentImpl("KIRILL", Arrays.asList(CourseEnum.ENGLISH, CourseEnum.MATH, CourseEnum.PHYS)));
        students.add(new StudentImpl("MARINA", Arrays.asList(CourseEnum.FRENCH, CourseEnum.PHYS, CourseEnum.MANAGEMENT)));
        students.add(new StudentImpl("ANTON", Arrays.asList(CourseEnum.MANAGEMENT, CourseEnum.ENGLISH, CourseEnum.ECONOMIC)));
        students.add(new StudentImpl("DENIS", Arrays.asList(CourseEnum.ECONOMIC, CourseEnum.PHYS)));
        System.out.println(getCourses(students));
        System.out.println(findStudents(students));
        System.out.println(findStudentsByCourse(students,CourseEnum.PHYS));
    }
    public static List<Course> getCourses(List<Student> students) {
        return students.stream()
                .flatMap((Student student) -> student.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());
    }
    public static List<Student> findStudents(List<Student> students){
        return students.stream()
                .sorted((Student s1, Student s2) -> s2.getAllCourses().size() - s1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }
    public static List<Student> findStudentsByCourse(List<Student> students, Course course){
        return students.stream()
                .filter(s -> s.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }
}
