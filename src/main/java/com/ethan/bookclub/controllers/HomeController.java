package com.ethan.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ethan.bookclub.models.Book;
import com.ethan.bookclub.models.LoginUser;
import com.ethan.bookclub.models.User;
import com.ethan.bookclub.services.BookService;
import com.ethan.bookclub.services.UserService;

@Controller
public class HomeController {

  @Autowired
  private UserService userServ;
  @Autowired
  private BookService bookService;

  @GetMapping("/")
  public String index(Model model) {

    // Bind empty User and LoginUser objects to the JSP
    // to capture the form input
    model.addAttribute("newUser", new User());
    model.addAttribute("newLogin", new LoginUser());
    return "index.jsp";
  }

  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("newUser") User newUser,
      BindingResult result, Model model, HttpSession session) {

    // TO-DO Later -- call a register method in the service
    // to do some extra validations and create a new user!
    User user = userServ.register(newUser, result);

    if (result.hasErrors()) {
      // Be sure to send in the empty LoginUser before
      // re-rendering the page.
      model.addAttribute("newLogin", new LoginUser());
      return "index.jsp";
    }

    // No errors!
    // TO-DO Later: Store their ID from the DB in session,
    // in other words, log them in.
    session.setAttribute("userId", user.getId());

    return "redirect:/books";
  }

  @PostMapping("/login")
  public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
      BindingResult result, Model model, HttpSession session) {

    // Add once service is implemented:
    User user = userServ.login(newLogin, result);

    if (result.hasErrors()) {
      model.addAttribute("newUser", new User());
      return "index.jsp";
    }

    // No errors!
    // TO-DO Later: Store their ID from the DB in session,
    // in other words, log them in.
    session.setAttribute("userId", user.getId());

    return "redirect:/books";
  }

  // ****************************READ ALL**********************************
  @GetMapping("/books")
  public String entryPage(Model model, HttpSession session) {
    if (session.getAttribute("userId") == null) {
      return "redirect:/";
    }
    Long id = (Long) session.getAttribute("userId");
    List<Book> books = bookService.allBooks();
    User user = userServ.getById(id);
    model.addAttribute("user", user);
    model.addAttribute("books", books);
    return "home.jsp";
  }

  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  // ************************************* */ CREATE  // **********************************************

  @PostMapping("/createbook")
  public String makeBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    if (result.hasErrors()) {
      return "new.jsp";
    } else {
      bookService.createBook(book);
      return "redirect:/books";
    }
  }

  @RequestMapping("/books/new")
  public String newBook(@ModelAttribute("book") Book book, BindingResult result, HttpSession session){
    if(session.getAttribute("userId")==null){
      return "redirect:/";
    }
    return "new.jsp";
  }

  // **************************************** UPDATE  // ***********************************************

  @GetMapping("/books/{id}/edit")
  public String showOne(@PathVariable("id")Long id, Model model, HttpSession session){
    if(session.getAttribute("userId")==null){
      return "redirect:/";
    }
    Book book = bookService.oneBook(id);
    model.addAttribute("book", book);
    return "edit.jsp";
  }

  @PutMapping("/edit/{id}")
  public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book,  BindingResult result, Model model) {
    if(result.hasErrors()){
      model.addAttribute("book", book);
      return "edit.jsp";

    }else{
          bookService.updateBook(book);
          return "redirect:/books";
        }
  }



  // ******************************READ ONE***********************
  @GetMapping("/books/{id}")
  public String oneBook(@PathVariable("id")Long id, Model model, HttpSession session){
    if(session.getAttribute("userId")==null){
      return "redirect:/";
    }
    Book book = bookService.oneBook(id);
    model.addAttribute("book", book);
    return "show.jsp";
  }

  // ***************************DELETE***************************
  @DeleteMapping("/books/delete/{id}")
  public String delete(@PathVariable("id")Long id, HttpSession session){
    bookService.deleteBook(id);
    return "redirect:/books";
  
  }


  
  
}
