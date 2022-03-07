package lms;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Utils {
    public static ArrayList<Book> importBooks(File file) {
        ArrayList<Book> wrongBooks = new ArrayList<>();
        try {
            String isbn;
            String title;
            String publisher;
            int edition;
            double cost;
            int quantity;
            
            Node nNode;
            Node jNode;
            Element eElement;
            Element element;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("book");
            for (int i = 0, n = nList.getLength(); i < n; i++) {
                nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ArrayList<String> authors = new ArrayList<>();
                    eElement = (Element) nNode;
                    isbn = eElement.getElementsByTagName("isbn").item(0).getTextContent();
                    title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    publisher = eElement.getElementsByTagName("publisher").item(0).getTextContent();
                    edition = Integer.valueOf(eElement.getElementsByTagName("edition").item(0).getTextContent());
                    cost = Double.valueOf(eElement.getElementsByTagName("cost").item(0).getTextContent());
                    quantity = Integer.valueOf(eElement.getElementsByTagName("quantity").item(0).getTextContent());
                    element = (Element) eElement.getElementsByTagName("authors").item(0);
                    NodeList authorList = element.getElementsByTagName("author");
                    for (int j = 0, len = authorList.getLength(); j < len; j++) {
                        jNode = authorList.item(j);
                        authors.add(jNode.getTextContent());
                    }
                    Book book = new Book(isbn, title, publisher, edition, cost, quantity, authors);
                    if (isValidISBN(isbn)) {
                        // TODO: add to database, check if book already exist in database using isbn
                        // if already exist, then just add quantity
                    } else {
                        // invalid ISBN
                        wrongBooks.add(book);
                    }
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e);
        }
        
        return wrongBooks;
    }
    
    public static boolean isValidHKID(String id) {
        String hkid = id.toUpperCase();
        int n = hkid.length();
        return (n == 8 || n == 9) && (hkid.equals(generateHKID(hkid.substring(0, n-1))));
    }
    
    public static String generateHKID(String id) {
        String hkid = id.toUpperCase();
        int temp = 0;
        String digit;
        if (hkid.length() == 7) {
            // 8 digit HKID
            temp += 324 + (hkid.charAt(0) - 55) * 8;
            for (int i = 1, n = hkid.length(); i < n; i++) {
                temp += Integer.valueOf(hkid.substring(i, i+1)) * (8 - i);
            }
            temp = (11 - (temp % 11)) % 11;
            digit = (temp == 10 ? "A" : Integer.toString(temp));
        } else {
            // 9 digit HKID
            temp += (hkid.charAt(0) - 55) * 9 + (hkid.charAt(1) - 55) * 8;
            for (int i = 2, n = hkid.length(); i < n; i++) {
                temp += Integer.valueOf(hkid.substring(i, i+1)) * (9 - i);
            }
            temp = (11 - (temp % 11)) % 11;
            digit = (temp == 10 ? "A" : Integer.toString(temp));
        }
        return hkid + digit;
    }
    
    public static boolean isValidISBN(String isbn) {
        int n = isbn.length();
        return (n == 10 || n == 13) && (isbn.equals(generateISBN(isbn.substring(0, n-1))));
    }

    public static String generateISBN(String isbn) {
        int temp = 0;
        String digit;
        if (isbn.length() == 9) {
            // 10 digit ISBN
            for (int i = 0, n = isbn.length(); i < n; i++) {
                temp += Integer.valueOf(isbn.substring(i, i+1)) * (10 - i);
            }
            temp = (11 - (temp % 11)) % 11;
            digit = (temp == 10 ? "X" : Integer.toString(temp));
        } else {
            // 13 digit ISBN
            for (int i = 0, n = isbn.length(); i < n; i++) {
                temp += Integer.valueOf(isbn.substring(i, i+1)) * (i % 2 == 0 ? 1 : 3);
            }
            temp = (10 - (temp % 10)) % 10;
            digit = Integer.toString(temp);
        }
        return isbn + digit;
    }
}
