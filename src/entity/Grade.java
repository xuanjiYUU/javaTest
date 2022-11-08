package entity;

public class Grade {
    private String cname;
    private long sno;
    private int score;

    public Grade(Long sno, String cname, int score) {
        this.cname = cname;
        this.sno = sno;
        this.score = score;
    }
    public Grade(Long sno, String cname) {
        this.cname = cname;
        this.sno = sno;
    }
    public Grade(int score) {
        this.score = score;
    }

    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
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
        return "Grade{" +
                "sno='" + sno + '\'' +
                ", cname='" + cname + '\'' +
                ", score=" + score +
                '}';
    }
}
