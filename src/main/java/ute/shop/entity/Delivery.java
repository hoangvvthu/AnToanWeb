package ute.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery")
@NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@Column(nullable = false, unique = true, length = 100)
	private String name;

	@Column(nullable = false, length = 1000)
	private String description;

	@Column(nullable = false)
	private BigDecimal price;

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

	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
}
