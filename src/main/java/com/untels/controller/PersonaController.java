package com.untels.controller;

import com.untels.service.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/lista-personas")
    public String listaPersonas(Model model) {
        model.addAttribute("listaPersonas", personaService.getAllPersonas());
        return "personas/lista-personas";
    }

}

/*
 * package com.untels.controller;
 * 
 * import java.util.Optional;
 * 
 * import com.untels.entity.Empleado; import com.untels.service.EmpleadoService;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping;
 * 
 * @Controller public class EmpleadoController {
 * 
 * @Autowired private EmpleadoService empleadoService;
 * 
 * @GetMapping("/lista-empleados") public String listaEmpleados(Model model) {
 * model.addAttribute("listaEmpleados", empleadoService.getAllEmpleados());
 * return "empleado/lista-empleados"; }
 * 
 * @GetMapping("/nuevo-empleado") public String nuevoEmpleado(Model model) {
 * Empleado empleado = new Empleado(); model.addAttribute("empleado", empleado);
 * return "empleado/nuevo-empleado"; }
 * 
 * @PostMapping("/grabar-empleado") public String
 * grabarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
 * empleadoService.grabarEmpleado(empleado); return "redirect:/lista-empleados";
 * }
 * 
 * @GetMapping("/editar-empleado/{id}") public String
 * editarEmpleado(@PathVariable(value = "id") long id, Model model) {
 * Optional<Empleado> empleadoOpt = empleadoService.getEmpleadoById(id); if
 * (empleadoOpt.isPresent()) { Empleado empleado = empleadoOpt.get();
 * model.addAttribute("empleado", empleado); return "empleado/editar-empleado";
 * }
 * 
 * return "redirect:/lista-empleados"; }
 * 
 * @GetMapping("/eliminar-empleado/{id}") public String
 * eliminarEmpleado(@PathVariable(value = "id") long id) {
 * this.empleadoService.eliminarEmpleadoById(id); return
 * "redirect:/lista-empleados"; } }
 */