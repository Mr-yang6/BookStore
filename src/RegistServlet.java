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
 * @create 2020-10-18 9:42
 */

public class RegistServlet extends HttpServlet {

    private userService userService = new userServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //2、检查验证码是否正确 ===> (还没学到服务器如何生成验证码，先把验证码写死为abcde)
        if("abcde".equalsIgnoreCase(code)){
            //验证码正确,检查用户名是否正确可用
            if(userService.existsUsername(username)){
                //用户名已存在
                request.setAttribute("msg","用户名已存在！！！");
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                System.out.println("用户名["+ username +"]已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            } else {
                //用户名未注册
                //调用service保存到数据库中
                userService.registUser(new User(null,username,password,email));
                //注册成功后跳转到注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        } else {
            //验证码不正确
            //跳回注册页面
            request.setAttribute("msg","验证码错误");
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            System.out.println("验证码[" + code + "]不正确");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
