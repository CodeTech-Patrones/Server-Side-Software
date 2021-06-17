package com.appnutrix.paymentMethod.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    @Query("Select b from PaymentMethod b where b.patient.id = :client_id")
    public List<PaymentMethod> findAllByClient(@Param("client_id") Integer client_id);
}