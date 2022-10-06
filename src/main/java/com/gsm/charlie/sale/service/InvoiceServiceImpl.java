package com.gsm.charlie.sale.service;

import com.gsm.charlie.inventory.domain.model.Movement;
import com.gsm.charlie.sale.domain.repository.InvoiceDetailRepository;
import com.gsm.charlie.inventory.domain.repository.MovementRepository;
import com.gsm.charlie.sale.domain.model.EStatus;
import com.gsm.charlie.sale.domain.model.Invoice;
import com.gsm.charlie.sale.domain.service.InvoiceService;
import com.gsm.charlie.sale.domain.repository.InvoiceRepository;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService  {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Transactional
    @Override
    public Invoice createInvoice(Invoice invoice) {

        if (invoice.getTypeVoucher() == null) {
            var all = invoiceRepository.findAll();
            var quotations = all.stream().filter(x -> x.getNumberQuotation() != null).toList();
            if (quotations.size()>0){
                var lastIndex = quotations.stream().max(Comparator.comparing(Invoice::getNumberQuotation)).get();
                invoice.setNumberQuotation(lastIndex.getNumberQuotation() + 1);
            }else invoice.setNumberQuotation(1L);
        }

        invoice.setTotal(invoice.getTotalInvoiceDetails());
        return invoiceRepository.save(invoice);
    }

    @Transactional
    @Override
    public Invoice updateInvoice(Long invoiceId, Invoice invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        invoice.getInvoiceDetails().clear();
        invoice.getInvoiceDetails().addAll(invoiceRequest.getInvoiceDetails());



        invoice.setTotal(invoice.getTotalInvoiceDetails());
        return invoiceRepository.save(
                invoice.setDateVoucher(invoiceRequest.getDateVoucher())
                        .setBusinessEntity(invoiceRequest.getBusinessEntity())
                        .setContact(invoiceRequest.getContact())
);
    }

    @Transactional
    @Override
    public Invoice patchInvoice(Long invoiceId, Invoice invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setTypeVoucher(invoiceRequest.getTypeVoucher());
        invoice.setNumberInvoice(invoiceRequest.getNumberInvoice());
        invoice.setNumberBill(invoiceRequest.getNumberBill());
        invoice.setNumberProforma(invoiceRequest.getNumberProforma());
        invoice.setNumberPurchaseOrder(invoiceRequest.getNumberPurchaseOrder());
        invoice.setMovement(invoiceRequest.getMovement());

        return invoiceRepository.save(invoice);
    }

        @Override
        public ResponseEntity<?> deleteInvoice(Long invoiceId) {
            Invoice invoice = invoiceRepository.findById(invoiceId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Invoice", "Id", invoiceId));

            invoiceRepository.delete(invoice);
            return ResponseEntity.ok().build();
        }

}
