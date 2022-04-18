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
 * Lop entity ung voi bang CAT_BANKS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "CAT_BANKS")
public class CatBanksEntity {

    @Id
    @GeneratedValue(generator = "CAT_BANKS_SEQ")
    @SequenceGenerator(name = "CAT_BANKS_SEQ", sequenceName = "CAT_BANKS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "BANK_ID")
    private Long bankId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;


}
