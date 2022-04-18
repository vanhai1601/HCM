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
 * Lop entity ung voi bang HR_DOCUMENT_TYPES
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_DOCUMENT_TYPES")
public class HrDocumentTypesEntity {

    @Id
    @GeneratedValue(generator = "HR_DOCUMENT_TYPES_SEQ")
    @SequenceGenerator(name = "HR_DOCUMENT_TYPES_SEQ", sequenceName = "HR_DOCUMENT_TYPES_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOCUMENT_TYPE_ID")
    private Long documentTypeId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;


}
