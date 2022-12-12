package com.example.application.views.venda;

import com.example.application.entidades.Venda;
import com.example.application.repositories.VendaRepository;
import com.example.application.repositories.postgres.VendaRepositoryImpl;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
//import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.select.Select;

@PageTitle("Venda")
@Route(value = "Venda", layout = MainLayout.class)
public class VendaView extends VerticalLayout {

    // private TextArea pedidoField;
    private TextField cpfField;
    private Select<String> entregaField;
    // private NumberField valortotalField;
    private IntegerField quantidadeField;
    private Button salvarButton;
    private Button limpabutton;
    private Grid<Venda> grid = new Grid<>(Venda.class, false);
    private VendaRepository repository = new VendaRepositoryImpl();
    private Boolean teste = true;
    private int id;
    private boolean CpfValido = false;

    public VendaView() {

        add(new Paragraph("Vendas"));
        cpfField = new TextField("Digite o CPF do Cliente");
        quantidadeField = new IntegerField("Cookie Tradional");
        quantidadeField.setValue(1);
        quantidadeField.setHasControls(true);
        quantidadeField.setMin(0);
        quantidadeField.setMax(20);
        entregaField = new Select<>();
        entregaField.setLabel("Forma de entrega");
        entregaField.setItems("Presencial", "Delivery");
        salvarButton = new Button("Salvar");
        limpabutton = new Button("Cancelar");

        limpabutton.addClickListener(ev -> {
            cpfField.setValue("");
            quantidadeField.setValue(1);
            entregaField.setValue("");
        });

        salvarButton.addClickListener(ev -> {

            String cp = cpfField.getValue();
            Integer qt = quantidadeField.getValue();
            String et = entregaField.getValue();

            CpfValido = repository.verificador(cp);
            if (!CpfValido) {
                Notification.show("Cliente nao cadastrado!");
                cpfField.focus();
                return;
            }
            if (qt == null) {
                Notification.show("Quantidade invalida !");
                quantidadeField.focus();
                return;
            }
            if (et == "") {
                Notification.show("Entrega invalida!");
                entregaField.focus();
                return;
            }

            if (cp == "") {
                Notification.show("CPF invalido !");
                cpfField.focus();
                return;
            }

            Venda novo = new Venda();
            novo.setCpf(cpfField.getValue());
            novo.setEntrega(entregaField.getValue());
            novo.setQuantidade(quantidadeField.getValue());
            if (teste == true) {
                repository.inserir(novo);
                Notification.show("Venda concluida!!");
            } else {
                novo.setId_venda(id);
                repository.editar(novo);
                Notification.show("Alteração salva!");
                teste = true;
            }
            grid.setItems(repository.listar());
        });

        salvarButton.addClickShortcut(Key.ENTER);
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(salvarButton, limpabutton);

        grid.addColumn(Venda::getCpf)
                .setHeader("CPF");
        grid.addColumn(Venda::getQuantidade)
                .setHeader("Pedido");
        grid.addColumn(Venda::getEntrega)
                .setHeader("Forma de entrega");

        grid.addComponentColumn(c -> {
            Button editaButton = new Button("Editar");
            editaButton.addClickListener(ev -> {
                cpfField.focus();
                cpfField.setValue(c.getCpf());
                id = c.getId_venda();
                teste = false;
            });
            return editaButton;
        });

        grid.addComponentColumn(c -> {
            Button del = new Button();
            del.setIcon(new Icon(VaadinIcon.TRASH));
            del.addClickListener(ev -> {
                repository.remover(c.getId_venda());
                grid.setItems(repository.listar());
            });
            return del;
        });

        grid.setItems(repository.listar());
        add(cpfField, quantidadeField, entregaField, hl, grid);

    }
}
