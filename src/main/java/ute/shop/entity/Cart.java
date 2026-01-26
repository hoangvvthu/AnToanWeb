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
@Table(name = "cart")
@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = true)
	private Store store;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<>(); // Một Cart có thể có nhiều CartItem

	public double getTotalAmount() {
		if (cartItems == null || cartItems.isEmpty()) {
			return 0.0;
		}
		return cartItems.stream().mapToDouble(cartItems -> cartItems.getCount() * cartItems.getProduct().getPrice()).sum();
	}
}
