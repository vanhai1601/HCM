/*
 * Copyright (C) 2022 EcoIT. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotNull;


/**
 * Lop entity ung voi bang HR_CONTRACT_TYPES
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_CONTRACT_TYPES")
public class HrContractTypesEntity {

    @Id
    @GeneratedValue(generator = "HR_CONTRACT_TYPES_SEQ")
    @SequenceGenerator(name = "HR_CONTRACT_TYPES_SEQ", sequenceName = "HR_CONTRACT_TYPES_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTRACT_TYPE_ID")
    private Long contractTypeId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;


}
