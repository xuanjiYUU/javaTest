package entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name, sex, brithday;
    private long sno, cno;
    private List<Grade> grades;

    //����ɾ��ʱָ��
    public Student(long sno) {
        this.sno = sno;
    }
    //����������Ϣ(����ָ��sno)
    public Student(long cno, String name, String sex, String brithday) {
        this.cno = cno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithday;
    }

    //����ֻ��������Ϣ
    public Student(long sno, long cno, String name, String sex, String brithray) {
        this.sno = sno;
        this.cno = cno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithray;
    }

    //���ڶ�ȫ����Ϣ�������ɼ���
    public Student(long sno, long cno, String name, String sex, String brithray, List<Grade> grades) {
        this.sno = sno;
        this.cno = cno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithray;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", brithday='" + brithday + '\'' +
                ", sno=" + sno +
                ", grades=" + grades +
                '}';
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade g) {
        if (this.grades == null) {
            this.grades = new ArrayList<>();
        }
        this.grades.add(g);
    }

    public long getCno() {
        return cno;
    }

    public void setCno(long cno) {
        this.cno = cno;
    }
}
