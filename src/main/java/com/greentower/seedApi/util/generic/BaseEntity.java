package com.greentower.seedApi.util.generic;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @Type(type="pg-uuid")
    private UUID id;

    @CreatedDate
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    private Date updatedAt;

    public BaseEntity() {

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