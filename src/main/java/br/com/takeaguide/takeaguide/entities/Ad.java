package br.com.takeaguide.takeaguide.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ads", schema = "take_a_guide")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Account user;

    @NotNull
    @Column(name = "ad_content", nullable = false)
    private byte[] adContent;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "verified", nullable = false)
    private Byte verified;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}