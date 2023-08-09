package test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.managepersonal.EvaluationRecord;
import de.hbrs.ia.model.managepersonal.ManagePersonal;
import de.hbrs.ia.model.managepersonal.ManagePersonalController;
import de.hbrs.ia.model.managepersonal.SalesMan;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HighPerformanceTest {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> salesmanColl;

    private MongoCollection<Document> evaluationRecordsColl;

    private ManagePersonal managePersonalController;
    private SalesMan salesMan1;
    private SalesMan salesMan2;
    private SalesMan salesMan3;

    private EvaluationRecord evaluationRecord1;
    private EvaluationRecord evaluationRecord2;
    private EvaluationRecord evaluationRecord3;


    @BeforeEach
    void setUp() {
        managePersonalController = new ManagePersonalController();
        salesmanColl = managePersonalController.getSalesmanColl();
        evaluationRecordsColl = managePersonalController.getEvaluationRecordsColl();


        salesMan1 = new SalesMan("Tom", "Becker", 1, "salesman", 2022);
        salesMan2 = new SalesMan("Lukas", "Fischer", 2, "salesman", 2021);
        salesMan3 = new SalesMan("Lea", "Kaiser", 3, "ceo", 2020);

        evaluationRecord1 = new EvaluationRecord(1, 20,10, "good job");
        evaluationRecord2 = new EvaluationRecord(2, 20,5, "bad job");
        evaluationRecord3 = new EvaluationRecord(3, 20,10, "well done");

    }

    @AfterEach
    void reset() {
        managePersonalController.cleanSalesMan();
        managePersonalController.cleanEvaluationRecords();
        managePersonalController = null;
        salesMan1 = null;
        salesMan2 = null;
        salesMan3 = null;
        evaluationRecord1 = null;
        evaluationRecord2 = null;
        evaluationRecord3 = null;
    }


    @Test
    void createSalesMan() {
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createSalesMan(salesMan2);
        managePersonalController.createSalesMan(salesMan3);

        assertEquals(3, salesmanColl.countDocuments());


    }

    @Test
    void readSalesMan() {
        managePersonalController.createSalesMan(salesMan1);
        assertEquals("SalesMan{firstname='Tom', lastname='Becker', sid=1, department='salesman', year=2022}", managePersonalController.readSalesMan(1).toString());
    }

    @Test
    void updateSalesMan() throws IllegalAccessException {
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.updateSalesMan(1, "firstname", "Michael");
        managePersonalController.updateSalesMan(1, "year", 2021);
        assertEquals("SalesMan{firstname='Michael', lastname='Becker', sid=1, department='salesman', year=2021}", managePersonalController.readSalesMan(1).toString());

    }

    @Test
    void deleteSalesMan() {
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.deleteSalesMan(1);
        assertEquals(0, salesmanColl.countDocuments());
    }

    @Test
    void querySalesMan() {
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createSalesMan(salesMan2);
        managePersonalController.createSalesMan(salesMan3);
        List<String> expected = new ArrayList<>();
        List<SalesMan> actual = managePersonalController.querySalesMan("department", "salesman");
        expected.add(salesMan1.toString());
        expected.add(salesMan2.toString());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i),actual.get(i).toString());
        }
    }

    @Test
    void cleanSalesMan(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createSalesMan(salesMan2);
        managePersonalController.createSalesMan(salesMan3);
        managePersonalController.cleanSalesMan();
        assertEquals(0, salesmanColl.countDocuments());
    }


    // CRUD evaluation record
    @Test
    void createEvaluationRecord(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createEvaluationRecord(evaluationRecord1, 1);
        managePersonalController.createEvaluationRecord(evaluationRecord2, 1);
        managePersonalController.createEvaluationRecord(evaluationRecord3, 1);

        assertEquals(3, evaluationRecordsColl.countDocuments());
    }

    @Test
    void readEvaluationRecord(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createEvaluationRecord(evaluationRecord1, 1);

        assertEquals(evaluationRecord1.toString(), managePersonalController.readEvaluationRecord(1).toString());
    }

    @Test
    void updateEvaluationRecord(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createEvaluationRecord(evaluationRecord1, 1);
        managePersonalController.updateEvaluationRecord(1, "comment", "new comment");

        assertEquals("new comment", managePersonalController.readEvaluationRecord(1).getComment());
    }

    @Test
    void deleteEvaluationRecord(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createEvaluationRecord(evaluationRecord1, 1);
        managePersonalController.deleteEvaluationRecord(1);
        assertEquals(0, evaluationRecordsColl.countDocuments());
    }

    @Test
    void queryEvaluationRecord(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createSalesMan(salesMan2);
        managePersonalController.createSalesMan(salesMan3);
        managePersonalController.createEvaluationRecord(evaluationRecord1, 1);
        managePersonalController.createEvaluationRecord(evaluationRecord2, 2);
        managePersonalController.createEvaluationRecord(evaluationRecord3, 3);
        List<String> expected = new ArrayList<>();
        expected.add(evaluationRecord1.toString());
        expected.add(evaluationRecord3.toString());
        List<EvaluationRecord> actual = managePersonalController.queryEvaluationRecord("actualVale", 10);
        for(int i =0; i<actual.size(); i++){
            assertEquals(expected.get(i),actual.get(i).toString());
        }
    }
    @Test
    void cleanEvaluationRecords(){
        managePersonalController.createSalesMan(salesMan1);
        managePersonalController.createSalesMan(salesMan2);
        managePersonalController.createSalesMan(salesMan3);
        managePersonalController.createEvaluationRecord(evaluationRecord1, 1);
        managePersonalController.createEvaluationRecord(evaluationRecord2, 2);
        managePersonalController.createEvaluationRecord(evaluationRecord3, 3);
        managePersonalController.cleanEvaluationRecords();
        assertEquals(0, evaluationRecordsColl.countDocuments());
    }




}