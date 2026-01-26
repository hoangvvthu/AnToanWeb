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
@Table(name = "storelevel")
@NamedQuery(name = "StoreLevel.findAll", query = "SELECT sl FROM StoreLevel sl")
public class StoreLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;

	@Column(nullable = false, unique = true, length = 32)
	private String name;

	@Column(nullable = false, unique = true)
	private Integer minPoint;

	@Column(nullable = false)
	private double discount;

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
	@OneToOne(mappedBy = "storeLevel")
	private Store store;
}
