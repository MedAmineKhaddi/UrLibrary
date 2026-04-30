package ma.services.urbook.Controllers;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.BookException;
import ma.services.urbook.Payload.DTO.BookDTO;
import ma.services.urbook.Payload.Response.ApiResponse;
import ma.services.urbook.services.BookService;
import org.aspectj.apache.bcel.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")

public class BookController {


    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) throws BookException {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<BookDTO>> getAllBooks() throws BookException {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @DeleteMapping("/delete-book/{bookID}")
    public ResponseEntity<?> deleteBook(@PathVariable  long bookID) throws BookException
    {
      bookService.deleteBookById(bookID);

        ApiResponse apiResponse = new ApiResponse("the book deleted successfully",true);
        return ResponseEntity.ok(apiResponse);
    }

}
