package ru.geekbrains.qa;

import java.util.List;

public class StudentImpl implements Student{
    String name;
    List<Course> courses;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
    public StudentImpl(String name, List<Course> courses){
        this.name = name;
        this.courses = courses;
    }
    @Override
    public String toString(){
        return name;
    }
}

