package com.gsm.charlie.security.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.security.domain.model.Role;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Accessors(chain = true)
@Getter @Setter
public class UserResource extends AuditModel {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String username;
    private String password;
    private boolean isActive;
    private List<Role> roles;
}
