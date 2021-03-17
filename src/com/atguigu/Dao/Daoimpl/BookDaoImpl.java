package com.atguigu.Dao.Daoimpl;

import com.atguigu.Dao.BaseDao;
import com.atguigu.Dao.BookDao;
import com.atguigu.bean.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-09 23:23
 */
@Repository
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`)values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
       public int deleteById(Integer id) {
        String sql = "delete from t_book where id = ?";
        int result = update(sql,id);
        SetId();
        return result;
    }

    @Override
    public int updateById(Book book, Integer id) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),id);
    }

    @Override
    public Book queryById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where id = ?";
        return (Book) getInstance(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBook() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book";
        return getForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageToalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) getValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book limit ?,? ";
        return getForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageToalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) getValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where price between ? and ? order by price limit ?,?";
        return getForList(Book.class,sql,min,max,begin,pageSize);
    }
}
