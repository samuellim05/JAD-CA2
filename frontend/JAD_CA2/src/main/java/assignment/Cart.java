/* 
	-Name: Samuel Lim Zhi En
	-Class: DIT/2A/02
	-Admission no. 2227874
*/

package assignment;

public class Cart extends Book{
	private int QuantityInCart;
	private double Total;
	public Cart(String title, String author, double price, int quantity, String publisher, String publication_date,
			String iSBN, String genre, String rating, String description, String img, int quantityInCart) {
		super(title, author, price, quantity, publisher, publication_date, iSBN, genre, rating, description, img);
		QuantityInCart = quantityInCart;
	}
	public Cart() {
		
	}

	public int getQuantityInCart() {
		return QuantityInCart;
	}

	public void setQuantityInCart(int quantityInCart) {
		QuantityInCart = quantityInCart;
	} 
	
	public double getTotal() {
		Total = getQuantityInCart()*getPrice();
		return Total;
	}
}
