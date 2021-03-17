package com.atguigu.service.serviceimpl;

import com.atguigu.Dao.BookDao;
import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-10 21:17
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBookById(Book book, Integer id) {
        bookDao.updateById(book,id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBook();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        //设置每页的显示数量
        page.setPageSize(pageSize);

        //获取并设置总记录数
        Integer pageToalCount = bookDao.queryForPageToalCount();
        page.setPageTotalCount(pageToalCount);

        //设置总页码
        Integer pageToal = pageToalCount / pageSize;
        if(pageToalCount % pageSize > 0)
            pageToal += 1;
        page.setPageTotal(pageToal);

        //设置当前页码
        page.setPageNo(pageNo);

        //获取当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(bookDao.queryForItems(begin,pageSize));
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();

        //设置每页的显示数量
        page.setPageSize(pageSize);

        //获取并设置总记录数
        Integer pageToalCount = bookDao.queryForPageToalCountByPrice(min,max);
        page.setPageTotalCount(pageToalCount);

        //设置总页码
        Integer pageToal = pageToalCount / pageSize;
        if(pageToalCount % pageSize > 0)
            pageToal += 1;
        page.setPageTotal(pageToal);

        //设置当前页码
        page.setPageNo(pageNo);

        //获取当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(bookDao.queryForItemsByPrice(min,max,begin,pageSize));
        return page;
    }
}
