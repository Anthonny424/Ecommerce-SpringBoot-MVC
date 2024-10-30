package com.example.ProyectoIntegrador;

import com.example.ProyectoIntegrador.repository.DetalleRepository;
import com.example.ProyectoIntegrador.service.FacturaServiceImpl;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class ProyectoIntegradorApplicationTests {

	@Autowired
	private FacturaServiceImpl facturaService;
	@Autowired
	private DetalleRepository repository;

	@Test
	void contextLoads() {
    String fecha = "2024-04-19 19:53:13.0";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date aea = formato.parse(fecha);
			System.out.println(aea);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}

