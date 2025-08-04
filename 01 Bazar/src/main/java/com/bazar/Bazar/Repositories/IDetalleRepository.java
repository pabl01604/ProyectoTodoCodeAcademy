package com.bazar.Bazar.Repositories;

import com.bazar.Bazar.Models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleRepository extends JpaRepository<DetalleVenta,Long> {
}
