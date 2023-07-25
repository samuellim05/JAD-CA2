/* 
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
*/

package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class verifyUserCA1
 */
@WebServlet("/VerifyUserCA1")
public class VerifyUserCA1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyUserCA1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();   //this will create an out object for printing to browser
		HttpSession session = request.getSession();
		
		//get login id and password
		String user = request.getParameter("loginid");
		String pwd = request.getParameter("password");
		int id = 0;
		String role = "";
		
		boolean found = false;

		//----------------START DATABASE CONNECTION----------------------

		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");

			// Step 2: Define Connection URL
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud/jad?user=trorkbipifbm4f8sysqo&password=pscale_pw_j8RqRC5MsQtS0cmfW3J8yQO61veWvToDFvpxc7FFWwM&serverTimezone=UTC";

			// Step 3: Establish connection to URL
			Connection conn = DriverManager.getConnection(connURL);
			// Step 4: Create Statement object
			Statement stmt = conn.createStatement();
			// Step 5: Execute SQL Command
			
			String sqlStr = "SELECT * FROM jad.members WHERE name=? AND password=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1,user);
			pstmt.setString(2,pwd);
			
			ResultSet rs = pstmt.executeQuery();

			// Step 6: Process Result
			if (rs.next()) {
				//set the found flag
				found = true;
				id = rs.getInt("id");
				role = rs.getString("role");
			} else {
				//print out not foiund message
			}

			// Step 7: Close connection
			conn.close();
		} catch (Exception e) {
			out.println("Error :" + e);
		}

		if (found) {			
			//System.out.println("record found"); //just for debugging purpose 
			session.setAttribute("sessUserID", id);
			session.setAttribute("sessUsername", user);
			session.setAttribute("sessUserRole", role);
			session.setAttribute("loginStatus", "success");
			// redirects user to the displayMember.jsp upon successful login
			if(role.equals("admin")){
	    		//redirect to admin homepage
	    		//response.sendRedirect("booksInventory.jsp?name="+user);
				response.sendRedirect(request.getContextPath() + "/JAD-main/booksInventory.jsp?name="+user);
	    	}else{
	    		 //redirect to customer homepage
	    		response.sendRedirect(request.getContextPath() + "/JAD-main/homepage.jsp");
	    	}
		} else {
			response.sendRedirect(request.getContextPath() + "/JAD-main/login.jsp?errCode=invalidLogin"); // Brings user to another webpage
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
