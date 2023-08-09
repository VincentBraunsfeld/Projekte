package de.hbrs.ia.model.managepersonal;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public interface ManagePersonal {

    //CRUD Salesman
    public void createSalesMan(SalesMan salesMan);

    public SalesMan readSalesMan(int sid);

    public void updateSalesMan(int sid, String key,Object attribute) throws IllegalAccessException;

    public void deleteSalesMan(int sid);

    public void cleanSalesMan();

    public List<SalesMan> querySalesMan(String key,Object attribute);


    //CRUD Evaluation Record
    public void createEvaluationRecord(EvaluationRecord evaluationRecord, int sid);

    public EvaluationRecord readEvaluationRecord(int sid);

    public void updateEvaluationRecord(int sid, String key, Object attribute);

    public void deleteEvaluationRecord(int sid);

    public List<EvaluationRecord> queryEvaluationRecord(String key, Object attribute);

    public void cleanEvaluationRecords();

    public MongoCollection<Document> getSalesmanColl();

    public MongoCollection<Document> getEvaluationRecordsColl();


}
