package tools;

import entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * ��̬����
 * ��ͣ����
 * @author ������
 * @date 2022/11/08
 */
public class STATIC_DATA {
    private static List<Student> students;
    private static Thread thread;//ά��ȫ����Դ���߳�

    public static List<Student> getInstance() {
        return students == null ? new ArrayList<>() : students;
    }
}
