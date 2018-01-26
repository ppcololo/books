package com.example.bookshelf.controller;

import com.example.bookshelf.model.Book;
import com.example.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @RequestMapping("/delete/{id}")
    public String removeBook(@PathVariable("id") Long id) {
        bookRepository.delete(id);
        return "redirect:/books";
    }

    @RequestMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.findOne(id));
        return "bookdata";
    }

    @RequestMapping("/update")
    public String bookData(Book book) {
        bookRepository.updateBook(book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getIsbn(),
                book.getPrintYear());
        return "redirect:/books";
    }

    @RequestMapping("/read/{id}")
    public String readBook(@PathVariable("id") Long id) {
        bookRepository.readBook(id);
        return "redirect:/books";
    }
}
