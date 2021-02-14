package com.example.demo.FirestoreService;

import com.example.demo.Domain.Book;
import com.example.demo.FirebaseInitialize;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BookFirestoreService {

    //FirebaseInitialize firebaseInitialize = new FirebaseInitialize();

    public String saveBookDetails(Book book) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection("books").add(book);
        return "Added document with ID: " + collectionsApiFuture.get().getId();
    }

    public List<Book> getAllBooks() throws ExecutionException, InterruptedException {
        //firebaseInitialize.initialize();
        List<Book> allBooks = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("books").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents)
            allBooks.add(document.toObject(Book.class));
        System.out.println(allBooks.size());
        return allBooks;
    }

    public Book getBookDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("books").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Book book = null;

        if(document.exists()) {
            book = document.toObject(Book.class);
            return book;
        } else {
            return null;
        }
    }

    public String updateBookDetails(Book book) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection("books").add(book);
        return "Book with ID " + collectionsApiFuture + " was updated";
    }

    public String deleteBook(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("books").document(id).delete();
        return "Document with ID " + id + " has been deleted";
    }
}
