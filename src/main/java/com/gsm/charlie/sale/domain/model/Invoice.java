package com.gsm.charlie.sale.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.inventory.domain.model.Movement;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
@Accessors(chain = true)
@Getter
@Setter
public class Invoice extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_voucher")
    @NotNull
    private Date dateVoucher;

    @Column(unique = true, name = "number_invoice")
    private Long numberInvoice;

    @Column(unique = true, name = "number_bill")
    private Long numberBill;

    @Column(unique = true, name = "number_proforma")
    private Long numberProforma;

    @Column(unique = true, name = "number_quotation")
    private Long numberQuotation;

    @Column(unique = true, name = "number_purchase_order")
    private Long numberPurchaseOrder;

    @NotNull
    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    @Min(0)
    @Max(100)
    private Float igv;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private EType type;

    @Enumerated(value = EnumType.STRING)
    private ETypeVoucher typeVoucher;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private EStatus status;

    @ManyToOne
    @JoinColumn(name = "business_entity_id")
    @NotNull
    @JsonIgnore
    private BusinessEntity businessEntity;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movement_id")
    @JsonIgnore
    private Movement movement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
    private User User;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    @NotNull
    @JsonIgnore
    private List<InvoiceDetail> invoiceDetails;

    @JsonIgnore
    public BigDecimal getTotalInvoiceDetails() {
        return invoiceDetails.stream().map(InvoiceDetail::rode).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
