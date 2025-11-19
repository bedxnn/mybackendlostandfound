package com.find.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class lostItemsService {

    private final LostItemsRepository lostItemsRepository;

    @Autowired
    public lostItemsService(LostItemsRepository lostItemsRepository) {
        this.lostItemsRepository = lostItemsRepository;
    }

    // Get all items
    public List<lost_items> getItems() {
        return lostItemsRepository.findAll();
    }

    // FIXED: Claim item with ownership protection
    public ResponseEntity<?> claimItem(Long id, String userId) {

        Optional<lost_items> optionalItem = lostItemsRepository.findById(id);

        if (!optionalItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item not found.");
        }

        lost_items item = optionalItem.get();

        // items created before login system existed
        if (item.getUserId() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("This item has no owner (old item). You cannot claim it.");
        }

        // REAL SECURITY CHECK
        if (!item.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You cannot claim someone else's item.");
        }

        // Mark claimed then delete it
        item.setClaimed(true);
        lostItemsRepository.deleteById(id);

        return ResponseEntity.ok("Item claimed successfully.");
    }

    // Search filtering
    public List<lost_items> getItemByName(String searchText, String student_id) {

        if ((searchText == null || searchText.isEmpty()) &&
                (student_id == null || student_id.isEmpty())) {
            return lostItemsRepository.findAll();
        }

        return lostItemsRepository.findAll().stream()
                .filter(item -> {
                    boolean matchName = true;
                    boolean matchStudent = true;

                    if (searchText != null && !searchText.isEmpty()) {
                        matchName = item.getName() != null &&
                                item.getName().toLowerCase().contains(searchText.toLowerCase());
                    }

                    if (student_id != null && !student_id.isEmpty()) {
                        String sid = String.valueOf(item.getStudent_id());
                        matchStudent = sid.contains(student_id);
                    }

                    return matchName || matchStudent;
                })
                .collect(Collectors.toList());
    }

    // Add item
    public lost_items addLostItem(lost_items item) {
        return lostItemsRepository.save(item);
    }

    // Delete item
    @Transactional
    public void deleteItem(long id) {
        if (lostItemsRepository.existsById(id)) {
            lostItemsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Item with ID " + id + " not found");
        }
    }
}
