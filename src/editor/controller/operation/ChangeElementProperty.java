package editor.controller.operation;

import editor.model.Element;
import editor.model.ElementProperty;
import editor.controller.Operation;


/**
* An operation aiming at changing any element's property.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ChangeElementProperty extends Operation {

    /**
     * The name of the property that will be changed
     */
    protected String propertyName;

    /**
     * The previous property
     */
    protected ElementProperty from;

    /**
     * The new property
     */
    protected ElementProperty to;

    /**
     * Alter the property of an element
     * 
     * @param element The element to change
     * @param propertyName The name of the property
     * @param from The previous property
     * @param to The new property
     */
    public ChangeElementProperty(Element element, String propertyName, ElementProperty from, ElementProperty to) {
        state = element;
        this.propertyName = propertyName;
        this.from = from.Clone();
        this.to = to.Clone();
    }

    @Override
    public void Do() {
        ((Element) state).properties.put(propertyName, to);
    }

    @Override
    public void Undo() {
        ((Element) state).properties.put(propertyName, from);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}