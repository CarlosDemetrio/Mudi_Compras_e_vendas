package com.example.demo.controller;


import com.example.demo.dto.RequisicaoNovoPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.User;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido request){
    
        
        return "pedido/formulario";
    }
    @PostMapping("novo")
     public String novo(@Validated RequisicaoNovoPedido request, BindingResult result){
        if(result.hasErrors()){
            return "pedido/formulario";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Pedido pedido = request.toPedido();
        pedido.setUser(user);
        pedidoRepository.save(pedido);

        return "redirect:/home";

     }


}
