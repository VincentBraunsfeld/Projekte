package de.hbrs.ia.model.managepersonal;

import java.util.Scanner;

public class ManagePersonalUI {


    private ManagePersonal managePersonal;

    public ManagePersonalUI() {
        managePersonal = new ManagePersonalController();
    }

    public void dialog() throws IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        // salesman
        String firstname;
        String lastname;
        int sid;                 // for both
        String department;
        int year;

        //eval record
        int targetValue;
        int actualValue;
        String comment;


        System.out.println("Options type:\n" +
                "[0] if you want to create a new salesman\n" +
                "[1] to read a salesman\n" +
                "[2] if you want to update a salesman\n" +
                "[3] if you want to delete a salesman\n" +
                "[4] if you want to create a new evaluation record\n" +
                "[5] to read a evaluation record\n" +
                "[6] if you want to update a evaluation record\n" +
                "[7] if you want to delete a evaluation record\n" +
                "[8] if you want to see the options\n" +
                "[9] if you want to close the dialog");

        int number = sc.nextInt();
        while (number != 9) {
            switch (number) {
                case 0:
                    System.out.println("to create a sales man you need to type: firstname,lastname,sid,department,year ");
                    String s = sc.next();
                    String[] elements = s.split(",");
                    firstname = elements[0];
                    lastname = elements[1];
                    sid = Integer.parseInt(elements[2]);
                    department = elements[3];
                    year = Integer.parseInt(elements[4]);
                    managePersonal.createSalesMan(new SalesMan(firstname, lastname, sid, department, year));
                    System.out.println("salesman got created");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 1:
                    System.out.println("pls enter the salesman sid to read");
                    sid = sc.nextInt();
                    System.out.println(managePersonal.readSalesMan(sid).toString());
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 2:
                    System.out.println("to update a salesman pls enter: key,property");
                    String tuple = sc.next();
                    String[] tup = tuple.split("'");
                    sid = Integer.parseInt(tup[0]);
                    String key = tup[1];
                    String value = tup[2];
                    managePersonal.updateSalesMan(sid, key, value);
                    System.out.println("salesman got updated");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 3:
                    System.out.println("pls enter the salesman id to delete");
                    sid = sc.nextInt();
                    managePersonal.deleteSalesMan(sid);
                    System.out.println("salesman got deleted");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 4:
                    System.out.println("to create a evaluation record you need to type: sid,targetValue,actualValue,comment");
                    String string = sc.next();
                    String[] ele = string.split(",");
                    sid = Integer.parseInt(ele[0]);
                    targetValue = Integer.parseInt(ele[1]);
                    actualValue = Integer.parseInt(ele[2]);
                    comment = ele[3];
                    managePersonal.createEvaluationRecord(new EvaluationRecord(sid, targetValue, actualValue, comment), sid);
                    System.out.println("evaluation record got created");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 5:
                    System.out.println("pls enter the salesman sid to read record for that id");
                    sid = sc.nextInt();
                    System.out.println(managePersonal.readEvaluationRecord(sid).toString());
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 6:
                    System.out.println("to update a evaluation record pls enter: key,property");
                    tuple = sc.next();
                    tup = tuple.split("'");
                    sid = Integer.parseInt(tup[0]);
                    key = tup[1];
                    value = tup[2];
                    managePersonal.updateEvaluationRecord(sid, key, value);
                    System.out.println("evaluation record got updated");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 7:
                    System.out.println("pls enter the salesman sid to delete the right evaluation record");
                    sid = sc.nextInt();
                    managePersonal.deleteEvaluationRecord(sid);
                    System.out.println("evaluation record got deleted");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                case 8:

                    System.out.println("Options type:\n" +
                            "[0] if you want to create a new salesman\n" +
                            "[1] to read a salesman\n" +
                            "[2] if you want to update a salesman\n" +
                            "[3] if you want to delete a salesman\n" +
                            "[4] if you want to create a new evaluation record\n" +
                            "[5] to read a evaluation record\n" +
                            "[6] if you want to update a evaluation record\n" +
                            "[7] if you want to delete a evaluation record\n" +
                            "[8] if you want to see the options\n" +
                            "[9] if you want to close the dialog");
                    System.out.println("pls type you re next number");
                    number = sc.nextInt();
                    break;
                default:
                    System.out.println("wrong number pls try it again:");
                    number = sc.nextInt();
                    break;
            }
        }
        System.out.println("dialog is closed");
        sc.close();
    }
}




