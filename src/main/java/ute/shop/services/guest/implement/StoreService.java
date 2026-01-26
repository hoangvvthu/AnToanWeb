package ute.shop.services.guest.implement;

import java.util.List;

import ute.shop.dao.guest.implement.StoreDAO;
import ute.shop.entity.Store;


public class StoreService {
    private final StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public List<Store> searchStores(String keywords, int page, int pageSize) {
        return storeDAO.searchStores(keywords, page, pageSize);
    }

    public long countSearchResults(String keywords) {
        return storeDAO.countSearchResults(keywords);
    }

    public Store getStoreDetails(int id) {
        return storeDAO.findStoreById(id);
    }
}
