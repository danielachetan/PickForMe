package com.example.demo.FirebaseService;

import com.example.demo.Domain.Book;
import com.example.demo.FirebaseInitialize;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//@Service
public class BookFirebaseService {

    FirebaseInitialize firebaseInitialize;
    FirebaseDatabase dbFirebase;
    DatabaseReference dbReference;
    public List<Book> allBooks = new ArrayList<>();

    public BookFirebaseService(FirebaseInitialize f) {
        //firebaseInitialize = new FirebaseInitialize();
        //firebaseInitialize.initialize();
        firebaseInitialize = f;
        dbFirebase = FirebaseDatabase.getInstance();
        dbReference = dbFirebase.getReference();
    }

    public List<Book> getAllBooks() throws InterruptedException, ExecutionException {
        List<Book> books = new ArrayList<>();
        dbReference.child("books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Book book = ds.getValue(Book.class);
                        if (!books.contains(book))
                            books.add(book);
                        System.out.println(book.toString());
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Couldn't retrieve books.");
            }
        });
        return books;
    }

    public Book saveBook(Book book) {
        DatabaseReference booksRef = dbReference.child("books");

        DatabaseReference newBookRef = booksRef.push();
        newBookRef.setValueAsync(book);

        System.out.println("Your book was successfully added to the database!");

        return book;
    }
}
