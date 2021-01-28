package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String home(Model model, Principal principal){


        List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName());

        model.addAttribute("pedidos", pedidos);


        return "Home";
    }
    @GetMapping("/{status}")
    public String porstatus(@PathVariable("status") String status, Model model){

        //ordena por data de entrega os arquivos do banco de dados
        Sort sort = Sort.by("dataEntrega").ascending();


        //limita quantidade de itens que irão aparecer na página
        PageRequest paginacao = PageRequest.of(0,5, sort);

        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);

        return "Home";
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:home";
    }
}
