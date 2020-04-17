package editor.domain;

import java.util.ArrayList;
import java.util.Optional;

import editor.application.App;
import editor.domain.menu.InteractionMenu;
import editor.domain.menu.TopMenu;
import editor.domain.menu.interactionmenus.ElementInteractionMenu;

public class AppController {

    private final int drag_start_dist = 4;

    private Canvas canvas;
    private Toolbar toolbar;
    private TopMenu topMenu;

    private boolean left = false;
    private boolean left_clicked = false;
    private int left_pos_x = -1;
    private int left_pos_y = -1;

    private boolean right = false;
    private boolean right_clicked = false;
    private int right_pos_x = -1;
    private int right_pos_y = -1;

    private int pos_x = 0;
    private int pos_y = 0;

    private Element holdElement = null;
    private boolean clickedOnHoldElement = false;

    private Element draggingElement = null;
    private int drag_x_elem_rel;
    private int drag_y_elem_rel;
    private boolean dragging_from_toolbar = false;

    private boolean readyToDrag = true;

    private boolean selecting = false;
    private int select_start_x;
    private int select_start_y;

    public AppController() {
        canvas = App.model.getCanvas();
        toolbar = App.model.getToolbar();
        topMenu = App.model.getTopMenu();
    }

    public void NotifyMouseLeftPress(int x, int y) {
        left = true;
        left_pos_x = x;
        left_pos_y = y;
        NotifyMousePos(x, y);
    }

    public void NotifyMouseLeftRelease(int x, int y) {
        left = false;
        left_clicked = true;
        left_pos_x = -1;
        left_pos_y = -1;
        NotifyMousePos(x, y);
    }

    public void NotifyMouseRightPress(int x, int y) {
        right = true;
        right_pos_x = x;
        right_pos_y = y;
        NotifyMousePos(x, y);
    }

    public void NotifyMouseRightRelease(int x, int y) {
        right = false;
        right_clicked = true;
        right_pos_x = -1;
        right_pos_y = -1;
        NotifyMousePos(x, y);
    }

    public void NotifyMousePos(int x, int y) {
        pos_x = x;
        pos_y = y;
        UpdateMouse();
    }

    public void NotifyWidth(int newWidth) {
        App.model.Resize(newWidth, App.model.height);
    }

    public void NotifyHeight(int newHeight) {
        App.model.Resize(App.model.width, newHeight);
    }

    public void UpdateMouse() {

        
        int canvas_relative_x = Math.max(0, pos_x - canvas.pos_x);
        int canvas_relative_y = Math.max(0, pos_y - canvas.pos_y);
        
        boolean inCanvas = App.model.getCanvas().isClicked(pos_x, pos_y);
        boolean inToolbar = App.model.getToolbar().isClicked(pos_x, pos_y);
        boolean inTopMenu = App.model.getTopMenu().isClicked(pos_x, pos_y);
        boolean inInteractionMenu = App.model.getInteractionMenu() == null ? false : App.model.getInteractionMenu().isClicked(pos_x, pos_y);

        System.out.println("----------------------------");
        System.out.println("readyToDrag: "+readyToDrag);
        System.out.println("dragging: "+draggingElement);
        System.out.println("holdElement: "+holdElement);
        System.out.println("selecting: "+selecting);
        System.out.println("clickedOnHoldElement: "+clickedOnHoldElement);

        if(left) {
            if(draggingElement == null && readyToDrag && holdElement == null) {
                // Find the clicked element before dragging
                Optional<Element> found = canvas.getElementAt(canvas_relative_x, canvas_relative_y);

                if(found.isPresent()) {
                    holdElement = found.get();
                    if(holdElement.isSelected()) {
                        clickedOnHoldElement = true;
                    }
                    holdElement.setSelected(true);
                    App.view.getCanvasView().Update();
                }
            } else if (holdElement != null && holdElement.isSelected()) {
                clickedOnHoldElement = true;
            }

            if(Math.sqrt((left_pos_y - pos_y) * (left_pos_y - pos_y) + (left_pos_x - pos_x) * (left_pos_x - pos_x)) >= this.drag_start_dist) {
                // Dragging

                if(inCanvas || draggingElement != null) {
                    if(selecting) {
                        int sr_x = Math.min(select_start_x, canvas_relative_x);
                        int sr_y = Math.min(select_start_y, canvas_relative_y);
                        int sr_width = select_start_x < canvas_relative_x ? canvas_relative_x - select_start_x : select_start_x - canvas_relative_x;
                        int sr_height = select_start_y < canvas_relative_y ? canvas_relative_y - select_start_y : select_start_y - canvas_relative_y;

                        App.model.UpdateSelectionRectangle(sr_x, sr_y, sr_width, sr_height);

                        App.model.getCanvas().getElements().forEach(element -> {
                            if(element.intersects(sr_x, sr_y, sr_width, sr_height)) {
                                App.model.Select(element);
                            } else {
                                App.model.Deselect(element);
                            }
                        });
                        return;
                    }
                    if(!readyToDrag) return; // Started dragging on an empty spot, ignoring.
                    if(dragging_from_toolbar) {
                        dragging_from_toolbar = false;
                        draggingElement = App.model.addElement(App.model.getSelectedTool(),canvas_relative_x,canvas_relative_y);
                        draggingElement.setSelected(true);
                        App.model.setSelectedTool(null);
                    }

                    if(draggingElement == null) {

                        select_start_x = canvas_relative_x;
                        select_start_y = canvas_relative_y;
                        if(holdElement == null) {
                            readyToDrag = false;
                            selecting = true;
                        } else {
                            draggingElement = holdElement;
                            drag_x_elem_rel = canvas_relative_x - draggingElement.pos_x;
                            drag_y_elem_rel = canvas_relative_y - draggingElement.pos_y;
                        }
                    }

                    if(draggingElement != null) {
                        draggingElement.Update(Math.max(0,canvas_relative_x - drag_x_elem_rel), Math.max(0,canvas_relative_y - drag_y_elem_rel));
                    }
                } else if (inToolbar) {

                    if(!dragging_from_toolbar) {
                        // Find an element to select and drag, or, select nothing
                        Optional<ToolbarElement> found = toolbar.getToolbarElements()
                                                                .stream().filter(element -> element.isClicked(pos_x, pos_y))
                                                                .findFirst();
        
                        if(!found.isPresent()) {
                            App.model.setSelectedTool(null);
                            readyToDrag = false;
                        } else {
                            App.model.setSelectedTool(found.get().getElement());
                            dragging_from_toolbar = true;
                        }
                    }
                }
            } else {
                // Holding click
            }
        }

        if(left_clicked) {
            left_clicked = false; // Consume event

            if(inInteractionMenu) {
                App.model.getInteractionMenu().onClick(pos_x, pos_y);
                return;
            } else if (!(App.model.getInteractionMenu() == null)) {
                App.model.setInteractionMenu(null);
                return;
            }
                
            readyToDrag = true;

            if(selecting) {
                selecting = false;
                App.model.UpdateSelectionRectangle(0,0,0,0);
                return;
            }

            if(draggingElement != null) {
                if(inToolbar) {
                    draggingElement.Update(select_start_x - drag_x_elem_rel, select_start_y - drag_y_elem_rel);
                    toolbar.addElement(draggingElement);
                }
            } else {

                if(inCanvas) {
                    // Clicking in canvas adds the selected element
                    if(holdElement == null) {
                        App.model.DeselectAll();
                    } else {
                        if(clickedOnHoldElement) {
                            clickedOnHoldElement = false;
                            App.model.Deselect(holdElement);
                        }
                    }
                    
                    Element selected = App.model.getSelectedTool();
                    if(selected != null) {
                        App.model.addElement(selected,canvas_relative_x,canvas_relative_y);
                    } else {
                        App.view.getCanvasView().Update();
                    }
                }
    
                if(inToolbar) {
                    // Find an element to select, or, select nothing
                    Optional<ToolbarElement> found = toolbar.getToolbarElements()
                                                            .stream().filter(element -> element.isClicked(pos_x, pos_y))
                                                            .findFirst();
    
                    if(!found.isPresent()) {
                        App.model.setSelectedTool(null);
                    } else {
                        App.model.setSelectedTool(found.get().getElement());
                    }
                }

            }
            
            draggingElement = null;
            holdElement = null;
            return;
        }
    
        if(right) {

        }

        if(right_clicked) {
            right_clicked = false;

            if(inCanvas) {
                Optional<Element> found = canvas.getElementAt(canvas_relative_x, canvas_relative_y);

                if(found.isPresent()) {
                    App.model.Select(found.get());
                    App.model.setInteractionMenu(new ElementInteractionMenu(pos_x, pos_y,found.get()));
                    return;
                } else {
                    App.model.DeselectAll();
                    App.view.getCanvasView().Update();
                }
            } 
            
            if (!(App.model.getInteractionMenu() == null)) {
                App.model.setInteractionMenu(null);
                return;
            }
        }
    }
}