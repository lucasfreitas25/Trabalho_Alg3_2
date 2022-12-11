package com.example.application.views.venda;

/* 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.application.entidades.Fornecedor;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
*/
public class VendaView {
    /*
     * @Route(value = "Fornecedor", layout = MainLayout.class)
     * public class FornecedorView extends VerticalLayout {
     * 
     * private TextArea pedidoField;
     * private Button salvarButton;
     * private Button limpabutton;
     * private DatePicker dataField;
     * private List<Fornecedor> lista = new ArrayList<>();;
     * private Grid<Fornecedor> grid = new Grid<>(Fornecedor.class, false);
     * 
     * public FornecedorView() {
     * 
     * add(new Paragraph("PEDIDO PRO FORNECEDOR"));
     * pedidoField = new TextArea("Pedido");
     * dataField = new DatePicker("Data para entrega");
     * salvarButton = new Button("Salvar");
     * limpabutton = new Button("Cancelar");
     * 
     * limpabutton.addClickListener(ev -> {
     * pedidoField.setValue("");
     * dataField.setValue(null);
     * });
     * 
     * salvarButton.addClickListener(ev -> {
     * 
     * String pd = pedidoField.getValue();
     * LocalDate dt = dataField.getValue();
     * 
     * if (pd == "") {
     * Notification.show("Pedido invalido !");
     * pedidoField.focus();
     * return;
     * }
     * if (dt == null) {
     * Notification.show("Data invalida!");
     * dataField.focus();
     * return;
     * }
     * 
     * Fornecedor novo = new Fornecedor();
     * novo.setPedido(pedidoField.getValue());
     * novo.setData(dataField.getValue());
     * lista.add(novo);
     * grid.setItems(lista);
     * });
     * 
     * salvarButton.addClickShortcut(Key.ENTER);
     * HorizontalLayout hl = new HorizontalLayout();
     * hl.add(salvarButton, limpabutton);
     * 
     * grid.addColumn(Fornecedor::getPedido)
     * .setHeader("Pedido");
     * grid.addColumn(Fornecedor::getData)
     * .setHeader("Data de entrega");
     * 
     * 
     * grid.addComponentColumn(c -> {
     * Button editaButton = new Button("Editar");
     * editaButton.addClickListener(ev -> {
     * pedidoField.focus();
     * pedidoField.setValue(c.getPedido());
     * data.setValue(c.getData());
     * id = c.getId_fornecedor();
     * teste = false;
     * });
     * return editaButton;
     * });
     * 
     * 
     * grid.addComponentColumn(c -> {
     * Button del = new Button("Del");
     * del.addClickListener(ev -> {
     * for (int i = 0; i < lista.size(); i++) {
     * if (lista.get(i).getPedido().equals(c.getPedido())) {
     * lista.remove(i);
     * Notification.show("Pedido removido");
     * grid.setItems(lista);
     * break;
     * }
     * }
     * });
     * return del;
     * });
     * 
     * grid.setItems(lista);
     * // grid.setItems(repository.listar());
     * add(pedidoField, dataField, hl, grid);
     */
}
