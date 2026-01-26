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
@Table(name = "transaction")
@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "_id") // Foreign key referencing User's ID
	private User user;

	@Column(nullable = false)
	private Boolean isUp; // Indicates whether the transaction is a withdrawal or deposit

	@Column(nullable = false)
	private double amount; // Amount of money involved in the transaction

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
	@JoinColumn(name = "store_id", referencedColumnName = "_id", nullable = false)
	private Store store; // Reference to Store
}
