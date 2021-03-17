package com.atguigu.junit;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.serviceimpl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-10 21:20
 */

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"爱情失败左更又点，过去就过去，呢个唔刚更唔系下一个咯","Yang",new BigDecimal(0),0,0,null));
        bookService.addBook(new Book(null,"依噶先确定好目标,将来要揾一个好工作,爱情呢样野放系第二位,刚心意就上","yang",new BigDecimal(0),0,0,null));

    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(20);
    }

    @Test
    public void updateBookById() {
        bookService.updateBookById(new Book(null,"依噶先确定好目标,将来要揾一个好工作,爱情呢样野放系第二位,刚心意就上","Yang",new BigDecimal(0),0,0,null),26);
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(25);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForItemsByPrice(){
        Page page = bookService.pageByPrice(1,4,10,50);
        for (Object book : page.getItems()){
            System.out.println(book);
        }
    }
}
