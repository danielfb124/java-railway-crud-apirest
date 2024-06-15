package com.api_rest.api_rest_java_springboot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_rest.api_rest_java_springboot.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long>{

}
