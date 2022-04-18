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
 * Lop entity ung voi bang USER_BOOKMARKS
 * @author author
 * @since 1.0
 * @version 1.0
 */

@Data
@Entity
@Table(name = "HR_USER_BOOKMARKS")
public class HrUserBookmarksEntity {

    @Id
    @GeneratedValue(generator = "USER_BOOKMARKS_SEQ")
    @SequenceGenerator(name = "USER_BOOKMARKS_SEQ", sequenceName = "USER_BOOKMARKS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_BOOKMARK_ID")
    private Long userBookmarkId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OPTIONS")
    private String options;

    @Column(name = "FLAG_STATUS")
    private Integer flagStatus;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastUpdateDate;


}
