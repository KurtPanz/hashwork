package hashwork.client.content.system.demographics.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import hashwork.client.content.system.demographics.model.LanguageModel;

/**
 * Created by hashcode on 2015/08/18.
 */
public class LanguageForm extends FormLayout {

    private final LanguageModel bean;
    public final BeanItem<LanguageModel> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public LanguageForm() {
        bean = new LanguageModel();
        item = new BeanItem<LanguageModel>(bean);
        binder = new FieldGroup(item);

        TextField name = new TextField("Language");
        name.setNullRepresentation("");


        // Add the bean validator
        name.addValidator(new BeanValidator(LanguageModel.class, "name"));
        name.setImmediate(true);


        // Create a field group and use it to bind the fields in the layout

        binder.bind(name, "name");
        addComponent(name);
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
