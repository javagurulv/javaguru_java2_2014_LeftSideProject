package lv.javaguru.java2.web.mvc.todoItemServlet;

import lv.javaguru.java2.database.TodoItemDAO;
import lv.javaguru.java2.domain.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alekmiku on 2014.11.11..
 */
@Controller
@Transactional
public class TodoItemController {

    @Autowired
    @Qualifier("ORM_TodoItemDAO")
    private TodoItemDAO todoItemDAO;

//        @Autowired(required=true)
//        @Qualifier(value="personService")
//        public void setPersonService(PersonService ps){
//            this.personService = ps;
//        }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String listItems(Model model) {
        model.addAttribute("item", new TodoItem());
        model.addAttribute("listItems", todoItemDAO.getAll());
        return "TodoItem";
    }

    //For add and update item
    @RequestMapping(value = "/item/add", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("item") TodoItem tdi) {

        if (tdi.getItemId() == 0) {
            //new item, add it
            todoItemDAO.create(tdi);
        } else {
            //existing person, call update
            todoItemDAO.merge(tdi);
        }
        return "redirect:/item";

    }

    @RequestMapping("/item/remove/{itemID}")
    public String removeItem(@PathVariable("itemID") int itemID) {
        TodoItem itemToDel = todoItemDAO.getById((long) itemID);
        todoItemDAO.delete(itemToDel);
        return "redirect:/items";
    }

    @RequestMapping("/item/edit/{itemID}")
    public String editItem(@PathVariable("itemID") int itemID, Model model) {
        model.addAttribute("item", todoItemDAO.getById(Long.valueOf(itemID)));
        model.addAttribute("listItems", todoItemDAO.getAll());
        return "TodoItem";
    }

}