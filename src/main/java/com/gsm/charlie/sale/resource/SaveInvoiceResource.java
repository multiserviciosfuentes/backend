package com.gsm.charlie.sale.resource;

import com.gsm.charlie.inventory.resource.MovementResource;
import com.gsm.charlie.sale.domain.model.*;
import com.gsm.charlie.security.resource.UserResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class SaveInvoiceResource {

    @NotNull
    private Date dateVoucher;
    private String numberInvoice;
    private String numberBill;
    private String numberProforma;
    private String numberQuotation;
    private String numberPurchaseOrder;
    @NotNull
    private EType type;
    private ETypeVoucher typeVoucher;
    @NotNull
    private EStatus status;

    private BusinessEntityResource businessEntity;
    private ContactResource contact;
    private MovementResource movement;
    private UserResource user;
    private List<InvoiceDetailResource> invoiceDetails;
}

