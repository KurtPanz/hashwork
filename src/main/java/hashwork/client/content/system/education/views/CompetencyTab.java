package hashwork.client.content.system.education.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import hashwork.app.facade.EducationFacade;
import hashwork.client.content.MainLayout;
import hashwork.client.content.system.education.EducationMenu;
import hashwork.client.content.system.education.forms.CompetencyForm;
import hashwork.client.content.system.education.model.CompetencyModel;
import hashwork.client.content.system.education.table.CompetencyTable;
import hashwork.domain.ui.education.Competency;
import hashwork.factories.ui.education.CompetencyFactory;

/**
 * Created by zamzam on 2015/10/07.
 */
public class CompetencyTab extends VerticalLayout
implements Button.ClickListener, Property.ValueChangeListener{
    private final MainLayout main;
    private final CompetencyForm form;
    private final CompetencyTable table;

    public CompetencyTab(MainLayout app) {
        main = app;
        form = new CompetencyForm();
        table = new CompetencyTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Competency Competency = EducationFacade.competencyService.findById(table.getValue().toString());
            final CompetencyModel model = getModel(Competency);
            form.binder.setItemDataSource(new BeanItem<>(model));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            EducationFacade.competencyService.save(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            EducationFacade.competencyService.update(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        Competency Competency = EducationFacade.competencyService.findById(table.getValue().toString());
        if (false) {
            Notification.show("CANNOT DELETE", "Object has related Items. Delete Related Items First", Notification.Type.ERROR_MESSAGE);
        } else {
            EducationFacade.competencyService.delete(Competency);
            getHome();
        }
    }


    private void getHome() {
        main.content.setSecondComponent(new EducationMenu(main, "Competency"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listeners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }


    private Competency getNewEntity(FieldGroup binder) {
        final CompetencyModel model = ((BeanItem<CompetencyModel>) binder.getItemDataSource()).getBean();
        final Competency Competency = CompetencyFactory.getCompetency(
                model.getName(),
                model.getCompetencyTypeId(),
                model.getNotes());
        return Competency;
    }

    private Competency getUpdateEntity(FieldGroup binder) {
        final CompetencyModel model = ((BeanItem<CompetencyModel>) binder.getItemDataSource()).getBean();
        final Competency competency = EducationFacade.competencyService.findById(table.getValue().toString());
        final Competency updatedCompetency = new Competency
                .Builder().copy(competency)
                .name(model.getName())
                .competencyTypeId(model.getCompetencyTypeId())
                .Notes(model.getNotes())
                .build();
        return updatedCompetency;


    }

    private CompetencyModel getModel(Competency competency) {
        final CompetencyModel model = new CompetencyModel();
        model.setName(competency.getName());
        model.setCompetencyTypeId(competency.getCompetencyTypeId());
        model.setNotes(competency.getNotes());
        return model;
    }
}
