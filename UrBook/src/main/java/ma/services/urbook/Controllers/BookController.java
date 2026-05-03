package ma.services.urbook.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.BookException;
import ma.services.urbook.Payload.DTO.BookDTO;
import ma.services.urbook.Payload.Response.ApiResponse;
import ma.services.urbook.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO bookDTO) throws BookException {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    //change method
    @PostMapping("/bulk")
    public ResponseEntity<?> createBooksBulk(@Valid @RequestBody List<BookDTO> bookDTO) throws BookException {
        List<BookDTO> createdBooks = bookService.createBooksBulk(bookDTO);
        return ResponseEntity.ok(createdBooks);
    }

    @DeleteMapping("/delete-book/{bookID}")
    public ResponseEntity<?> deleteBook(@PathVariable  long bookID) throws BookException
    {
      bookService.deleteBook(bookID);

        ApiResponse apiResponse = new ApiResponse("the book deleted successfully",true);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) throws BookException {
        BookDTO bookDTO = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @Valid @RequestBody BookDTO bookDTO) throws BookException {
        BookDTO updatedBook = bookService.updateBook(bookDTO, bookId);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("permanent/{bookId}")
    public ResponseEntity<?> hardDeleteBook(@PathVariable Long bookId) throws BookException {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok(new ApiResponse("Book permanent deleted successfully, with id: "+bookId,true));
    }


}
