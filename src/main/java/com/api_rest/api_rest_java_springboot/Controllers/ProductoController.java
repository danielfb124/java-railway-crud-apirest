package com.api_rest.api_rest_java_springboot.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api_rest.api_rest_java_springboot.Entities.Producto;
import com.api_rest.api_rest_java_springboot.Repositories.ProductoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/productos")

public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping
    public List<Producto>obtenerProductos(){
        return productoRepository.findAll();
    }
    @GetMapping ("/{id}")
    public Producto obtenerProductoId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("no se encontro el producto con el id: "+id));
    }


    @PostMapping
    public Producto CrearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
   
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id,@RequestBody Producto detallesProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("no se encontro el producto con el id: "+id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());
        return productoRepository.save(producto);
    }
    
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("no se encontro el producto con el id: "+id));
        productoRepository.delete(producto);
        return "el producto con el id: "+id+" fue eliminado";
    }

}
