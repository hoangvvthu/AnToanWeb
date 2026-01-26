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
@Table(name = "store")
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;

	@Column(nullable = false, unique = true, length = 100)
	private String name;

	@Column(length = 255)
	private String bio;

	@Column(unique = true)
	private String slug;

	@OneToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "_id", nullable = false)
	private User owner;

	@ManyToMany
	@JoinTable(name = "store_staff", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> staffs;

	@Column(nullable = false)
	private Boolean isActive = true;

	@Column(nullable = false)
	private Boolean isOpen = true;

	private String avatar;
	private String cover;

	@ElementCollection
	private List<String> featuredImages;

	@Column(nullable = false)
	private Double commissionSold;

	private Integer point;
	private Double rating;

	@Column(nullable = false)
	private double e_wallet = 0.0;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		if (this.slug == null || this.slug.isEmpty()) {
			this.slug = name.toLowerCase().replace(" ", "-");
		}
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "storeLevel_id", referencedColumnName = "_id", nullable = false)
	private StoreLevel storeLevel;

	@OneToOne
	@JoinColumn(name = "commission_id", referencedColumnName = "_id", nullable = false)
	private Commission commission;

	@OneToMany(mappedBy = "store")
	private List<UserFollowStore> followers = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<Transaction> transactions = new ArrayList<>();
}
