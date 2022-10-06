package com.gsm.charlie.catalog.resource;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class UnitOfMeasurementResource extends AuditModel {
    private Long id;
    private String name;
}
