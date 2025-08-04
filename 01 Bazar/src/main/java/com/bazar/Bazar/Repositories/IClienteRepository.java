package com.bazar.Bazar.Repositories;

import com.bazar.Bazar.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente,Long> {
}
