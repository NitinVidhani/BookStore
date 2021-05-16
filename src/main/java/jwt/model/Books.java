package jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String subtitle;
	private String isbn13;
	
	private String price;
	
	
	
	@Column(columnDefinition="TEXT")
	private String image;
	
	@Column(columnDefinition="TEXT")
	private String url;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	public Books(int id, String title, String subtitle, String isbn13, String image, String url) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.subtitle = subtitle;
//		this.isbn13 = isbn13;
//		this.image = image;
//		this.url = url;
//	}

//	public Books(String title, String subtitle, String isbn13, String image, String url) {
//		super();
//		this.title = title;
//		this.subtitle = subtitle;
//		this.isbn13 = isbn13;
//		this.image = image;
//		this.url = url;
//	}

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Books(int id, String title, String subtitle, String isbn13, String price, String image, String url) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.isbn13 = isbn13;
		this.price = price;
		this.image = image;
		this.url = url;
	}
	

}
