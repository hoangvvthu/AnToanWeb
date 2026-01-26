package ute.shop.services.implement;

import ute.shop.dao.ICartDao;
import ute.shop.dao.IProductDao;
import ute.shop.dao.implement.CartDaoImpl;
import ute.shop.dao.implement.ProductDaoImpl;
import ute.shop.entity.Cart;
import ute.shop.entity.CartItem;
import ute.shop.entity.Product;
import ute.shop.entity.User;
import ute.shop.services.ICartService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartServiceImpl implements ICartService {

	private final ICartDao cartDao = new CartDaoImpl();
	private final IProductDao productDao = new ProductDaoImpl();

	@Override
	public Cart findCartByUserId(int userId) {
		return cartDao.findByUserId(userId);
	}

	@Override
	public Cart getCartByUser(User user) {
		Cart cart = cartDao.getCartByUser(user);
		if (cart == null) {
			throw new IllegalArgumentException("Không tìm thấy giỏ hàng cho người dùng này.");
		}
		return cart;
	}

	@Override
	public Cart createCart(User user) {
		if (user == null) {
			throw new IllegalArgumentException("Người dùng không hợp lệ.");
		}
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setCreatedAt(new Date());
		cart.setUpdatedAt(new Date());
		cart.setCartItems(List.of()); // Giỏ hàng trống
		return cartDao.save(cart);
	}

	@Override
	public Cart addOrUpdateCartItem(int userId, int productId, int quantity) {
		// Lấy giỏ hàng của người dùng
		Cart cart = cartDao.findByUserId(userId);

		if (cart == null) {
			// Tạo mới nếu giỏ hàng chưa tồn tại
			cart = new Cart();
			cart.setUser(new User(userId));
			cart.setCartItems(new ArrayList<>());
			cartDao.save(cart);
		}

		// Kiểm tra sản phẩm đã tồn tại trong giỏ chưa
		CartItem existingItem = cart.getCartItems().stream().filter(item -> item.getProduct().get_id() == productId)
				.findFirst().orElse(null);

		if (existingItem != null) {
			// Cập nhật số lượng
			existingItem.setCount(existingItem.getCount() + quantity);
		} else {
			// Tạo mới CartItem
			CartItem newItem = new CartItem();
			newItem.setCart(cart);
			newItem.setProduct(new Product(productId));
			newItem.setCount(quantity);
			cart.getCartItems().add(newItem);
		}

		// Lưu giỏ hàng
		cartDao.update(cart);
		return cart;
	}

	@Override
	public void removeCartItem(int userId, int productId) {
		// Tìm giỏ hàng theo userId
		Cart cart = cartDao.findByUserId(userId);
		if (cart == null) {
			throw new IllegalArgumentException("Giỏ hàng không tồn tại cho user ID: " + userId);
		}

		// Kiểm tra nếu giỏ hàng có item nào
		if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
			throw new IllegalArgumentException("Giỏ hàng trống, không có sản phẩm để xóa.");
		}

		System.out.println("Đang xóa sản phẩm với ID: " + productId);
		System.out.println("Số lượng sản phẩm trong giỏ hàng trước khi xóa: " + cart.getCartItems().size());

		// Tìm sản phẩm cần xóa
		CartItem itemToRemove = cart.getCartItems().stream().filter(item -> item.getProduct().get_id() == productId)
				.findFirst().orElse(null);

		if (itemToRemove == null) {
			throw new IllegalArgumentException("Sản phẩm không tồn tại trong giỏ hàng.");
		}

		// Xóa sản phẩm khỏi danh sách CartItems
		cart.getCartItems().remove(itemToRemove);
		cart.setUpdatedAt(new Date());

		// Cập nhật lại giỏ hàng vào cơ sở dữ liệu
		Cart updatedCart = cartDao.update(cart);
		if (updatedCart == null) {
			throw new IllegalStateException("Không thể cập nhật giỏ hàng sau khi xóa sản phẩm.");
		}

		System.out.println(
				"Xóa sản phẩm thành công. Số lượng sản phẩm sau khi xóa: " + updatedCart.getCartItems().size());
	}

	@Override
	public void clearCart(int userId) {
		Cart cart = cartDao.findByUserId(userId);
		if (cart == null) {
			throw new IllegalArgumentException("Giỏ hàng không tồn tại cho user ID: " + userId);
		}

		if (cart.getCartItems().isEmpty()) {
			System.out.println("Giỏ hàng đã trống.");
			return; // Không cần xử lý thêm nếu giỏ hàng đã trống
		}

		System.out.println("Xóa toàn bộ giỏ hàng của user ID: " + userId);

		// Xóa tất cả sản phẩm trong giỏ
		cart.getCartItems().clear();
		cart.setUpdatedAt(new Date());

		// Lưu lại giỏ hàng trống vào cơ sở dữ liệu
		Cart updatedCart = cartDao.update(cart);
		if (updatedCart == null) {
			throw new IllegalStateException("Không thể xóa toàn bộ giỏ hàng.");
		}

		System.out.println("Xóa toàn bộ giỏ hàng thành công.");
	}

}
