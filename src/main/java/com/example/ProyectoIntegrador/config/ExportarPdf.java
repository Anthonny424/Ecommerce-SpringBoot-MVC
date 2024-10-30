package com.example.ProyectoIntegrador.config;

import com.example.ProyectoIntegrador.model.Detalle;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("verDetalleFactura.html")
public class ExportarPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Detalle> listaDetalle = (List<Detalle>) model.get("listaDetalle");
        Font fuenteTitulo = FontFactory.getFont("Helvetica",16, Color.WHITE);
        Font fuenteColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
        Font fuenteCeldas = FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);

        //Mejoras estéticas
        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20, -20, 40,20);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;

        celda = new PdfPCell(new Phrase("Resumen de Factura", fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(74, 78,81));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);


        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        //Creación de la tabla
        PdfPTable tablaDetalle = new PdfPTable(5);
        tablaDetalle.setWidths(new float[]{0.8f,2f,1f,1f,1f});
        celda=new PdfPCell(new Phrase("Id", fuenteColumnas));
        celda.setBackgroundColor(new Color(74, 78,81));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaDetalle.addCell(celda);

        celda=new PdfPCell(new Phrase("Nombre", fuenteColumnas));
        celda.setBackgroundColor(new Color(74, 78,81));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaDetalle.addCell(celda);

        celda=new PdfPCell(new Phrase("Precio", fuenteColumnas));
        celda.setBackgroundColor(new Color(74, 78,81));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaDetalle.addCell(celda);

        celda=new PdfPCell(new Phrase("Cantidad", fuenteColumnas));
        celda.setBackgroundColor(new Color(74, 78,81));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaDetalle.addCell(celda);

        celda=new PdfPCell(new Phrase("Total", fuenteColumnas));
        celda.setBackgroundColor(new Color(74, 78,81));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaDetalle.addCell(celda);



        listaDetalle.forEach(detalle -> {
            tablaDetalle.addCell(String.valueOf(detalle.getId()));
            tablaDetalle.addCell(detalle.getNombre());
            tablaDetalle.addCell(String.valueOf(detalle.getPrecio()));
            tablaDetalle.addCell(String.valueOf(detalle.getCantidad()));
            tablaDetalle.addCell(String.valueOf(detalle.getTotal()));

        });
        document.add(tablaTitulo);
        document.add(tablaDetalle);
    }
}
