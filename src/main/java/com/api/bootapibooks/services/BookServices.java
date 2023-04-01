package com.api.bootapibooks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.api.bootapibooks.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.bootapibooks.entities.Book;

@Component
public class BookServices {
//    private static List<Book> list = new ArrayList<>();
//
//    static {
//        list.add(new Book(1, "Spring Boot", "spring_boot"));
//        list.add(new Book(2, "Spring MVC", "spring_mvc"));
//        list.add(new Book(3, "Spring JPA", "spring_jpa"));
//    }

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> books = (List<Book>) this.bookRepository.findAll();
        return books;
    }

    public Book getBookById(int id) {
        Book book = null;
        try {
//            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book book) {
        book = this.bookRepository.save(book);
        return book;
    }

    public void deleteBook(int id) {
//        list=list.stream().filter(book -> book.getId()!=id).collect(Collectors.toList());
        this.bookRepository.deleteById((Integer) id);
    }

    public void updateBook(Book book, int id) {
//        list.forEach(book1 -> {
//            if (book1.getId() == id) {
//                book1.setTitle(book.getTitle());
//                book1.setAuthor(book.getAuthor());
//            }
//        });
        book.setId(id);
        this.bookRepository.save(book);
    }
}
