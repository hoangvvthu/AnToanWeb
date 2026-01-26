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
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;

	@Column(nullable = false, unique = true, length = 32)
	private String name;

	@Column(unique = true, length = 128) // Giới hạn độ dài của slug
	private String slug;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id") // Đảm bảo tên cột trong cơ sở dữ liệu
	private Category parentCategory;

	@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Category> subCategories = new ArrayList<>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "category_style", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "style_id"))
	private List<Style> styles = new ArrayList<>();

	private String image;

	@Column(nullable = false)
	private Boolean isDeleted = false;

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

}
