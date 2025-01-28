package com.interview.purchase_api.repository;

import com.interview.purchase_api.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<Purchase, Integer> {
}
