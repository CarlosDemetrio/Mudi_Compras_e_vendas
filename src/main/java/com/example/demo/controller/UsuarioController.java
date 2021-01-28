package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.StatusPedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("pedido")
    public String home(Model model, Principal principal){

        //pega os pedidos do usuário
        List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName());
        model.addAttribute("pedidos", pedidos);

        return "usuario/Home";
    }
    @GetMapping("pedido/{status}")
    public String porstatus(@PathVariable("status") String status, Model model, Principal principal){

        //recebe valor do link e transforma eles em letras maiusculas
        List<Pedido> pedidos = pedidoRepository.findByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)), principal.getName());

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);

        return "usuario/Home";
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/usuario/Home";
    }
}
