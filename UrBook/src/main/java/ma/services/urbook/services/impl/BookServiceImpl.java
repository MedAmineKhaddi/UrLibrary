package ma.services.urbook.services.impl;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.BookException;
import ma.services.urbook.Mappers.BookMapper;
import ma.services.urbook.Models.Book;
import ma.services.urbook.Payload.DTO.BookDTO;
import ma.services.urbook.Payload.Request.BookSearchRequest;
import ma.services.urbook.Payload.Response.PageResponse;
import ma.services.urbook.Repositories.BookRepository;
import ma.services.urbook.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO createBook(BookDTO bookDTO) throws BookException {
        if(bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new BookException("Book with ISBN " + bookDTO.getIsbn() + " already exists");
        }
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook=bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public List<BookDTO> createBooksBulk(List<BookDTO> bookDTOs) throws BookException {
        List<BookDTO> createdBooks = new ArrayList<>();
        for (BookDTO bookDTO : bookDTOs) {
            BookDTO book = createBook(bookDTO);
            createdBooks.add(book);
        }
        return createdBooks;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public BookDTO deleteBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        return null;
    }

    @Override
    public List<BookDTO> getBooks() throws BookException {
        List<BookDTO> list = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            BookDTO dto = bookMapper.toDTO(book);
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        return List.of();
    }

    @Override
    public BookDTO getBooksByISBN(String ISBN) {
        return null;
    }

    @Override
    public void deleteBookById(Long bookId) {

    }

    @Override
    public void handleDeleteBooks(Long bookId) {

    }

//    @Override
//    public PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest) {
//        return null;
//    }
}
