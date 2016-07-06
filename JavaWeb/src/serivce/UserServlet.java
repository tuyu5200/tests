package serivce;

import model.UserEntity;
import util.JDBCutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Tuyu on 2016/6/19 9:09 .
 */
@WebServlet(name = "serivce.UserServlet")
public class UserServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        UserEntity userAccount = new UserEntity();
        String username = request.getParameter("userName");
        String pwd = request.getParameter("userPwd");
        String email = request.getParameter("email");
        JDBCutil db=new JDBCutil();
        db.getConnection();
        String sql="insert into userinfo values("+username+","+pwd+","+email+")";
        db.doInsert(sql);
        db.closeConn();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
