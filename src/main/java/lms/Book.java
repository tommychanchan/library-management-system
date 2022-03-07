package lms;

import java.util.*;

public class Book {
    private String isbn;
    private String title;
    private String publisher;
    private int edition;
    private double cost;
    private int quantity;
    private ArrayList<String> authors;
    
    public Book() {}
    
    public Book(String isbn, String title, String publisher, int edition, double cost, int quantity, ArrayList<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.edition = edition;
        this.cost = cost;
        this.quantity = quantity;
        this.authors = authors;
    }
    
    public String getISBN() {
        return isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public int getEdition() {
        return edition;
    }
    
    public double getCost() {
        return cost;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public ArrayList<String> getAuthors() {
        return authors;
    }
    
    public String joinAuthors(String separator) {
        String temp = "";
        for (int i = 0, n = authors.size(); i < n; i++) {
            temp += (i == 0 ? "" : separator) + authors.get(i);
        }
        return temp;
    }
    
    public void addQuantity(int n) {
        quantity += n;
    }
}
