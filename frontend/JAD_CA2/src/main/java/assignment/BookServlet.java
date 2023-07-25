/* 
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
*/

package assignment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sID = request.getParameter("id");
		String title = request.getParameter("Title");
		String author = request.getParameter("Author"); 
		String sPrice = request.getParameter("Price");
		
		String sQuantity = request.getParameter("Quantity"); 
		
		String publisher = request.getParameter("Publisher"); 
		String publication_date = request.getParameter("PublicationDate");
		String ISBN = request.getParameter("Isbn"); 
		String genre = request.getParameter("genre");
		String rating = request.getParameter("Rating");
		String description = request.getParameter("Description");
		String img = request.getParameter("Img");
		if(img == null||img=="") {
			img = "img/defaultImg.jpg";
		}
		
			double dPrice = Double.parseDouble(sPrice);
		int iQuantity = Integer.parseInt(sQuantity);
		Book book = new Book(title, author, dPrice, iQuantity, publisher, publication_date, ISBN, genre, rating, description, img);
		//Create a book record in the database by sending it to the book class
		book.createBook();
		// Print a message to the console
        System.out.println("Book saved: " + book.getTitle());

        // Redirect or forward the user
        response.sendRedirect(request.getContextPath()+"/JAD-main/success.jsp");
		
			
		
		
	
		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle DELETE operation
        // Extract book ID from the request and delete the corresponding book from the database
		 String Id = request.getParameter("Id");
		 
    }
}
