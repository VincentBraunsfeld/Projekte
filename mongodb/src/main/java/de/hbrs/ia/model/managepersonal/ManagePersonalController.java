package de.hbrs.ia.model.managepersonal;


import java.util.List;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;

public class ManagePersonalController implements ManagePersonal {
     private MongoClient mongoClient;
     private MongoDatabase database;
     private MongoCollection<Document> salesmanColl;
     private MongoCollection<Document> evaluationRecordsColl;

    public ManagePersonalController() {
        this.mongoClient = new MongoClient("localhost", 27017);
        this.database = mongoClient.getDatabase("db");
        this.salesmanColl = database.getCollection("salesman");
        this.evaluationRecordsColl = database.getCollection("evaluation records");
    }

    public MongoCollection<Document> getSalesmanColl() {return salesmanColl;}

    public MongoCollection<Document> getEvaluationRecordsColl() {
        return evaluationRecordsColl;
    }

    //CRUD salesman
    @Override
    public void createSalesMan(SalesMan salesMan) {
        if (salesMan.getSid() == null) throw new IllegalArgumentException("pls set the sid");
        try {
            if (readSalesMan(salesMan.getSid()) == null) {
                salesmanColl.insertOne(new Document("firstname", salesMan.getFirstname())
                        .append("lastname", salesMan.getLastname())
                        .append("sid", salesMan.getSid())
                        .append("department", salesMan.getDepartment())
                        .append("year", salesMan.getYear()));
            }
            ;
        } catch (Exception e) {
            System.out.println("sid is already used");
        }
    }


    @Override
    public SalesMan readSalesMan(int sid) {
        SalesMan salesMan = null;
        try {
            Document doc = salesmanColl.find(eq("sid", sid)).first();
            salesMan = new SalesMan(
                    (String) doc.get("firstname"),
                    (String) doc.get("lastname"),
                    (Integer) doc.get("sid"),
                    (String) doc.get("department"),
                    (Integer) doc.get("year"));

        } catch (NullPointerException e) {
            System.out.println("salesman with the sid: " + sid + " not found");
        }
        return salesMan;
    }

    @Override
    public void updateSalesMan(int sid, String key, Object attribute) {
        update(sid, key, attribute, salesmanColl);
        System.out.println("the salesman with the sid: " + sid + " got upgraded");
    }

    @Override
    public void deleteSalesMan(int sid) {
        if (readSalesMan(sid) == null) throw new IllegalArgumentException("id " + sid + " not found");
        salesmanColl.deleteOne(eq("sid", sid));
        /*if (readEvaluationRecord(sid) != null) {
            deleteEvaluationRecord(sid);
            System.out.println("evaluation with the sid = " + sid + " and salesman got deleted");
        }*/
        System.out.println("salesman with the sid = "+ sid+ " got deleted");
    }

    @Override
    public List<SalesMan> querySalesMan(String key, Object attribute) {
        List<SalesMan> list = new ArrayList<>();
        MongoCursor<Document> cursor = salesmanColl.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (doc.get(key) == attribute) {
                    list.add(new SalesMan(
                            (String) doc.get("firstname"),
                            (String) doc.get("lastname"),
                            (Integer) doc.get("sid"),
                            (String) doc.get("department"),
                            (Integer) doc.get("year")));

                }
            }
        } finally {
            cursor.close();
            return list;
        }
    }

    @Override
    public void cleanSalesMan() {
        MongoCursor<Document> cursor = salesmanColl.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                deleteSalesMan((Integer) doc.get("sid"));
            }
        } finally {
            cursor.close();
        }
    }

    //CRUD for Evaluation Record
    @Override
    public void createEvaluationRecord(EvaluationRecord record, int sid) {
        if (readSalesMan(sid) == null) throw new IllegalArgumentException("no salesman found");

        evaluationRecordsColl.insertOne(new Document("sid", sid)
                .append("targetValue", record.getTargetValue())
                .append("actualValue", record.getActualValue())
                .append("comment", record.getComment()));

    }

    @Override
    public EvaluationRecord readEvaluationRecord(int sid) {
        EvaluationRecord evaluationRecord = null;
        try {
            Document doc = evaluationRecordsColl.find(eq("sid", sid)).first();
            evaluationRecord = new EvaluationRecord(
                    (Integer) doc.get("sid"),
                    (Integer) doc.get("targetValue"),
                    (Integer) doc.get("actualValue"),
                    (String) doc.get("comment"));

        } catch (NullPointerException e) {
            System.out.println("evaluation with the sid: " + sid + " not found");
        }
        return evaluationRecord;
    }

    @Override
    public void updateEvaluationRecord(int sid, String key, Object attribute) {
        update(sid, key, attribute, evaluationRecordsColl);
        System.out.println("the evaluation with the sid: " + sid + " got upgraded");
    }


    @Override
    public void deleteEvaluationRecord(int sid) {
        if (readEvaluationRecord(sid) == null) throw new IllegalArgumentException("sid " + sid + " not found");
        evaluationRecordsColl.deleteOne(eq("sid", sid));
        System.out.println("evaluation with the sid = " + sid + " got deleted");
    }

    @Override
    public List<EvaluationRecord> queryEvaluationRecord(String key, Object attribute) {
        List<EvaluationRecord> list = new ArrayList<>();
        MongoCursor<Document> cursor = evaluationRecordsColl.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (doc.get(key) == attribute) {
                    list.add(new EvaluationRecord(
                            (Integer) doc.get("sid"),
                            (Integer) doc.get("targetValue"),
                            (Integer) doc.get("actualValue"),
                            (String) doc.get("comment")));

                }
            }
        } finally {
            cursor.close();
            return list;
        }
    }

    @Override
    public void cleanEvaluationRecords() {
        MongoCursor<Document> cursor = evaluationRecordsColl.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                deleteEvaluationRecord((Integer) doc.get("sid"));
            }
        } finally {
            cursor.close();
        }
    }

    private void update(int sid, String key, Object attribute, MongoCollection<Document> coll) {
        if (readSalesMan(sid) == null) throw new IllegalArgumentException("sid " + sid + " not found");
        if (attribute instanceof String)
            coll.updateOne(eq("sid", sid), new Document("$set", new Document(key, (String) attribute)));
        if (attribute instanceof Integer)
            coll.updateOne(eq("sid", sid), new Document("$set", new Document(key, (Integer) attribute)));
    }
}
