package com.gsm.charlie.sale.resource;

import com.gsm.charlie.inventory.domain.model.Movement;
import com.gsm.charlie.inventory.resource.MovementResource;
import com.gsm.charlie.sale.domain.model.*;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.resource.UserResource;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class InvoiceResource extends AuditModel {

    private Long id;
    private Date dateVoucher;
    private Long numberInvoice;
    private Long numberBill;
    private Long numberProforma;
    private Long numberQuotation;
    private Long numberPurchaseOrder;
    private BigDecimal total;
    private EType type;
    private ETypeVoucher typeVoucher;
    private EStatus status;

    private BusinessEntityResource businessEntity;
    private ContactResource contact;
    private UserResource user;
    private List<InvoiceDetailResource> invoiceDetails;
}
