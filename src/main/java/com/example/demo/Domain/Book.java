package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Book {

    private @Id String id;
    private String title;
    private String author;
    //private List<String> genre;
    private String photo;
    private String description;
    private double rating;
    private int numberOfPages;

    public Book() {
        title = "";
        author = "";
        //genre = new ArrayList<String>();
        photo = "";
        description = "";
        rating = 0;
        numberOfPages = 0;
    }

    public Book(String title, String author, String photo, String description, int numberOfPages) {
        this.title = title;
        this.author = author;
        //this.genre = new ArrayList<String>(Arrays.asList(genre));
        this.photo = photo;
        this.description = description;
        rating = 0;
        this.numberOfPages = numberOfPages;
    }


    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    //public List<String> getGenre() {
        //return genre;
    //}

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    /*
    public void setId(String id) {
        this.id = id;
    }

     */

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //public void setGenre(List<String> genre) {

        //this.genre.addAll(genre);
    //}

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        Book book = (Book) o;
        return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title) &&
                Objects.equals(this.author, book.author);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.author, this.genre, this.photo, this.description,
                this.rating, this.numberOfPages);
    }

*/

    @Override
    public String toString() {
        String string = "'" + getTitle() + "' - " + getAuthor();
        return string;
    }


}
