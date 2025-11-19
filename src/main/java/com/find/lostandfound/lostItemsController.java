package com.find.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5179")
public class lostItemsController {

    private final lostItemsService lostItemsService;

    @Autowired
    public lostItemsController(lostItemsService lostItemsService) {
        this.lostItemsService = lostItemsService;
    }


    @PostMapping("/lostitems")
    public lost_items addLostItem(@RequestBody lost_items item) {
        System.out.println("Saving item with userId = " + item.getUserId());
        return lostItemsService.addLostItem(item);
    }


    @PutMapping("/lostitems/{id}/claim")
    public ResponseEntity<?> claimItem(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String userId = body.get("userId");
        return lostItemsService.claimItem(id, userId);
    }


    @GetMapping("/lostitems")
    public java.util.List<lost_items> getItems() {
        return lostItemsService.getItems();
    }
}
