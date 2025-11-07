package com.find.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;

@Component
public class lostItemsService {
    private final LostItemsRepository lostItemsRepository;

    @Autowired
    public lostItemsService(LostItemsRepository lostItemsRepository){
        this.lostItemsRepository = lostItemsRepository;
    }
    public List<lost_items> getItems(){
        return lostItemsRepository.findAll();
    }
    public List <lost_items> getItemByName(String searchText){
        try {
            if (searchText == null || searchText.isEmpty()) {
                return lostItemsRepository.findAll();
            }
            return lostItemsRepository.findAll().stream().filter(lost_items -> lost_items.getUserName().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("error don'twork" + e.getMessage());
        }
    }
    public lost_items addlostItem(lost_items addLost){
        try{
            lostItemsRepository.save(addLost);
            return addLost;
        } catch (Exception e) {
            throw new RuntimeException("error" + e.getMessage());
        }


    }

    @Transactional
    public void lost_items(long id) {
        if (lostItemsRepository.existsById(id)) {
            lostItemsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Item with ID " + id + " not found");
        }
    }


    public lost_items claimed(long id){
       lost_items items = lostItemsRepository.findById(id).orElseThrow();
       items.setClaimed(true);
   return lostItemsRepository.save(items);
    }
}
