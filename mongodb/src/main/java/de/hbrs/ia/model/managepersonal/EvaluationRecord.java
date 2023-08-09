package de.hbrs.ia.model.managepersonal;

public class EvaluationRecord {

    //performance record id

    //salesman id
    private int sid;
    private int targetValue;
    private int actualValue;
    private String comment;

    public EvaluationRecord(int sid, int targetValue, int actualValue, String comment) {
        this.sid = sid;
        this.targetValue = targetValue;
        this.actualValue = actualValue;
        this.comment = comment;
    }

    public EvaluationRecord(int targetValue, int actualValue, String comment) {
        this.targetValue = targetValue;
        this.actualValue = actualValue;
        this.comment = comment;
    }



    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public int getActualValue() {
        return actualValue;
    }

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "EvaluationRecord{" +
                ", sid=" + sid +
                ", targetValue=" + targetValue +
                ", actualValue=" + actualValue +
                ", comment='" + comment + '\'' +
                '}';
    }

}
