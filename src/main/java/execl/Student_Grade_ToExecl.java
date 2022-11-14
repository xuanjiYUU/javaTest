package execl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;
import control.Control;
import control.CouControl;
import control.GraControl;
import entity.Cource;
import entity.Grade;
import entity.Student;
import tools.DOUBLE;
import tools.mError;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 学生成绩execl
 *
 * @author 马文涛
 * @date 2022/11/09
 */
public class Student_Grade_ToExecl {


    public Student_Grade_ToExecl() {

    }

    /**
     * 创造成绩
     *
     * @param ss 党卫军
     * @throws SQLException          sqlexception异常
     * @throws mError                米误差
     * @throws PropertyVetoException 财产否决例外
     */
    public void createGrades(List<Student> ss, int flag) throws SQLException, mError, PropertyVetoException {
        Control cc = CouControl.getInstance();
        String filename = "Grades" + System.currentTimeMillis() + ".xlsx";
        List<Student_Grade> student_grades_desc = getAllGrades(ss, flag);
        List<Student_SumScore> sumScores_desc = getSumCoreGrades(ss, flag).stream().toList();
        List<Student_SumScore> class_course_avg_score_desc = getClass_Course_Avg_Score(ss, cc.read(null), flag);

        try (ExcelWriter excelWriter = EasyExcel.write(filename).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet(1, "成绩").head(Student_Grade.class).build();
            excelWriter.write(student_grades_desc, writeSheet);
            writeSheet = EasyExcel.writerSheet(2, "平均分").head(Student_SumScore.class).build();
            excelWriter.write(sumScores_desc, writeSheet);
            writeSheet = EasyExcel.writerSheet(3, "班级课程平均分").head(Student_Class_Course_AvgScore.class).build();
            excelWriter.write(class_course_avg_score_desc, writeSheet);
        }
    }


    /**
     * 得到所有成绩
     *
     * @param students 学生
     * @param flag     国旗
     * @return {@link List}<{@link Student_Grade}>
     */
    public List<Student_Grade> getAllGrades(List<Student> students, int flag) {
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
        if (flag == 0) {
            result.sort((o1, o2) -> {
                return o1.getScore() - o2.getScore();
            });
        } else if (flag == 1) {
            result.sort((o1, o2) -> {
                return o2.getScore() - o1.getScore();
            });
        }
        return result;
    }

    /**
     * 获得核心和成绩
     *
     * @param students 学生
     * @param flag     国旗
     * @return {@link Set}<{@link Student_SumScore}>
     * @throws PropertyVetoException 财产否决例外
     * @throws SQLException          sqlexception异常
     */
    public Set<Student_SumScore> getSumCoreGrades(List<Student> students, int flag) throws PropertyVetoException, SQLException {
        Set<Student_SumScore> result = new HashSet<>();
        for (Student s : students) {
            result.add(new Student_SumScore(s.getSno(), s.getCno(), s.getName(), s.getSum_score(), DOUBLE.round(s.getSum_score() / s.getGrades().size(), 3)));
        }
        for (Student_SumScore s : result) {
            System.out.println(s);
        }
        if (flag == 0) {
            result.stream().sorted((o1, o2) -> {
                return (int) (o1.getSumScore() - o2.getSumScore());
            });
        } else if (flag == 1) {
            result.stream().sorted((o1, o2) -> {
                return (int) (o2.getSumScore() - o1.getSumScore());
            });
        }
        return result;
    }

    /**
     * 得到类课程avg得分
     *
     * @param ss 党卫军
     * @param cs cs
     * @return {@link List}<{@link Student_Class_Course_AvgScore}>
     * @throws PropertyVetoException 财产否决例外
     * @throws SQLException          sqlexception异常
     */
    public List<Student_Class_Course_AvgScore> getClass_Course_Avg_Score(List<Student> ss, List<Cource> cs, int flag) throws PropertyVetoException, SQLException {
        Map<String, Student_Class_Course_AvgScore> result = new HashMap<>();
        GraControl gc = GraControl.getInstance(); //使用暴露的接口查询
        for (Student s : ss) {
            for (Cource c : cs) {
                String head = s.getName() + s.getCno() + c.getCname();
                if (s.getScore(c.getCname()) == 0) { //去除0成绩的课程
                    continue;
                }else{
                    result.put(head, new Student_Class_Course_AvgScore(s.getSno(), s.getCno(), s.getName(), c.getCname(), s.getScore(c.getCname()), gc.readClass_Course_Avg_Score(s.getCno(), c.getCname())));
                }
            }
        }
        List<Student_Class_Course_AvgScore> result_temp = result.values().stream().toList();
        List<Student_Class_Course_AvgScore> result_sort = new ArrayList<>(result_temp);
        if (flag == 0) {
            result_sort.sort((o1, o2) -> {
                return (int) (o1.getClass_course_avg_scroe() - o2.getClass_course_avg_scroe());
            });
        } else if (flag == 1) {
            result_sort.sort((o1, o2) -> {
                return (int) (o2.getClass_course_avg_scroe() - o1.getClass_course_avg_scroe());
            });
        }
        return result_sort;
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

class Student_SumScore {
    @ExcelProperty(value = "学号", index = 1)
    private long sno;
    @ExcelProperty(value = "班级", index = 2)
    private long cno;
    @ExcelProperty(value = "姓名", index = 3)
    private String name;
    @ExcelProperty(value = "该生总分", index = 4)
    private double sumScore;
    @ExcelProperty(value = "该生平均分", index = 5)
    private double avg_Score;

    public Student_SumScore(long sno, long cno, String name, double sumScore, double avg_Score) {
        this.cno = cno;
        this.sno = sno;
        this.sumScore = sumScore;
        this.avg_Score = avg_Score;
        this.name = name;

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

    public double getSumScore() {
        return sumScore;
    }

    public void setSumScore(double sumScore) {
        this.sumScore = sumScore;
    }

    public double getAvg_Score() {
        return avg_Score;
    }

    public void setAvg_Score(double avg_Score) {
        this.avg_Score = avg_Score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student_SumScore{" +
                "sno=" + sno +
                ", cno=" + cno +
                ", name='" + name + '\'' +
                ", sumScore=" + sumScore +
                ", avg_Score=" + avg_Score +
                '}';
    }
}

class Student_Class_Course_AvgScore {
    @ExcelProperty(value = "学号", index = 1)
    private long sno;
    @ExcelProperty(value = "班级", index = 2)
    private long cno;
    @ExcelProperty(value = "姓名", index = 3)
    private String name;
    @ExcelProperty(value = "课程", index = 4)
    private String cname;
    @ExcelProperty(value = "得分", index = 5)
    private double score;

    public long getSno() {
        return sno;
    }

    @ExcelProperty(value = "课程班级平均分", index = 6)
    private double class_course_avg_scroe;

    public Student_Class_Course_AvgScore(long sno, long cno, String name, String cname, double score, double class_course_avg_scroe) {
        this.sno = sno;
        this.cno = cno;
        this.name = name;
        this.cname = cname;
        this.score = score;
        this.class_course_avg_scroe = class_course_avg_scroe;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getClass_course_avg_scroe() {
        return class_course_avg_scroe;
    }

    public void setClass_course_avg_scroe(double class_course_avg_scroe) {
        this.class_course_avg_scroe = class_course_avg_scroe;
    }
}

