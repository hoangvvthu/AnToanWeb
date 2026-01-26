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
@Table(name = "style_value")
@NamedQuery(name = "StyleValue.findAll", query = "SELECT sv FROM StyleValue sv")
public class StyleValue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@Column(nullable = false, unique = true, length = 32)
	private String name;

	@ManyToOne
	@JoinColumn(name = "style_id", nullable = false)
	private Style style; // Tham chiếu đến Style

	@Column(nullable = false)
	private Boolean isDeleted = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
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

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product; // Tham chiếu đến Product

	@OneToOne
	@JoinColumn(name = "cart_item_id", nullable = false) // Foreign key trỏ đến CartItem
	private CartItem cartItem; // Tham chiếu đến CartItem

}
