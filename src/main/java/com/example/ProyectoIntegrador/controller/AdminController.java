package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.model.*;
import com.example.ProyectoIntegrador.repository.DetalleRepository;
import com.example.ProyectoIntegrador.service.*;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FacturaServiceImpl facturaService;
    @Autowired
    private DetalleServiceImpl detalleService;
    @Autowired
    private ProductoServiceImpl productoService;
    @Autowired
    private UsuarioSeviceImpl usuarioSevice;
    @Autowired
    private RopaServiceImpl ropaService;

    @GetMapping("")
    public String verDashboard(Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("total", facturaService.TotalGanancia());
            model.addAttribute("clientes", facturaService.TotalClientes());
            model.addAttribute("facturas", facturaService.TotalFacturas());
            model.addAttribute("stock", facturaService.TotalStock());
            return "Dashboard.html";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/login")
    public String loginAdmin(){
        return "loginAdmin.html";
    }

    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session){
        session.removeAttribute("adminLoggedIn");
        return "redirect:/home";
    }

    @PostMapping("/verificar")
    public String verificarAdmin(@RequestParam("email")String email, @RequestParam("pass")String pass, Model model, HttpSession session){
        String rol= "Admin";
        Usuario usuario = usuarioSevice.VerificarAccessoAdmin(email, pass, rol);
        if (usuario!=null){
            session.setAttribute("adminLoggedIn", true);
            model.addAttribute("admin", usuario.getCliente().getNombre());
            return "redirect:/admin";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/Grafico1")
    public String Grafico1(Model model){
        int year=2023;
        List<Tuple> lista = facturaService.GraficoUno(year); //Tupla del repository
        List<BigDecimal> point = new ArrayList<>(); //Separar por cantidad o total
        List<String> label = new ArrayList<>(); //Separar por mes o dia
        BigDecimal data;
        String labels;
        for(Tuple x: lista){
            data= (BigDecimal)x.get("total");
            point.add(data);
        }

        for(Tuple x: lista){
            labels = (String) x.get("mes");
            label.add(labels);
        }
        model.addAttribute("label",label);
        model.addAttribute("point",point);

        return "Grafico1.html";
    }
    @GetMapping("/Grafico2")
    public String Grafico2(Model model){
        List<Tuple> lista =productoService.Grafico2();
        List<BigDecimal> point = new ArrayList<>();
        List<String> label = new ArrayList<>();
        BigDecimal data;
        String labels;
        for(Tuple x: lista){
            data = (BigDecimal) x.get("total");
            point.add(data);
        }
        for(Tuple x: lista){
            labels= (String) x.get("categoria");
            label.add(labels);
        }
        model.addAttribute("label", label);
        model.addAttribute("point", point);
        return "Grafico2.html";
    }

    @GetMapping("/Grafico3")
    public String Grafico3(Model model){
        int year =2023;
        List<Tuple> lista = facturaService.Grafico3(year);
        List<String> label = new ArrayList<>();
        List<Long> point = new ArrayList<>();
        Long data;
        String labels;
        for(Tuple x: lista){
            labels= (String) x.get("mes");
            label.add(labels);
        }
        for(Tuple x: lista){
            data = (Long) x.get("total");
            point.add(data);
        }
        model.addAttribute("label", label);
        model.addAttribute("point", point);
        return "Grafico3.html";
    }
    @GetMapping("/Grafico4")
    public String Grafico4(Model model){
        int year=2024;
        List<Tuple> lista = facturaService.Grafico4(year);
        List<String> label = new ArrayList<>();
        List<BigDecimal> point = new ArrayList<>();
        BigDecimal data;
        String labels;
        for(Tuple x: lista){
            data= (BigDecimal) x.get("total");
            point.add(data);
        }
        for(Tuple x: lista){
            labels = (String) x.get("mes");
            label.add(labels);
        }
        model.addAttribute("label", label);
        model.addAttribute("point", point);
        return "Grafico4.html";
    }
    @GetMapping("/Grafico5")
    public String Grafico5(Model model){
        List<Tuple> lista = productoService.StockGeneral();
        List<String> label = new ArrayList<>();
        List<BigDecimal> point = new ArrayList<>();
        BigDecimal data;
        String labels;
        for(Tuple x: lista){
            data= (BigDecimal) x.get("stock");
            point.add(data);
        }
        for(Tuple x: lista){
            labels = (String) x.get("nombre");
            label.add(labels);
        }
        model.addAttribute("label", label);
        model.addAttribute("point", point);
        return "Grafico5.html";
    }
    @GetMapping("/Grafico6")
    public String Grafico6(Model model){
        List<Tuple> lista = facturaService.Grafico6();
        List<String> label = new ArrayList<>();
        List<BigDecimal> point = new ArrayList<>();
        BigDecimal data;
        String labels;
        for(Tuple x: lista){
            data= (BigDecimal) x.get("promedio");
            point.add(data);
        }
        for(Tuple x: lista){
            labels = (String) x.get("mes");
            label.add(labels);
        }
        model.addAttribute("label", label);
        model.addAttribute("point", point);
        return "Grafico6.html";
    }
    @GetMapping("/verStock")
    public String verStock(Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaProductos", productoService.ListarProductoActivo());
            return "verStockAdmin.html";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/verUsuarios")
    public String verUsuarios(Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaUsuarios", usuarioSevice.ListarUsuarios());
            return "verUsuariosAdmin.html";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/verFacturas/{id}")
    public String verFacturas(@PathVariable Integer id, Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaFacturas", facturaService.buscarFacturaSegunCliente(id));
            return "verFacturasAdmin.html";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/editFactura/{id}")
    public String editFactura(@PathVariable int id, Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            Factura factura = facturaService.buscarFactura(id);
            model.addAttribute("factura", factura);
            return "FormFactura.html";
        }else{
            return "redirect:/home";
        }
    }
    @PostMapping("/saveFactura")
    public String saveFactura(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        String estado = request.getParameter("estado");
        facturaService.UpdateEstado(estado, id);
        return "redirect:/admin/verUsuarios";
    }
    @GetMapping("/verDetallesFactura/{id}")
    public String verDetallesFactura(Model model, @PathVariable int id, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaDetalles", detalleService.BuscarSegunFactura(id));
            return "verDetalleFacturaAdmin.html";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/verRopaCasual")
    public String verRopaCasual(Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaProducto", productoService.ListarProductoActivo());
            return "verCrudRopaAdmin";
        }else{
            return "redirect:/home";
        }
    }

    @PostMapping("/listarCategoria")
    public String listarPorCategoria(Model model, HttpSession session, @RequestParam("categoria")String categoria){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaProducto", productoService.ListarSegunCategoria(categoria));
            return "verCrudRopaAdmin";
        }else{
            return "redirect:/home";
        }
    }

    @GetMapping("/newRopa")
    public String NewRopa(Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            Ropa ropa = new Ropa();
            Producto producto = new Producto();
            producto.setEstado("active");
            ropa.setProducto(producto);
            model.addAttribute("ropa", ropa); //Mandamos objeto vacio ropa al form, solo con el estado ya seteado
            return "FormRopa.html";
        }else{
            return "redirect:/home";
        }
    }
    @PostMapping("/saveProducto")
    public String saveRopa(@ModelAttribute("ropa")Ropa ropa, @RequestParam("file") MultipartFile imagen, @RequestParam("foto")String foto) {
        if (!imagen.isEmpty()) { //Si es que subieron imagen..
            String rutaAbsoluta = "C://Productos//recursos";
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                ropa.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{ //Si no subieron foto
                ropa.setFoto(foto); //Colocamos la imagen que ya estaba guardada en su registro
        }
        ropaService.saveRopa(ropa);
        return "redirect:/admin/verRopaCasual";
    }
    @GetMapping("/editRopa/{id}")
    public String editRopa(@PathVariable int id, Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            Ropa ropa = ropaService.buscarRopa(id);
            model.addAttribute("ropa" , ropa);
            return "FormRopa.html";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/deleteRopa/{id}")
    public String deleteRopa(@PathVariable int id, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            ropaService.deleteRopa(id);
            return "redirect:/admin/verRopaCasual";
        }else{
            return "redirect:/home";
        }
    }
    @GetMapping("/verReportes")
    public String verReportes(Model model, HttpSession session){
        Boolean adminLoggedIn = (Boolean) session.getAttribute("adminLoggedIn");
        if (adminLoggedIn != null && adminLoggedIn){
            model.addAttribute("listaFac", facturaService.ListasTodasFacturasPendientes());
            model.addAttribute("listaPro", productoService.ListarProductoInactivo());
            return "verReportesAdmin.html";
        }else{
            return "redirect:/home";
        }
    }

}
