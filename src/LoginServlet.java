import com.atguigu.bean.User;
import com.atguigu.service.userService;
import com.atguigu.service.serviceimpl.userServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mr.yang
 * @create 2020-10-18 10:52
 */
public class LoginServlet extends HttpServlet {

    private userService userService = new userServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //将请求参数封装成一个bean对象
        User user = new User(null,username,password,null);

        //2、验证用户名和密码是否正确
        if (userService.login(user) == null){
            //用户名或密码错误
            req.setAttribute("msg","用户名或密码输入错误");
            req.setAttribute("username",username);
            System.out.println("登录失败，用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            //用户名和密码正确
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
