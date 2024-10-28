package jarvis.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Book {
    private int idBook = 1;
    private final int id;
    private String name;
    private String authors;
    private String genre;
    private double price;
    private Date publishDate;


    public Book(String name, String authors, String genre, double price, Date publishDate) {
        this.id = idBook++;
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
    }
}
