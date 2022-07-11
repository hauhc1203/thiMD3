package Servlet;

import DAO.NVDAO;
import DAO.PhongBanDAO;
import model.NhanVien;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;


@WebServlet(urlPatterns = "/nv")
public class NVServlet extends HttpServlet {
    ArrayList<NhanVien> nhanViens;
    NVDAO nvdao=new NVDAO();
    PhongBanDAO phongBanDAO=new PhongBanDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action= req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action==null){
            action="";

        }

        switch (action){
            case  "create":
               showCreateForm(req,resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                int id= Integer.parseInt(req.getParameter("id"));
                nvdao.deleteByID(id);
                resp.sendRedirect("/nv");
                break;
            default:
              showAll(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action= req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action==null){
            action="";

        }

        switch (action){
            case  "create":
                creatNV(req,resp);
                break;
            case "edit":
                editNV(req,resp);
                break;
            case "search":

                searchNV(req,resp);
                break;
            default:
                showAll(req,resp);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }


    public void creatNV(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=req.getParameter("name");
        String email=req.getParameter("email");
        LocalDate ngaysinh= LocalDate.parse(req.getParameter("birthday"));

        String diachi=req.getParameter("diachi");
        String sdt=req.getParameter("sdt");
        int maP= Integer.parseInt(req.getParameter("maP"));
        nvdao.insert(new NhanVien(name,ngaysinh,diachi,sdt,email,phongBanDAO.findCByID(maP)));
        resp.sendRedirect("/nv");
    }


    public  void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        req.setAttribute("phongs",phongBanDAO.selectAll());

        requestDispatcher=req.getRequestDispatcher("createNV.jsp");
        requestDispatcher.forward(req,resp);
    }
    public  void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        int id= Integer.parseInt(req.getParameter("id"));

        req.setAttribute("phongs",phongBanDAO.selectAll());
        req.setAttribute("nv",nvdao.findCByID(id));
        requestDispatcher=req.getRequestDispatcher("editNV.jsp");
        requestDispatcher.forward(req,resp);
    }

    public  void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nhanViens=nvdao.selectAll();
        req.setAttribute("nhanviens",nhanViens);
        RequestDispatcher requestDispatcher =req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }
    public void editNV(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=req.getParameter("name");
        String email=req.getParameter("email");
        LocalDate ngaysinh= LocalDate.parse(req.getParameter("birthday"));
        int id= Integer.parseInt(req.getParameter("id"));
        String diachi=req.getParameter("diachi");
        String sdt=req.getParameter("sdt");
        int maP= Integer.parseInt(req.getParameter("maP"));
        nvdao.edit(new NhanVien(name,ngaysinh,diachi,sdt,email,phongBanDAO.findCByID(maP)),id);
        resp.sendRedirect("/nv");
    }

    public  void searchNV(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key=req.getParameter("key");
        key = "%"+key+"%";
        nhanViens=nvdao.search(key);
        req.setAttribute("nhanviens",nhanViens);
        RequestDispatcher requestDispatcher =req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }

}
