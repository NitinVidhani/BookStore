package jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="favorites")
public class Favorites {
	
	@Override
	public String toString() {
		return "Favorites [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", isbn13=" + isbn13 + ", price="
				+ price + ", userName=" + userName + ", image=" + image + ", url=" + url + "]";
	}

	@Id  
	@GeneratedValue  
	private Integer id;
	private String title;
	private String subtitle;
	private String isbn13;
	private String price;
	private String userName;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(columnDefinition="TEXT")
	private String image;
	
	@Column(columnDefinition="TEXT")
	private String url;

	
	



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Favorites() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Favorites(Integer id, String title, String subtitle, String isbn13, String image, String url) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.isbn13 = isbn13;
		this.image = image;
		this.url = url;
	}
	public Favorites(String title, String subtitle, String isbn13, String image, String url) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.isbn13 = isbn13;
		this.image = image;
		this.url = url;
	}
	public void setPrice(String price) {
		
		this.price=price;
	}
	
	public Favorites(Integer id, String title, String subtitle, String isbn13, String userName, String image,
			String url) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.isbn13 = isbn13;
		this.userName = userName;
		this.image = image;
		this.url = url;
	}
	public String getPrice() {
		// TODO Auto-generated method stub\
		return price;
		
	}
	
	


}
