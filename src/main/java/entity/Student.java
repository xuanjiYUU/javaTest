package entity;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String brithday;
    private String sex;
    private long sno;
    private long cno;
    private List<Grade> grades;
    /**
     * 该属性对数据库不可见
     */
    private double sum_score;

    //用于删除时指定
    public Student(long sno) {
        this.sno = sno;
    }

    //用于增加信息(不用指定sno)
    public Student(long cno, String name, String sex, String brithday) {
        this.cno = cno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithday;
    }

    //用于只读基本信息
    public Student(long sno, long cno, String name, String sex, String brithray) {
        this.sno = sno;
        this.cno = cno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithray;
    }

    //用于读全部信息（包含成绩）
    public Student(long sno, long cno, String name, String sex, String brithray, List<Grade> grades) {
        this.sno = sno;
        this.cno = cno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithray;
        this.grades = grades;
        caculater_sumScore();
    }

    /**
     * 计算总分数
     */
    private void caculater_sumScore() {
        double sum = 0.0;
        for (Grade g : grades) {
            sum += g.getScore();
        }
        setSum_score(sum);
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
                ", brithday='" + brithday + '\'' +
                ", sex='" + sex + '\'' +
                ", sno=" + sno +
                ", cno=" + cno +
                ", grades=" + grades +
                ", sum_score=" + sum_score +
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
        caculater_sumScore();
    }

    public long getCno() {
        return cno;
    }

    public void setCno(long cno) {
        this.cno = cno;
    }

    public double getSum_score() {
        return sum_score;
    }

    /**
     * @param sum_score
     */
    public void setSum_score(double sum_score) {
        this.sum_score = sum_score;
    }

    public int getScore(String cname) {
        int result = 0;
        for (Grade g : grades) {
            if (g.getCname().equals(cname)) {
                result = g.getScore();
            }
        }
        return result;
    }
}
