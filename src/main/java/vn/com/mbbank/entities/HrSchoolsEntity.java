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
import java.util.Date;
import javax.persistence.Temporal;


/**
 * Lop entity ung voi bang HR_SCHOOLS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_SCHOOLS")
public class HrSchoolsEntity {

    @Id
    @GeneratedValue(generator = "HR_SCHOOLS_SEQ")
    @SequenceGenerator(name = "HR_SCHOOLS_SEQ", sequenceName = "HR_SCHOOLS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCHOOL_ID")
    private Long schoolId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;


}
