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

    public static void main(String[] args) {

        System.out.printf("Hello World!\n");

        String ip = "217.197.2.6";
        String port = "5984";
        String dbName = "testdb";
        String userName = "admin";
        String userPassword = "admin";

        CouchdbConnector connector = new CouchdbConnector(ip, port, dbName, userName, userPassword);

        StringIntegerDouble object = new StringIntegerDouble("string1", 1, 1.0);

        try {
            connector.write(object);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        String json = gson.toJson(object);

        try {
            connector.writeJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int N = 5;
        List<StringIntegerDouble> objectList = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            objectList.add(new StringIntegerDouble("string" + (i + 2), i + 2, i + 2.0));
        }

        try {
            connector.writeBulk(objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> stringList = new ArrayList<>();

        for (int i=0; i<N; ++i){
            stringList.add(gson.toJson(
                    new StringIntegerDouble("string"+(100+i), 100+i, 100.0+i)));
        }

        try {
            connector.writeBulkJsons(stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
