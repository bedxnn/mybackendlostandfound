package com.find.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/lostitems")
public class lostItemsController {
    private final lostItemsService lostItemsService;
    @Autowired
    public lostItemsController(lostItemsService lostItemsService){
        this.lostItemsService = lostItemsService;

    }
    @GetMapping("/search")
    public ResponseEntity<List<lost_items>> search(@RequestParam(required = false) String searchText) {
        try {
            List<lost_items> results = lostItemsService.getItemByName(searchText);
            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping()
    public List <lost_items>  getItems (){
        return lostItemsService.getItems();
    }
    @PostMapping
        public ResponseEntity<String> addItems(@RequestBody lost_items newItem) {
        try {
            lostItemsService.addlostItem(newItem);
            return ResponseEntity.ok(" sucesfully added");
        } catch (RuntimeException e) {
            return ResponseEntity.status(505).body(e.getMessage());
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            lostItemsService.lost_items(id);
            return ResponseEntity.ok("Deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/claim")
public lost_items updateFound(@PathVariable long id){
        return lostItemsService.claimed(id);
}
}
