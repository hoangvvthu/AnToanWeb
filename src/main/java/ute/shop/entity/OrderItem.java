package ute.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
@NamedQuery(name = "OrderItem.findAll", query = "SELECT oi FROM OrderItem oi")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "_id", nullable = false)
	private Order order; // Tham chiếu đến Order

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "_id", nullable = false)
	private Product product; // Tham chiếu đến Product

	@ElementCollection
	@Column(nullable = false)
	private List<String> styleValueIds; // List of selected style value IDs

	@Column(nullable = false)
	private int count; // Quantity of the product

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
}
