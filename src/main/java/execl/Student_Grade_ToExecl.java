package execl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import control.Control;
import entity.Grade;
import entity.Student;
import tools.mError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 学生成绩execl
 *
 * @author 马文涛
 * @date 2022/11/09
 */
public class Student_Grade_ToExecl {


    public Student_Grade_ToExecl() {

    }

    public void createGrades(String filename, String sheet, Control c) throws SQLException, mError {
        EasyExcel.write(filename + ".xlsx", Student_Grade.class).
                sheet(sheet)
                .doWrite(get(c.read(null),0));
    }

    public List<Student_Grade> get(List<Student> students,int flag) {
        List<Student_Grade> result = new ArrayList<>();
        for (Student s : students) {
            for (Grade g : s.getGrades()) {
                Student_Grade sg = new Student_Grade(s.getSno(),
                        s.getCno(), s.getName(),
                        s.getSex(), s.getBrithday()
                        , g.getCname(), g.getScore());
                result.add(sg);
            }
        }
        if(flag==0){
            result.sort((o1, o2) -> {
                return o1.getScore() - o2.getScore();
            });
        }else if(flag==1){
            result.sort((o1, o2) -> {
                return o2.getScore() - o1.getScore();
            });
        }
        return result;
    }
}

class Student_Grade {
    @ExcelProperty(value = "学号", index = 1)
    private long sno;
    @ExcelProperty(value = "班级", index = 2)
    private long cno;
    @ExcelProperty(value = "姓名", index = 3)
    private String name;
    @ExcelProperty(value = "性别", index = 4)
    private String sex;
    @ExcelProperty(value = "生日", index = 5)
    private String brithday;
    @ExcelProperty(value = "课程", index = 6)
    private String cname;
    @ExcelProperty(value = "得分", index = 7)
    private int score;

    public Student_Grade(long sno, long cno, String name, String sex, String brithday, String cname, int score) {
        this.cno = cno;
        this.sno = sno;
        this.name = name;
        this.sex = sex;
        this.brithday = brithday;
        this.cname = cname;
        this.score = score;
    }

    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
    }

    public long getCno() {
        return cno;
    }

    public void setCno(long cno) {
        this.cno = cno;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student_Grade{" +
                "sno=" + sno +
                ", cno=" + cno +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", brithday='" + brithday + '\'' +
                ", cname='" + cname + '\'' +
                ", score=" + score +
                '}';
    }
}
