package com.find.lostandfound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostItemsRepository extends JpaRepository <lost_items, Long> {

}

