package ma.services.urbook.services;

import ma.services.urbook.Exceptions.BookException;
import ma.services.urbook.Payload.DTO.BookDTO;
import ma.services.urbook.Payload.Request.BookSearchRequest;
import ma.services.urbook.Payload.Response.PageResponse;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO) throws BookException;
    List<BookDTO> createBooksBulk(List<BookDTO> bookDTOs) throws BookException;
    BookDTO updateBook(BookDTO bookDTO);
    BookDTO deleteBook(BookDTO bookDTO);
    BookDTO getBookById(Long bookId);
    List<BookDTO> getBooks() throws BookException;
    List<BookDTO> getBooksByAuthor(String author);
    BookDTO getBooksByISBN(String ISBN);
    void deleteBookById(Long bookId);
    void handleDeleteBooks(Long bookId);
//    PageResponse<BookDTO> searchBooksWithFilters(
//            BookSearchRequest searchRequest
//    );


}
