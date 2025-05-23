package com.vi.base.commonUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "LJM_QUERY_MASTER")
public class QUERY_MASTER {
    
    @Id
    @SequenceGenerator(name = "QueryMasterSeq", sequenceName = "QM_SYS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QueryMasterSeq")
    @Column(name = "QM_SYS_ID")
    @JsonProperty("QM_SYS_ID")
    private int QM_SYS_ID;
    
    @Column(name = "QM_PROG_CODE")
    @JsonProperty("QM_PROG_CODE")
    private String QM_PROG_CODE;
    
    @Column(name = "QM_SCREEN_NAME")
    @JsonProperty("QM_SCREEN_NAME")
    private String QM_SCREEN_NAME;
    
    @Column(name = "QM_QUERY_TYPE")
    @JsonProperty("QM_QUERY_TYPE")
    private String QM_QUERY_TYPE;
    
    @Column(name = "QM_QUERY_NAME")
    @JsonProperty("QM_QUERY_NAME")
    private String QM_QUERY_NAME;
    
    @Column(name = "QM_QUERY_DESC")
    @JsonProperty("QM_QUERY_DESC")
    private String QM_QUERY_DESC;
    
    @Column(name = "QM_QUERY")
    @JsonProperty("QM_QUERY")
    private String QM_QUERY;
    
    @Column(name = "QM_INS_DT")
    private Timestamp QM_INS_DT;
    
    @Column(name = "QM_INS_ID")
    private int QM_INS_ID;
    
    @Column(name = "QM_UPD_DT")
    private Timestamp QM_UPD_DT;

    // Getters and Setters
    public int getQM_SYS_ID() {
        return QM_SYS_ID;
    }

    public void setQM_SYS_ID(int QM_SYS_ID) {
        this.QM_SYS_ID = QM_SYS_ID;
    }

    public String getQM_PROG_CODE() {
        return QM_PROG_CODE;
    }

    public void setQM_PROG_CODE(String QM_PROG_CODE) {
        this.QM_PROG_CODE = QM_PROG_CODE;
    }

    public String getQM_SCREEN_NAME() {
        return QM_SCREEN_NAME;
    }

    public void setQM_SCREEN_NAME(String QM_SCREEN_NAME) {
        this.QM_SCREEN_NAME = QM_SCREEN_NAME;
    }

    public String getQM_QUERY_TYPE() {
        return QM_QUERY_TYPE;
    }

    public void setQM_QUERY_TYPE(String QM_QUERY_TYPE) {
        this.QM_QUERY_TYPE = QM_QUERY_TYPE;
    }

    public String getQM_QUERY_NAME() {
        return QM_QUERY_NAME;
    }

    public void setQM_QUERY_NAME(String QM_QUERY_NAME) {
        this.QM_QUERY_NAME = QM_QUERY_NAME;
    }

    public String getQM_QUERY_DESC() {
        return QM_QUERY_DESC;
    }

    public void setQM_QUERY_DESC(String QM_QUERY_DESC) {
        this.QM_QUERY_DESC = QM_QUERY_DESC;
    }

    public String getQM_QUERY() {
        return QM_QUERY;
    }

    public void setQM_QUERY(String QM_QUERY) {
        this.QM_QUERY = QM_QUERY;
    }

    public Timestamp getQM_INS_DT() {
        return QM_INS_DT;
    }

    public void setQM_INS_DT(Timestamp QM_INS_DT) {
        this.QM_INS_DT = QM_INS_DT;
    }

    public int getQM_INS_ID() {
        return QM_INS_ID;
    }

    public void setQM_INS_ID(int QM_INS_ID) {
        this.QM_INS_ID = QM_INS_ID;
    }

    public Timestamp getQM_UPD_DT() {
        return QM_UPD_DT;
    }

    public void setQM_UPD_DT(Timestamp QM_UPD_DT) {
        this.QM_UPD_DT = QM_UPD_DT;
    }
}

