package com.ethan.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethan.bookclub.models.Book;
import com.ethan.bookclub.repository.BookRepository;

@Service
public class BookService {

  @Autowired
  BookRepository bookRepository;

  public void createBook(Book book) {
    bookRepository.save(book);
  }

  public List<Book> allBooks() {
    return bookRepository.findAll();
  }

// ************************** GET ONE BOOK **********************************

  public Book oneBook(Long id) {
    Optional<Book> optionalBook = bookRepository.findById(id);
    return optionalBook.orElse(null);
  }

  
  // if (optionalBook.isPresent()) {
  //   return optionalBook.get();
  // } else {
  //   return null;
  // }

  // ***************************UPDATE BOOK ****************************

  public void updateBook(Book book) {
    bookRepository.save(book);
  }

  // ****************************DELETE BOOK**********************************

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

}
