package com.greentower.seedApi.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    public BaseEntity() {
        this.createdAt = new Date();
    }

    public BaseEntity(UUID id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
