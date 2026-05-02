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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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
        book.isAvailableCopiesValid();
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


    //implements right now
    @Override
    public BookDTO getBookById(Long bookId) throws BookException {
        Book book =  bookRepository.findById(bookId).orElseThrow(()->new BookException("Book with ID " + bookId + " not found"));
        return bookMapper.toDTO(book);
    }

    //implements right now
    @Override
    public BookDTO getBooksByISBN(String ISBN) throws BookException{

        Book book = bookRepository.findByIsbn(ISBN).orElseThrow(()-> new BookException("Book with ISBN " + ISBN + " not found"));

        return bookMapper.toDTO(book);
    }

    //implements right now
    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long bookId) throws BookException {
        Book existingBook = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book with ID " + bookId + " not found"));
        bookMapper.updateEntityFromDTO(bookDTO, existingBook);
        existingBook.isAvailableCopiesValid();
        Book savedBook=bookRepository.save(existingBook);

        return bookMapper.toDTO(savedBook);
    }

 // implements right now
    @Override
    public void deleteBook(Long bookID) throws  BookException {
            Book existingBook = bookRepository.findById(bookID).orElseThrow(()->new BookException("Book with ID " + bookID + " not found"));
            existingBook.setActive(false);
            bookRepository.save(existingBook);
    }

    // implements right now
    @Override
    public List<BookDTO> getBooks() throws BookException {
        List<BookDTO> list = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            BookDTO dto = bookMapper.toDTO(book);
            list.add(dto);
        }
        return list;
    }

    // implements right now
    @Override
    public void handleDeleteBooks(Long bookId) throws BookException {
        Book existingBook = bookRepository.findById(bookId).orElseThrow(()->new BookException("Book with ID " + bookId + " not found"));
        bookRepository.delete(existingBook);
    }

    //implements right now
    @Override
    public PageResponse<BookDTO> searchBooksWithFilters(BookSearchRequest searchRequest) {

        Pageable pageable = createPageable(searchRequest.getPage(),
                searchRequest.getSize(),
                searchRequest.getSortBy(),
                searchRequest.getSortDirection());
        Page<Book> bookPage = bookRepository.searchBooksWithFilters(
                searchRequest.getSearchTerm(),
                searchRequest.getGenreId(),
                searchRequest.getAvailableOnly(),
                pageable
        );

        return convertToPageResponse(bookPage);
    }

    //implements right now
    @Override
    public long getTotalActiveBooks() {
        return bookRepository.countByActiveTrue();
    }

    //implements right now
    @Override
    public long getTotalAvailableBooks() {
        return bookRepository.countAvailableBooks();
    }


    private Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        size=Math.min(size,10);
        size =Math.max(size,1);

        Sort sort = sortDirection.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        return (Pageable) PageRequest.of(page, size, sort);
    }


    private  PageResponse<BookDTO> convertToPageResponse(Page<Book> books){
        List<BookDTO> bookDTOS = books.getContent()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(
                bookDTOS,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst(),
                books.isEmpty()
        );
    }
    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        return List.of();
    }
}
