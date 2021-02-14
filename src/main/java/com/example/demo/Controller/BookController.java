package com.example.demo.Controller;

import com.example.demo.Domain.Book;
import com.example.demo.FirebaseService.BookFirebaseService;
import com.example.demo.FirestoreService.BookFirestoreService;
import com.example.demo.ModelAssembler.BookModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {

    private final BookFirestoreService bookFirestoreService;
    //private final BookFirebaseService bookFirebaseService;
    private final BookModelAssembler assembler;


    public BookController(BookFirestoreService bookFirestoreService, BookFirebaseService bookFirebaseService, BookModelAssembler assembler) {
        this.bookFirestoreService = bookFirestoreService;
        this.bookFirebaseService = bookFirebaseService;
        this.assembler = assembler;
    }




    @Autowired
    BookFirebaseService bookFirebaseService;


    @GetMapping("/books")
    @ResponseBody
    public CollectionModel<EntityModel<Book>> all() throws ExecutionException, InterruptedException {
        //bookFirebaseService.getAllBooks();
        //return bookFirebaseService.allBooks;

        bookFirebaseService.getAllBooks();

        List<EntityModel<Book>> books = bookFirebaseService.allBooks.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(books, linkTo(methodOn(com.example.demo.Controller.BookController.class).all()).withSelfRel());
    }

    @PostMapping("/createBook")
    public String createNewBook(@RequestBody Book newBook) throws ExecutionException, InterruptedException {
        return bookFirestoreService.saveBookDetails(newBook);
    }

    @GetMapping("/getBookDetails")
    public Book getBookDetails(@RequestHeader String id) throws ExecutionException, InterruptedException {
        return bookFirestoreService.getBookDetails(id);
    }

    @PutMapping("/updateBook")
    public String updateBook(@RequestBody Book newBook) throws ExecutionException, InterruptedException {
        return bookFirestoreService.updateBookDetails(newBook);
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestHeader String id) {
        return bookFirestoreService.deleteBook(id);
    }
}
