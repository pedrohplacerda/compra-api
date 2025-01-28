package com.interview.purchase_api.repository;

import com.interview.purchase_api.entities.UserPurchase;
import com.interview.purchase_api.entities.UserPurchaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyUserRepository extends JpaRepository<UserPurchase, UserPurchaseId> {
}
