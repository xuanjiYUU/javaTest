package entity;

public class Course {
    private String cname;

    public String getCname() {
        return cname;
    }
    public Course(String cname){
        this.cname=cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
