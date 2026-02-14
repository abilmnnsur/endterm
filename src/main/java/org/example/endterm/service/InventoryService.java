package org.example.endterm.service;

import org.example.endterm.cache.InMemoryCache;
import org.example.endterm.exception.InvalidInputException;
import org.example.endterm.model.InventoryItem;
import org.example.endterm.model.InventoryValue;
import org.example.endterm.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repo;
    private final InMemoryCache cache = InMemoryCache.getInstance();

    private static final String TOP_INVENTORIES_CACHE_KEY = "top_inventories";

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    public double getPlayerInventoryValue(int playerId) {
        if (playerId <= 0) {
            throw new InvalidInputException("Player ID must be positive");
        }
        return repo.getInventoryValueByPlayerId(playerId);
    }

    public List<InventoryValue> getTopInventories() {
        if (cache.contains(TOP_INVENTORIES_CACHE_KEY)) {
            return (List<InventoryValue>) cache.get(TOP_INVENTORIES_CACHE_KEY);
        }

        List<InventoryValue> topInventories = repo.getTopInventories();
        cache.put(TOP_INVENTORIES_CACHE_KEY, topInventories);
        return topInventories;
    }

    public List<InventoryItem> getPlayerInventory(int playerId) {
        if (playerId <= 0) {
            throw new InvalidInputException("Player ID must be positive");
        }
        return repo.getInventoryByPlayerId(playerId);
    }
}
