package com.bazar.Bazar.Repositories;

import com.bazar.Bazar.Models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta,Long> {
}
