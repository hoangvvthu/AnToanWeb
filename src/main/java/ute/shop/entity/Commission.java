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
@Table(name = "commission")
@NamedQuery(name = "Commission.findAll", query = "SELECT c FROM Commission c")
public class Commission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;

	@Column(nullable = false, unique = true, length = 32)
	private String name;

	@Column(nullable = false, unique = true)
	private Double cost;

	@Column(nullable = false, length = 3000)
	private String description;

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
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	// Optional: One-to-One relationship with Store
	@OneToOne(mappedBy = "commission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Store store;

	@OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders; // Một Commission có thể có nhiều Order
}
