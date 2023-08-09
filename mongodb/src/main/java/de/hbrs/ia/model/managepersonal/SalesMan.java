package de.hbrs.ia.model.managepersonal;

public class SalesMan {
    private String firstname;
    private String lastname;
    private Integer sid;
    private String department;
    private int year;

    public SalesMan(String firstname, String lastname, Integer sid, String department, int year) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sid = sid;
        this.department = department;
        this.year = year;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "SalesMan{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", sid=" + sid +
                ", department='" + department + '\'' +
                ", year=" + year +
                '}';
    }


}
