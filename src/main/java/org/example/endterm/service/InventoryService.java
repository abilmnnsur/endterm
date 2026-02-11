package org.example.endterm.service;

import org.example.endterm.exception.InvalidInputException;
import org.example.endterm.model.InventoryItem;
import org.example.endterm.model.InventoryValue;
import org.example.endterm.repository.InventoryRepository;

import java.util.List;

public class InventoryService {
    private final InventoryRepository repo = new InventoryRepository();

    public double getPlayerInventoryValue(int playerId) {
        return repo.getInventoryValueByPlayerId(playerId);
    }

    public List<InventoryValue> getTopInventories() {
        return repo.getTopInventories();
    }
    public List<InventoryItem> getPlayerInventory(int playerId) {
        if (playerId <= 0) {
            throw new InvalidInputException("Player ID must be positive");
        }
        return repo.getInventoryByPlayerId(playerId);
    }
}