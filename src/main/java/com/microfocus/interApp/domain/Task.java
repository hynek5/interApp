package com.microfocus.interApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.annotation.Annotation;

@Entity
public class Task implements Entity {

    @Override
    public String name() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    public enum OperType {
        READ("Read"),
        WRITE("Write");

        private final String displayValue;

        private OperType(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }


    }

    public  enum Status {
        NEW,
        COMPLETED,
        PENDING,
        RUNNING,
    }

    @Id
    @GeneratedValue
    private Long id;
    private java.util.UUID uuid = java.util.UUID.randomUUID();
    private String gid;
    private OperType type;
    private String metadata;
    private Status status = Status.NEW;

    public Task(){
    }

    public Long getId() {
        return id;
    }

    public java.util.UUID getUuid() {
        return uuid;
    }

    public void setUuid(java.util.UUID UUID) {
        this.uuid = UUID;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String GID) {
        this.gid = GID;
    }

    public OperType getType() {
        return type;
    }

    public void setType(OperType type) {
        this.type = type;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
