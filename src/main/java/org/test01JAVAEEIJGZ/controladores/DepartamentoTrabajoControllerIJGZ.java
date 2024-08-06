package org.test01JAVAEEIJGZ.controladores;

import org.test01JAVAEEIJGZ.modelos.DepartamentoTrabajoIJGZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.test01JAVAEEIJGZ.servicios.interfaces.IDepartamentoTrabajoServiceIJGZ;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("departamentosTrabajo")
public class DepartamentoTrabajoControllerIJGZ {
    @Autowired
    private IDepartamentoTrabajoServiceIJGZ departamentoTrabajoServiceIJGZ;
  
    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<DepartamentoTrabajoIJGZ> departamentosTrabajo = departamentoTrabajoServiceIJGZ.buscarTodosPaginados(pageable);
        model.addAttribute("departamentosTrabajo", departamentosTrabajo);

        int totalPages = departamentosTrabajo.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "departamentoTrabajo/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("departamentoTrabajo", new DepartamentoTrabajoIJGZ());
        return "departamentoTrabajo/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("departamentoTrabajo") DepartamentoTrabajoIJGZ departamentoTrabajo, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("departamentoTrabajo", departamentoTrabajo);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "departamentoTrabajo/create";
        }

        departamentoTrabajoServiceIJGZ.crearOEditar(departamentoTrabajo);
        attributes.addFlashAttribute("msg", "Departamento Trabajo creado correctamente.");
        return "redirect:/departamentosTrabajo";
    }

    @GetMapping("/details/{id}")
public String details(@PathVariable("id") Integer id, Model model) {
    DepartamentoTrabajoIJGZ departamentoTrabajo = departamentoTrabajoServiceIJGZ.buscarPorId(id).get();
    model.addAttribute("departamentoTrabajo", departamentoTrabajo);
    return "departamentoTrabajo/details";
}


  
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        DepartamentoTrabajoIJGZ departamentoTrabajo = departamentoTrabajoServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("departamentoTrabajo", departamentoTrabajo);
        return "departamentoTrabajo/edit";
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute("departamentoTrabajo") DepartamentoTrabajoIJGZ departamentoTrabajo, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("departamentoTrabajo", departamentoTrabajo);
            attributes.addFlashAttribute("error", "No se pudo modificar debido a un error.");
            return "departamentoTrabajo/edit";
        }

        departamentoTrabajoServiceIJGZ.crearOEditar(departamentoTrabajo);
        attributes.addFlashAttribute("msg", "Departamento Trabajo  modificado correctamente");
        return "redirect:/departamentosTrabajo";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        DepartamentoTrabajoIJGZ departamentoTrabajo = departamentoTrabajoServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("departamentoTrabajo", departamentoTrabajo);
        return "departamentoTrabajo/delete";
    }

    @PostMapping("/delete")
    public String delete(DepartamentoTrabajoIJGZ departamentoTrabajo, RedirectAttributes attributes){
        departamentoTrabajoServiceIJGZ.eliminarPorId(departamentoTrabajo.getId());
        attributes.addFlashAttribute("msg", "Departamento Trabajo eliminado correctamente");
        return "redirect:/departamentosTrabajo";
    }
}
