package ute.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
@NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "_id", nullable = false)
	private User user; // Reference to User

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "_id", nullable = false)
	private Product product; // Reference to Product

	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "_id", nullable = false)
	private Store store; // Reference to Store

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "_id", nullable = false)
	private Order order; // Reference to Order

	@Column(nullable = false, length = 1000)
	private String content; // Review content

	@Column(nullable = false)
	private int stars; // Rating in stars (you might consider using Integer if null values are allowed)

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, nullable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
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
}
