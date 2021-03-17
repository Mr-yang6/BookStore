package com.atguigu.junit;

import com.atguigu.Dao.BookDao;
import com.atguigu.Dao.Daoimpl.BookDaoImpl;
import com.atguigu.bean.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-09 23:46
 */
public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"光辉岁月","Beyond",new BigDecimal(5000),5000,10000,null));
        bookDao.addBook(new Book(null,"真的爱你","Beyond",new BigDecimal(6000),8000,10000,null));
        bookDao.addBook(new Book(null,"喜欢你","Beyond",new BigDecimal(7000),6000,10000,null));
        bookDao.addBook(new Book(null,"海阔天空","Beyond",new BigDecimal(8000),7000,10000,null));
        bookDao.addBook(new Book(null,"备胎","Yang",new BigDecimal(0),0,0,null));
    }

    @Test
    public void deleteById() {
        bookDao.deleteById(21);
    }

    @Test
    public void updateById() {
        bookDao.updateById(new Book(null,"你有好多野放唔低么,做人要潇洒滴","Yang",new BigDecimal(100),0,0,null),25);
    }

    @Test
    public void queryById() {
       Book book = bookDao.queryById(25);
       System.out.println(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = bookDao.queryBook();
        for(Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageToalCount(){
        System.out.println(bookDao.queryForPageToalCount());
    }

    @Test
    public void queryForItems(){
        for (Book book : bookDao.queryForItems(0,4)){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageToalCountByPrice(){
        System.out.println(bookDao.queryForItemsByPrice(10,50,1,4));
    }

    @Test
    public void queryForItemsByPrice(){
        for (Book book : bookDao.queryForItemsByPrice(10,50,1,4)){
            System.out.println(book);
        }
    }
}
