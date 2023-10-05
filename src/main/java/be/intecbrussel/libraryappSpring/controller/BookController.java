package be.intecbrussel.libraryappSpring.controller;

import be.intecbrussel.libraryappSpring.model.Book;
import be.intecbrussel.libraryappSpring.service.BookService;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/book")
    public Optional<Book> addBook(@RequestBody Book book) {
        System.out.println(book);
        return bookService.addBook(book);
    }

    @GetMapping("book/{id}")
    @ResponseBody
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @GetMapping("book/title/{title}")
    @ResponseBody
    public ResponseEntity<Book[]> getBookByName(@PathVariable String title) {

        Optional<Book[]> booksByName = bookService.getBookByName(title);

        if (booksByName.isPresent()) {

            return ResponseEntity.ok(booksByName.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //TODO
    @GetMapping("book/genre/{genre}")
    public ResponseEntity<Book[]> getBookByGenre(@PathVariable String genre) {
        Optional<Book[]> booksByGenre = bookService.getBookByGenre(genre);

        if (booksByGenre.isPresent()){
            return ResponseEntity.ok(booksByGenre.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(booksByGenre.get());
        }
    }


    @GetMapping()
    public List<Book> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return allBooks;
    }

    @DeleteMapping("/book/delete-book/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
        Optional<Book> deletedBook = bookService.deleteBookById(id);

        if (deletedBook.isPresent()){
            return ResponseEntity.ok(deletedBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // update the entire book
    @PatchMapping("book/update/{id}")
    public ResponseEntity<Book> updateBookName(@PathVariable Long id, @RequestBody Book book) {

        Optional<Book> updateBook = bookService.getBook(id);
        if (updateBook.isPresent()) {
            Book bookToUpdate = updateBook.get();
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setISBN(book.getISBN());
            bookToUpdate.setGenre(book.getGenre());
            bookToUpdate.setAvailable(book.isAvailable());
            bookToUpdate.setReleaseDate(book.getReleaseDate());

            bookService.save(bookToUpdate);
            return ResponseEntity.ok(updateBook.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
        }
    }

    // update only the available property
    @PatchMapping("book/update/available/{id}")
    public ResponseEntity<Book> updateBookAvailable(@PathVariable Long id, @RequestBody Book book) {

        Optional<Book> foundBook = bookService.getBook(id);
        if (foundBook.isPresent()) {
            foundBook.get().setAvailable(book.isAvailable());
            bookService.save(foundBook.get());
            return ResponseEntity.ok(foundBook.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
        }
    }

    @GetMapping("/available-books/{availableBook}")
    @ResponseBody
    public ResponseEntity<Book[]> getAvailableBooks(@PathVariable boolean availableBook) {
        Optional<Book[]> allAvailableBooks = bookService.getAllAvailableBooks(availableBook);
        if (allAvailableBooks.isPresent()){
            return ResponseEntity.ok(allAvailableBooks.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allAvailableBooks.get());
        }
    }
}
