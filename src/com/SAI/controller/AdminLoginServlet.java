package com.SAI.controller;

import java.io.IOException;

import com.SAI.dao.AdminLoginDAO;
import com.SAI.model.AdminLogin;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
       HttpSession sess= request.getSession();
       
       
		String name= request.getParameter("adminName");
	    String password = request.getParameter("password"); 
	    
	    AdminLogin a = new AdminLogin();
		a.setUserName(name);
		a.setPassword(password);
		System.out.println(a.getUserName() + a.getPassword());
		AdminLoginDAO adminLoginDao = new AdminLoginDAO();
		boolean check = adminLoginDao.checkCredentials(a);
		if(check) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Products.jsp");
			sess.setAttribute("USERNAME", a.getUserName());
			dispatcher.forward(request, response); 
		}
		else {
			RequestDispatcher dispathcerOne = request.getRequestDispatcher("/loginfailed.jsp");
			dispathcerOne.forward(request, response);
		}
		
	}

}
