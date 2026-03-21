package dev.rohitrai.Finance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Transaction {
    @Id
    private UUID id;
    private double amount;
    private TransactionType type;
    private String description;
    private String tags;
    private long date;
    @CreationTimestamp
    private long createTime;
    @UpdateTimestamp
    private long updateTime;
}
