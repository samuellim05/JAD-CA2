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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		// TODO Auto-generated method stub
				String sID = request.getParameter("eID");
				int iID = Integer.parseInt(sID);
				String title = request.getParameter("eTitle");
				String author = request.getParameter("eAuthor"); 
				String sPrice = request.getParameter("ePrice");
				double dPrice = Double.parseDouble(sPrice);
				String sQuantity = request.getParameter("eQuantity"); 
				int iQuantity = Integer.parseInt(sQuantity);
				String publisher = request.getParameter("ePublisher"); 
				String publication_date = request.getParameter("ePublicationDate");
				String ISBN = request.getParameter("eIsbn"); 
				String genre = request.getParameter("eGenre");
				String rating = request.getParameter("eRating");
				String description = request.getParameter("eDescription");
				String img = request.getParameter("eImg");
				Book book = new Book(title, author, dPrice, iQuantity, publisher, publication_date, ISBN, genre, rating, description, img);
				//Create a book record in the database by sending it to the book class
				book.updateBook(iID);
				// Print a message to the console
		        System.out.println("Book edited: " + book.getTitle());

		        // Redirect or forward the user
		        response.sendRedirect(request.getContextPath()+"/JAD-main/success.jsp");
		doGet(request, response);
	}

}
