package ute.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product {

	// Constructor chỉ nhận ID
	public Product(int id) {
		this._id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(unique = true)
	private String slug; // Generate slug automatically based on `name`

	@Column(nullable = false, length = 1000)
	private String description;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private double promotionalPrice;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private int sold = 0;

	@Column(nullable = false)
	private Boolean isActive = true;

	@Column(nullable = false)
	private Boolean isSelling = true;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "list_images")
	private List<String> listImages;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category; // Tham chiếu đến Category

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "style_value_ids")
	private List<String> styleValueIds;

	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store; // Tham chiếu trực tiếp tới Store

	@Column(nullable = false)
	private int rating = 3;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
		slug = generateSlug(name); // Generate slug from name during creation
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
	
	public String getFirstImage() {
	    return listImages != null && !listImages.isEmpty() ? listImages.get(0) : "default.jpg";
	}


	// Example method to generate slug
	private String generateSlug(String name) {
		return name.toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
	}

	// One-to-Many relationship with Review
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>(); // Một Product có thể có nhiều Review

	// One-to-Many relationship with CartItem
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<>(); // Một Product có thể có nhiều CartItem

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems = new ArrayList<>(); // Một Product có thể có nhiều OrderItem

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserFollowProduct> userFollowProducts = new ArrayList<>(); // Một Product có thể có nhiều
																			// UserFollowProduct

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StyleValue> styleValues = new ArrayList<>(); // Một Product có thể có nhiều StyleValue

}