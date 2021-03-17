package com.atguigu.web;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-10 21:42
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = WebUtils.getBean(BookService.class);

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳转到图书列表页面
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);//会有一个Bug，请求转发会使表单重复提交
        //需要用请求重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取需要修改的图书编号
        int id =WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用BookService.queryBookById()获取该图书信息
        Book book = bookService.queryBookById(id);
        //3.将该图书信息保存到request域中
        req.setAttribute("book",book);
        //4.请求转发到book_edit.jsp页面中
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数，封装成一个Bean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.获取需要修改的图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //3.调用bookService.update()修改图书
        bookService.updateBookById(book,id);
        //4.请求重定向返回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数id，图书编程
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        //3.请求重定向会图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数(当前页码)pageNo和(当前页显示的数量)pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookServicce.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
