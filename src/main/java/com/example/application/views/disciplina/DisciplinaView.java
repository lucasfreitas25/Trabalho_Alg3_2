package com.example.application.views.disciplina;

import com.example.application.entidades.Disciplina;
import com.example.application.repositories.DisciplinaRepository;
import com.example.application.repositories.postgres.DisciplinaRepositoryImpl;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "disciplina", layout = MainLayout.class)
public class DisciplinaView extends VerticalLayout {

    private TextField nomefield;
    private IntegerField cargahorariafield;
    private Button salvarButton;
    private Button limparButton;
    private Grid<Disciplina> grid = new Grid<>(Disciplina.class, false);
    private DisciplinaRepository repository = new DisciplinaRepositoryImpl();
    private Boolean teste = true;
    private int id;

    public DisciplinaView() {
        nomefield = new TextField("Disciplina: ");
        cargahorariafield = new IntegerField("Carga Horaria");
        salvarButton = new Button("Salvar");
        limparButton = new Button("Cancelar");

        limparButton.addClickListener(ev -> {
            nomefield.setValue("");
            cargahorariafield.setValue(null);
        });

        salvarButton.addClickListener(ev -> {

            Integer ch = cargahorariafield.getValue();
            String nm = nomefield.getValue();

            if (nm == "") {
                Notification.show("Insira seu nome!");
                nomefield.focus();
                return;
            }

            if (ch == null || ch <= 0) {
                Notification.show("Carga horária inválida!!");
                cargahorariafield.focus();
                return;
            }

            Disciplina novo = new Disciplina();
            novo.setNome(nomefield.getValue());
            novo.setCargahoraria(cargahorariafield.getValue());

            if (teste == true) {
                repository.inserir(novo);
                Notification.show("Salvo!!");
            } else {
                novo.setId(id);
                repository.editar(novo);
                Notification.show("Alteração salva!");
                teste = true;
            }

            grid.setItems(repository.listar());
            limparButton.click();
        });

        salvarButton.addClickShortcut(Key.ENTER);
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(salvarButton, limparButton);

        grid.addColumn(Disciplina::getNome)
                .setHeader("Disciplina");
        grid.addColumn(Disciplina::getCargahoraria)
                .setHeader("Carga Horária");

        grid.addComponentColumn(c -> {
            Button editaButton = new Button("Editar");
            editaButton.addClickListener(ev -> {
                nomefield.focus();
                nomefield.setValue(c.getNome());
                cargahorariafield.setValue(c.getCargahoraria());
                id = c.getId();
                teste = false;
            });
            return editaButton;
        });

        grid.addComponentColumn(c -> {
            Button del = new Button("DEL");
            del.addClickListener(ev -> {
                repository.remover(c.getId());
                grid.setItems(repository.listar());
            });
            return del;

        });
        grid.setItems(repository.listar());
        add(nomefield, cargahorariafield, hl, grid);
    }
}
