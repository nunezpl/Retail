package com.ecommerce.backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompraId implements Serializable {
    private UUID compraId;
    private UUID productoId;
}
