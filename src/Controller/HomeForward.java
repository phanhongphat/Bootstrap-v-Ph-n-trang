package Controller;

import java.io.IOException;
import java.util.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Emp;
import DAO.HomeDAO;
import DB.DBConnection;


@WebServlet("/HomeForward")
public class HomeForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HomeForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String pageidstr = request.getParameter("pageid");
		
		//ép kiểu string sang int để tính start
		int pageid = Integer.parseInt(pageidstr);
		int count = 7;
		//neu pageid = 1 => ko phan trang
		//neu pageid != 1 => phan trang cho no
		
		if (pageid == 1)
		{
			
		}
		else 
		{
			pageid = pageid - 1;
			pageid = pageid * count +1;
		}
		Connection conn = DBConnection.CreateConecction();
		
		List<Emp> list = HomeDAO.DisplayEmp(pageid - 1, count, conn);
			
		int sumrow = HomeDAO.countrow(conn);
		int maxpageid = (sumrow/count)+1;
		request.setAttribute("maxpageid",maxpageid);
		
		
		request.setAttribute("listemp", list);
		request.setAttribute("numberpage", pageid);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/View/Home.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
