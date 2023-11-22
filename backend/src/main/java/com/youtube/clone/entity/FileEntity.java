package com.youtube.clone.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;//*Identificador único Universal*/

@Entity
@Table(name = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String type;
    @Lob //*LargeObject*/
    private byte[] data;
}
