package com.gsm.charlie.catalog.resource;

import com.gsm.charlie.catalog.domain.model.Nickname;
import com.gsm.charlie.catalog.domain.model.UnitOfMeasurement;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Accessors(chain = true)
@Getter @Setter
public class ProductResource extends AuditModel {
    private Long id;
    private String code;
    private String name;
    private BigDecimal price;
    private String pathImage;
    private UnitOfMeasurementResource unitOfMeasurement;
    private List<Nickname> nicknames;
}
