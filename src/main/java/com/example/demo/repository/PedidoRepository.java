package com.example.demo.repository;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //Cacheable faz as consultas do banco de dados diminuírem por conta do cache, as consultas ficam
    // salvas no navegador
    @Cacheable("pedidosbyStatus")
    List<Pedido> findByStatus(StatusPedido status, Pageable sort);

    @Cacheable("pedidosbyUser")
    @Query("select p from pedido join p.user u  where u.username = :username ")
    List<Pedido> findAllByUser(@Param("username")String username);

    @Cacheable("pedidosbyStatusandUser")
    @Query("select p from pedido join p.user u  where u.username = :username and p.status = :status ")
    List<Pedido> findByStatusEUsuario(@Param("status")StatusPedido status,@Param("username")String username);
}
