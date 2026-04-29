package ma.services.urbook.Mappers;


import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.BookException;
import ma.services.urbook.Models.Book;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.BookDTO;
import ma.services.urbook.Repositories.BookRepository;
import ma.services.urbook.Repositories.GenreRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public BookDTO toDTO(Book book) throws BookException {
       if(book == null){
           return null;
       }
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .price(book.getPrice())
                .isbn(book.getIsbn())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .active(book.getActive())
                .language(book.getLanguage())
                .publisher(book.getPublisher())
                .pages(book.getPages())
                .availableCopies(book.getAvailableCopies())
                .coverImageUrl(book.getCoverImageUrl())
                .totalCopies(book.getTotalCopies())
                .publishedDate(book.getPublishedDate())
                .genreCode(book.getGenre().getCode())
                .genreName(book.getGenre().getName())
                .genreId(book.getGenre().getId())
                .build();
    }

    public Book toEntity(BookDTO bookDTO) throws BookException {
        if(bookDTO == null){
            return null;
        }
        Book book = Book.builder()
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .price(bookDTO.getPrice())
                .isbn(bookDTO.getIsbn())
                .createdAt(bookDTO.getCreatedAt())
                .updatedAt(bookDTO.getUpdatedAt())
                .active(bookDTO.getActive())
                .language(bookDTO.getLanguage())
                .publisher(bookDTO.getPublisher())
                .pages(bookDTO.getPages())
                .availableCopies(bookDTO.getAvailableCopies())
                .coverImageUrl(bookDTO.getCoverImageUrl())
                .totalCopies(bookDTO.getTotalCopies())
                .publishedDate(bookDTO.getPublishedDate())
                .build();
                if(bookDTO.getGenreId() != null){
                    Genre genre = genreRepository.findById(bookDTO.getGenreId()).orElseThrow(()->new BookException("Genre with id"+bookDTO.getGenreId()+" Not Found"));
                    book.setGenre(genre);

                }

                return bookRepository.save(book);

    }

    public BookDTO updateEntityFromDTO(BookDTO bookDTO,Book book) throws BookException {
        if(bookDTO == null || book == null){
            return null;
        }
        /*you can not update ISBN*/
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
//        book.setIsbn(bookDTO.getIsbn());
        book.setCreatedAt(bookDTO.getCreatedAt());
        book.setUpdatedAt(bookDTO.getUpdatedAt());
        if(bookDTO.getActive()!=null){book.setActive(bookDTO.getActive());}
        book.setLanguage(bookDTO.getLanguage());
        book.setPublisher(bookDTO.getPublisher());
        book.setPages(bookDTO.getPages());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setCoverImageUrl(bookDTO.getCoverImageUrl());
        book.setTotalCopies(bookDTO.getTotalCopies());
        book.setPublishedDate(bookDTO.getPublishedDate());
        if(bookDTO.getGenreId() != null){
            Genre genre = genreRepository.findById(bookDTO.getGenreId()).orElseThrow(()-> new BookException("Genre with id: "+bookDTO.getGenreId()+"not founded"));
            book.setGenre(genre);
        }

    return bookDTO;
    }

}
