package com.bazar.Bazar.Repositories;

import com.bazar.Bazar.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Long> {
}
