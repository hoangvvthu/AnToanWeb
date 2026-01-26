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
@Table(name = "user_follow_store")
@NamedQuery(name = "UserFollowStore.findAll", query = "SELECT ufs FROM UserFollowStore ufs")
public class UserFollowStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // Reference to User

	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store; // Reference to Store

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, nullable = false)
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
}
