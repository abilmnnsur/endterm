package org.example.endterm.controller;

import org.example.endterm.model.InventoryItem;
import org.example.endterm.model.InventoryValue;
import org.example.endterm.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping("/{playerId}")
    public List<InventoryItem> getInventory(@PathVariable int playerId) {
        return inventoryService.getPlayerInventory(playerId);
    }


    @GetMapping("/{playerId}/value")
    public double getInventoryValue(@PathVariable int playerId) {
        return inventoryService.getPlayerInventoryValue(playerId);
    }


    @GetMapping("/top")
    public List<InventoryValue> getTopInventories() {
        return inventoryService.getTopInventories();
    }
}
