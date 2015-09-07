package hashwork.client.content.system.locations.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.*;
import hashwork.client.content.system.locations.model.RegistrationBodyModel;


/**
 * Created by hashcode on 2015/09/07.
 */
public class RegistrationBodyForm extends FormLayout {
    private final RegistrationBodyModel bean;
    public final BeanItem<RegistrationBodyModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public RegistrationBodyForm() {
        bean = new RegistrationBodyModel();
        item = new BeanItem<RegistrationBodyModel>(bean);
        binder = new FieldGroup(item);

        TextField name = new TextField("Name");
        name.setNullRepresentation("");
        TextField description = new TextField("Description");
        description.setNullRepresentation("");
        TextField coreActivity = new TextField("Core Activity");
        coreActivity.setNullRepresentation("");
        TextField active = new TextField("Active");
        active.setNullRepresentation("");
        DateField date = new DateField("Date Created");


        // Add the bean validator
        name.addValidator(new BeanValidator(RegistrationBodyModel.class, "name"));
        name.setImmediate(true);
        date.addValidator(new BeanValidator(RegistrationBodyModel.class, "asOfDate"));
        date.setImmediate(true);

        // Create a field group and use it to bind the fields in the layout

        binder.bind(name, "name");
        binder.bind(description, "description");
        binder.bind(coreActivity, "coreActivity");
        binder.bind(active, "active");
        binder.bind(date, "asOfDate");

        addComponent(name);
        addComponent(description);
        addComponent(coreActivity);
        addComponent(active);
        addComponent(date);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);

        // Determines which properties are shown, and in which order:
        edit.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        addComponent(buttons);
    }
}
