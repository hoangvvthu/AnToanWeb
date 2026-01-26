package ute.shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "style")
@NamedQuery(name = "Style.findAll", query = "SELECT s FROM Style s")
public class Style {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, unique = true, length = 32)
	private String name;

	@ElementCollection
	@Column(name = "category_ids", nullable = false)
	private List<String> categoryIds;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@Column(name = "updated_at")
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

	@OneToMany(mappedBy = "style", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StyleValue> styleValues = new ArrayList<>(); // Một Style có thể có nhiều StyleValue
	
	@ManyToMany(mappedBy = "styles", fetch = FetchType.LAZY)
	private List<Category> categories = new ArrayList<>(); // Một Style có thể có nhiều Category
}
