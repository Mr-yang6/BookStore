package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-10 21:09
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 通过图书编号，删除图书信息
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 通过图书编号，修改图书信息
     * @param book
     * @param id
     */
    public void updateBookById(Book book,Integer id);

    /**
     * 通过图书编号，查找图书信息
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查找所有图书信息
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 设置分页信息
     * @param pageNo   当前页码
     * @param pageSize 当前页显示的数量
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 通过图书价格，搜索图书信息
     * @param pageNo
     * @param pageSize
     * @param min   价格区间的最小值
     * @param max   价格区间的最大值
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
