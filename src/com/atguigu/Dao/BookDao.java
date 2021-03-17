package com.atguigu.Dao;

import com.atguigu.bean.Book;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-09 23:13
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 通过图书编号，删除图书
     * @param id
     * @return
     */
    public int deleteById(Integer id);

    /**
     * 通过图书编号，修改图书信息
     * @param book
     * @param id
     * @return
     */
    public int updateById(Book book,Integer id);

    /**
     * 通过图书编号，查询图书信息
     * @param id
     * @return
     */
    public Book queryById(Integer id);

    /**
     * 查询所有图书信息
     * @return
     */
    public List<Book> queryBook();

    /**
     * 获取总计录数
     * @return
     */
    Integer queryForPageToalCount();

    /**
     * 获取当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForItems(int begin, int pageSize);

    /**
     * 获取区间内的总记录数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageToalCountByPrice(int min, int max);

    /**
     * 获取区间内的图书信息
     * @param min
     * @param max
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize);
}
