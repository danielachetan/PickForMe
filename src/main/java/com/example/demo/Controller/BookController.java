package com.example.demo.Controller;

import com.example.demo.Domain.Book;
import com.example.demo.FirebaseService.BookFirebaseService;
import com.example.demo.FirestoreService.BookFirestoreService;
import com.example.demo.ModelAssembler.BookModelAssembler;
import com.example.demo.Repository.BookRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
    private final BookFirestoreService bookFirestoreService;
    //private final BookFirebaseService bookFirebaseService;
    private final BookModelAssembler assembler;
    private final BookRepository bookRepository;


    public BookController(BookFirestoreService bookFirestoreService, /*BookFirebaseService bookFirebaseService,*/ BookModelAssembler assembler, BookRepository bookRepository) {
        this.bookFirestoreService = bookFirestoreService;
        //this.bookFirebaseService = bookFirebaseService;
        this.assembler = assembler;
        this.bookRepository = bookRepository;
    }




    @GetMapping("/books")

    public /*@ResponseBody CollectionModel<EntityModel<Book>>*/List<Book> all() throws ExecutionException, InterruptedException {
        //return bookFirebaseService.getAllBooks();
        //return bookFirebaseService.allBooks;

        //bookFirebaseService.getAllBooks();

        //List<Book> books = bookFirebaseService.getAllBooks();//.stream()
                //.map(assembler::toModel)
                //.collect(Collectors.toList());

        //return books; //CollectionModel.of(books, linkTo(methodOn(com.example.demo.Controller.BookController.class).all()).withSelfRel());

        //return (List<Book>) bookRepoQuerySnapshotl();
    	
    	return bookFirestoreService.getAllBooks();
    }

    @PostMapping("/books")
    public /*@ResponseBody ResponseEntity<?>*/ void addBook(@RequestBody Book book) throws ExecutionException, InterruptedException {
        /*
        EntityModel<Book> entityModel = assembler.toModel(bookFirebaseService.saveBook(book));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

         */

        bookRepository.save(book);
    }
    /*
    public String createNewBook(@RequestBody Book newBook) throws ExecutionException, InterruptedException {
        return bookFirestoreService.saveBookDetails(newBook);
    }
    */

    @GetMapping("/getBookDetails")
    public Book getBookDetails(@RequestHeader String id) throws ExecutionException, InterruptedException {
        return bookFirestoreService.getBookDetails(id);
    }

    
    @PutMapping("/books")
    public String updateBook(@RequestBody Book newBook) throws ExecutionException, InterruptedException {
        return bookFirestoreService.updateBookDetails(newBook);
    }

    @DeleteMapping("/books")
    public String deleteBook(@RequestHeader String id) {
        return bookFirestoreService.deleteBook(id);
    }
    
}
