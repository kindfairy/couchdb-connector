package ru.spbu.apmath.st033672;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        public IntegerString(){}

        public IntegerString(Integer inValue, String string) {
            this.intValue = inValue;
            this.string = string;
        }

        @Override
        public String toString(){
            return "intValue: " + intValue + "\n" + "string: " + string + "\n";
        }
    }



    private static String ip = "217.197.2.6";
    private static String port = "5984";
    private static String dbName = "testdb";
    private static String userName = "admin";
    private static String userPassword = "admin";


    public static void main(String[] args) throws IOException {

//        testExtraFieldsInJson();
//
//        test1();
//
//        test2();
//
//        test3();
//
//        test4();

        test6();






    }

    private static void testExtraFieldsInJson() {
        String json = "{\"intValue\":1234, \"anothstr\":\"another string\", \"string\":\"some string\"}";

        Gson gson = new Gson();

        IntegerString obj = gson.fromJson(json, IntegerString.class);

        System.out.println(obj.toString());
    }

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

    private static void test6(){

        //6. Reading all docs

        CouchdbConnector connector = new CouchdbConnector(ip, port, "articles", userName, userPassword);


        List<KeyValuePair<Article, Article>> list = new ArrayList<>();
        try {
            list = connector.<Article, Article>getView(Article.class, Article.class, "tfidf",
                    "wodd-view", false, 0, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for( KeyValuePair<Article, Article> keyValuePair: list ){
            System.out.println(keyValuePair.toString());
        }




        list = new ArrayList<>();
        try {
            list = connector.<Article, Article>getView(Article.class, Article.class, "tfidf",
                    "wodd-view", false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for( KeyValuePair<Article, Article> keyValuePair: list ){
            System.out.println(keyValuePair.toString());
        }

    }




}
















