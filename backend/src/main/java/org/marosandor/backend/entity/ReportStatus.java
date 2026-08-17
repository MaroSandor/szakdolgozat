package org.marosandor.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "report_statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String label;

    private Integer displayOrder;
}
