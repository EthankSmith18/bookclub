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

  public Book oneBook(Long id) {
    Optional<Book> optionaBook = bookRepository.findById(id);
    if (optionaBook.isPresent()) {
      return optionaBook.get();
    } else {
      return null;
    }
  }

  // Edit a Book
  public void updateBook(Book book) {
    bookRepository.save(book);
  }

  // Delete
  public void deleteBook(Book book) {
    bookRepository.delete(book);
  }
  public void deleteBook(Long id) {
  }

}
