import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取传来的值
        String fname=request.getParameter("fname");

        String priceStr=request.getParameter("price");
        Integer price=Integer.parseInt(priceStr);

        String fcountStr=request.getParameter("fcount");
        Integer fcount= Integer.parseInt(fcountStr);

        String remark=request.getParameter("remark");

        //测试
        System.out.println("fname = " +fname);
        System.out.println("price = " +price);
        System.out.println("fcount = " +fcount);
        System.out.println("remark = " +remark);



    }
}
