package com.atguigu.web;

import com.atguigu.bean.User;
import com.atguigu.service.userService;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 继承了BaseServlet，而BaseServlet重写了doPost方法
 * @author Mr.yang
 * @create 2020-11-07 20:56
 */
public class userServlet extends BaseServlet {
    private userService userService = WebUtils.getBean(userService.class);
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //将请求参数封装成一个bean对象
        User user = new User(null,username,password,null);
        User isuser = null;
//        //使用WebUtils获取请求参数
//        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());

        //2、验证用户名和密码是否正确
        if ((isuser = userService.login(user)) == null){
            //用户名或密码错误
            req.setAttribute("msg","用户名或密码输入错误");
            req.setAttribute("username",username);
            System.out.println("登录失败，用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            //用户名和密码正确
            HttpSession session = req.getSession();
            session.setAttribute("user",isuser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 处理用户注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Seesion中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1、获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //2、检查验证码是否正确
        if(token != null && token.equalsIgnoreCase(code)){
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

    /**
     * 处理用户注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁Session中用户登录的信息
        req.getSession().invalidate();
        //2.重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }
}
