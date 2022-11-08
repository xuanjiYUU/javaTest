package tools;

import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class STATIC_DATA {
    private static List<Student> students;
    private static Thread thread;//维护全局资源的线程

    public static List<Student> getInstance() {
        return students == null ? new ArrayList<>() : students;
    }
}
