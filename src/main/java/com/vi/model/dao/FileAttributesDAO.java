package com.vi.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "LJM_FILE_ATTRIBUTES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileAttributesDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LJM_SYS_ID")
    private Long sysId;

    @Column(name = "LJM_FILE_NAME", nullable = false, length = 255)
    private String fileName;

    @Column(name = "LJM_AUTHOR", length = 255)
    private String author;

    @Column(name = "LJM_DOCTYPE", length = 500)
    private String docType;

    @Column(name = "LJM_TRANID", length = 50)
    private String tranId;

    @Column(name = "LJM_DOC_MODULE", length = 100)
    private String docModule;

    @Column(name = "LJM_CREATE_DT", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "LJM_MOD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modDate;

    @Column(name = "LJMA_ADDL1", length = 255)
    private String additional1;

    @Column(name = "LJM_ADDL2", length = 255)
    private String additional2;

    @Column(name = "LJM_ADDL3", length = 255)
    private String additional3;

    @Column(name = "LJM_ADDL4", length = 255)
    private String additional4;

    @Column(name = "LJM_ADDL5", length = 255)
    private String additional5;

    @Column(name = "LJM_STATUS", length = 50)
    private String status;

    @Lob
    @Column(name = "LJM_DEEPLINK_URL")
    private String deepLinkUrl;

    @Column(name = "LJM_FILE_PATH", length = 255)
    private String filePath;

    @PrePersist
    protected void onCreate() {
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.modDate = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.modDate = new Timestamp(System.currentTimeMillis());
    }
}
