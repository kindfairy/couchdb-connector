package ru.spbu.apmath.st033672;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    private static class StringIntegerDouble {
        private String string;
        private Integer intValue;
        private Double doubleValue;

        public StringIntegerDouble(String string, Integer intValue, Double doubleValue) {
            this.string = string;
            this.intValue = intValue;
            this.doubleValue = doubleValue;
        }

    }

    private static class IntegerString {
        private Integer intValue;
        private String string;

        public IntegerString(Integer inValue, String string) {
            this.intValue = inValue;
            this.string = string;
        }
    }

    private static String ip = "217.197.2.6";
    private static String port = "5984";
    private static String dbName = "testdb";
    private static String userName = "admin";
    private static String userPassword = "admin";

    private static void test1(){

        //1. Write Object

        CouchdbConnector connector = new CouchdbConnector(ip, port, dbName, userName, userPassword);

        IntegerString object = new IntegerString(12, "object");
        try {
            connector.write(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2(){

        //2. Write JSON

        CouchdbConnector connector = new CouchdbConnector(ip, port, dbName, userName, userPassword);


        Gson gson = new Gson();
        IntegerString object = new IntegerString(151, "string-json");
        String json = gson.toJson(object);
        try {
            connector.writeJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void test3(){

        //3. Write list of objects

        CouchdbConnector connector = new CouchdbConnector(ip, port, dbName, userName, userPassword);

        int N = 7;
        List<IntegerString> objectList = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            objectList.add(new IntegerString(i, "objects-"  + i));
        }
        try {
            connector.writeBulk(objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void test4(){

        //4. Write JSONS

        CouchdbConnector connector = new CouchdbConnector(ip, port, dbName, userName, userPassword);

        List<String> stringList = new ArrayList<>();
        int N = 8;
        Gson gson = new Gson();
        for (int i=0; i<N; ++i){
            stringList.add(gson.toJson(
                    new IntegerString(i, "jsons-" + i)));
        }
        try {
            connector.writeBulkJsons(stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test5(){

        //5. Reading all docs

        CouchdbConnector connector = new CouchdbConnector(ip, port, dbName, userName, userPassword);

        try {
            List<IntegerString> list = connector.readAll(IntegerString.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {


        test1();

        test2();

        test3();

        test4();

        test5();


    /*
	String json = "{\"intValue\":1234, \"string\":\"string\", \"anstr\":\"string\"}";

	Gson gson = new Gson();


	IntegerString obj = gson.fromJson(json, IntegerString.class);
	*/


        //List<IntegerString> list = connector.readAll(IntegerString.class);
        /*
        for (int i = 0; i < 7; ++i) {
            list.add(new IntegerString(i, "string-" + i));
        }
        */


        //connector.writeBulk(list);


        /*



        */

    }

}
