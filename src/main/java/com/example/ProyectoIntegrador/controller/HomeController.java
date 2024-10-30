package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.model.*;
import com.example.ProyectoIntegrador.repository.UsuarioRepository;
import com.example.ProyectoIntegrador.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductoServiceImpl service;
    @Autowired
    private ClienteServiceImpl servicecliente;
    @Autowired
    private UsuarioRepository serviceusuario;
    @Autowired
    private FacturaServiceImpl facturaService;
    @Autowired
    private DetalleServiceImpl detalleService;
    @Autowired
    private OrdenServiceImpl ordenService;


    private List<Orden> cart = new ArrayList<>();
    private Factura factura = new Factura();


    @GetMapping("")
    public String inicio(){
        return "home.html";
    }

    @GetMapping("/cerrarsession")
    public String cerrarSession(HttpSession session){
        session.removeAttribute("session");
        return "redirect:/home";
    }
    @PostMapping("/saveUsuario")
    public String saveUsuario(HttpServletRequest request){

        Cliente cliente = new Cliente();
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellido(request.getParameter("apellido"));
        cliente.setDireccion(request.getParameter("direccion"));
        cliente.setTelefono(Integer.parseInt(request.getParameter("telefono")));
        cliente.setEdad(Integer.parseInt(request.getParameter("edad")));
        servicecliente.saveCliente(cliente);
        int id = servicecliente.BuscarClientePorDatos(cliente.getNombre(), cliente.getApellido(), cliente.getDireccion(), cliente.getTelefono(), cliente.getEdad());


        String email= request.getParameter("email");
        String password = request.getParameter("pass");
        String rol= "User";
        serviceusuario.RegistrarUsuario(email, password, id, rol);

        return "redirect:/home";
    }
    @PostMapping("/loginUser")
    public String loginUser(@RequestParam("email")String email, @RequestParam("password")String password, HttpSession session){
        Usuario usuario = serviceusuario.VerificarAcceso(email, password);
        if (usuario != null){
            session.setAttribute("session", usuario); //Subida de los datos del usuario a una SESSION
            return "redirect:/home/verProductos";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/verProductos")
    public String verProductos(Model model, HttpSession session){
        if(session.getAttribute("session")==null){ //Si no hay session
            Cliente cliente = new Cliente();
            cliente.setId(0);
            cliente.setNombre("Visitante");
            cliente.setDireccion("Visitante");
            Usuario usuario = new Usuario();
            usuario.setId(0);
            usuario.setEmail("@hotmail.com");
            usuario.setCliente(cliente);
            session.setAttribute("session", usuario);
            model.addAttribute("listaProductos", service.ListarProductoActivo());
            model.addAttribute("datos", session.getAttribute("session"));
            return "verProductos.html";
        }else{
            model.addAttribute("listaProductos", service.ListarProductoActivo());
            model.addAttribute("datos", session.getAttribute("session"));
            return "verProductos.html";
        }
    }
    @GetMapping("/verDetalle/{id}")
    public String verDetalle(Model model, @PathVariable int id, HttpSession session){
        Producto producto = service.ObtenerProducto(id).get();
        model.addAttribute("producto", producto);
        model.addAttribute("datos", session.getAttribute("session"));
        return "verDetalle.html";
    }
    @GetMapping("/verRopaMujer")
    public String verRopaMujer(Model model, HttpSession session){
        String genero = "Mujer";
        model.addAttribute("listaProductos", service.ListarSegunGenero(genero));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
    @GetMapping("/verRopaDeportiva")
    public String verRopaDeportiva(Model model, HttpSession session){
        String cat="Deporte";
        model.addAttribute("listaProductos", service.ListarSegunCategoria(cat));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
    @GetMapping("/verRopaHombre")
    public String verRopaHombre(Model model, HttpSession session){
        String genero="Hombre";
        model.addAttribute("listaProductos", service.ListarSegunGenero(genero));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
    @GetMapping("/verRopaInfantil")
    public String verRopaInfantil(Model model, HttpSession session){
        String cat="Niños";
        model.addAttribute("listaProductos", service.ListarSegunCategoria(cat));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
    @GetMapping("/verRopaFormal")
    public String verRopaFormal(Model model, HttpSession session){
        String cat="Formal";
        model.addAttribute("listaProductos", service.ListarSegunCategoria(cat));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
    @GetMapping("/verRopaVerano")
    public String verRopaVerano(Model model, HttpSession session){
        String cat="Verano";
        model.addAttribute("listaProductos", service.ListarSegunCategoria(cat));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
    @GetMapping("/verCart")
    public String verCarro(Model model, HttpSession session){
            model.addAttribute("cart", cart);
            model.addAttribute("factura", factura);
            model.addAttribute("datos", session.getAttribute("session"));
            return "verCarro.html";
    }
    @PostMapping("/addCart")
    public String addCart(@RequestParam("id") int id, @RequestParam("cantidad") int cantidad, Model model){
            Producto producto = service.ObtenerProducto(id).get();
            Orden orden = new Orden();
            orden.setId(producto.getId());
            orden.setNombre(producto.getRopa().getNombre());
            orden.setFoto(producto.getRopa().getFoto());
            orden.setCantidad(cantidad);
            orden.setTotal(producto.getRopa().getPrecio()*cantidad);
            cart.add(orden);

            double sumatotal=0;
            sumatotal=cart.stream().mapToDouble(total-> total.getTotal()).sum();
            factura.setTotal(sumatotal);

            model.addAttribute("cart", cart);
            model.addAttribute("factura", factura);

            return "redirect:/home/verCart";
    }
    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id, Model model){
       List<Orden> ListadeCarroSinObjetoEliminado = new ArrayList<>();
        for(Orden orden : cart){
            if (orden.getId()!=id){
                ListadeCarroSinObjetoEliminado.add(orden);
            }
        }
        cart=ListadeCarroSinObjetoEliminado;
        double sumatotal=0;
        sumatotal=cart.stream().mapToDouble(total-> total.getTotal()).sum();
        factura.setTotal(sumatotal);

        model.addAttribute("cart", cart);
        model.addAttribute("factura", factura);
        return "redirect:/home/verCart";
    }

    @PostMapping("/comprar")
    public String comprar(HttpSession session, @RequestParam("opc") int Modo, Model model) {
            if (Modo == 1) { //Si es Delivery
                model.addAttribute("datos", session.getAttribute("session"));
                model.addAttribute("factura", factura);

                Date fechaActual = new Date(); //Fecha y hora actual del sistema
                Factura factura1 = new Factura(); //Crear factura vacia a insertar
                Usuario usuario1 = (Usuario) session.getAttribute("session"); //Recuperar datos del usuario logeado

                //Crear e insertar factura
                factura1.setFecha(fechaActual);
                factura1.setTotal(factura.getTotal()); //comparte el valor de la sumatotal del carro global
                factura1.setCliente(usuario1.getCliente());
                factura1.setEstado("pendiente");
                facturaService.saveFactura(factura1);

                for (Orden x : cart) {
                    Detalle detalle = new Detalle();
                    detalle.setNombre(x.getNombre());
                    detalle.setPrecio(x.getTotal() / x.getCantidad());
                    detalle.setCantidad(x.getCantidad());
                    detalle.setTotal(x.getTotal());
                    detalle.setFactura(factura1);
                    detalle.setIdproducto(x.getId());
                    detalleService.saveDetalle(detalle);

                    Orden orden = new Orden();
                    orden.setNombre(x.getNombre());
                    orden.setFoto(x.getFoto());
                    orden.setCantidad(x.getCantidad());
                    orden.setTotal(x.getTotal());
                    orden.setCliente(usuario1.getCliente());
                    orden.setFactura(factura1);
                    ordenService.saveOrden(orden);

                }
                //limpiar lista
                factura = new Factura();
                cart.clear(); //limpiamos el carro

                return "PasarelaPago.html";

            } else { //Si es presencial

                //1 Delivery, 2 Presencial
                Date fechaActual = new Date(); //Fecha y hora actual del sistema
                Factura factura1 = new Factura(); //Crear factura vacia a insertar
                Usuario usuario1 = (Usuario) session.getAttribute("session"); //Recuperar datos del usuario logeado

                //Crear e insertar factura
                factura1.setFecha(fechaActual);
                factura1.setTotal(factura.getTotal()); //comparte el valor de la sumatotal del carro global
                factura1.setCliente(usuario1.getCliente());
                factura1.setEstado("pendiente");
                facturaService.saveFactura(factura1);

                //Guardar los Detalles de la factura

                for (Orden x : cart) {
                    Detalle detalle = new Detalle();
                    detalle.setNombre(x.getNombre());
                    detalle.setPrecio(x.getTotal() / x.getCantidad());
                    detalle.setCantidad(x.getCantidad());
                    detalle.setTotal(x.getTotal());
                    detalle.setFactura(factura1);
                    detalle.setIdproducto(x.getId());
                    detalleService.saveDetalle(detalle);


                    Orden orden = new Orden();
                    orden.setNombre(x.getNombre());
                    orden.setFoto(x.getFoto());
                    orden.setCantidad(x.getCantidad());
                    orden.setTotal(x.getTotal());
                    orden.setCliente(usuario1.getCliente());
                    orden.setFactura(factura1);
                    ordenService.saveOrden(orden);

                }
                //limpiar lista
                factura = new Factura();
                cart.clear(); //limpiamos el carro

                return "redirect:/home/verProductos";
            }
    }

    @GetMapping("/verFacturas")
    public String verFacturas(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("session");
            model.addAttribute("listaFacturas", facturaService.buscarFacturaSegunCliente(usuario.getCliente().getId()));
            model.addAttribute("datos", session.getAttribute("session"));
            return "verFacturas.html";
    }

    @GetMapping("/verDetalles/{id}")
    public String verDetalles (@PathVariable int id, Model model, HttpSession session){
        Usuario usuario = (Usuario)session.getAttribute("session");
        model.addAttribute("listaDetalle", detalleService.BuscarSegunFactura(id));
        model.addAttribute("datos", session.getAttribute("session"));
        model.addAttribute("id", id);
        return "verDetalleFactura.html";
    }
    @PostMapping("/Busqueda")
    public String Busqueda(@RequestParam("text") String text, Model model, HttpSession session){
        model.addAttribute("listaProductos", service.BuscarPorNombre(text, "active"));
        model.addAttribute("datos", session.getAttribute("session"));
        return "verProductos.html";
    }
}
