package com.gsm.charlie.sale.domain.repository;

import com.gsm.charlie.sale.domain.model.BusinessEntity;
import com.gsm.charlie.sale.domain.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
