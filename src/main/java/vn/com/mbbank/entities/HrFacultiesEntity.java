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
 * Lop entity ung voi bang HR_FACULTIES
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_FACULTIES")
public class HrFacultiesEntity {

    @Id
    @GeneratedValue(generator = "HR_FACULTIES_SEQ")
    @SequenceGenerator(name = "HR_FACULTIES_SEQ", sequenceName = "HR_FACULTIES_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACULTY_ID")
    private Long facultyId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;


}
