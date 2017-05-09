package ru.spbu.apmath.st033672;

public class Article{

    private String _id;
    private String _rev;
    private String docName;
    private String text;

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "docName='" + docName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}