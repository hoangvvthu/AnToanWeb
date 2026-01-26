package ute.shop.dao;

import ute.shop.entity.Cart;
import ute.shop.entity.CartItem;
import ute.shop.entity.User;

public interface ICartDao {
	Cart findByUserId(int userId);

	Cart getCartByUser(User user);

	Cart save(Cart cart);

	Cart update(Cart cart);

	Cart findById(int cartId);

	CartItem findCartItemByCartAndProduct(int cartId, int productId);

	CartItem addOrUpdateCartItem(CartItem cartItem);

	boolean removeCartItem(CartItem cartItem);
}
