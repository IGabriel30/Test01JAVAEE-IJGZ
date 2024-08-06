package org.test01JAVAEEIJGZ.controladores;


import java.util.stream.Collectors;

import org.test01JAVAEEIJGZ.modelos.CategoriaIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.ICategoriaServiceIJGZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("categorias")
public class CategoriaControllerIJGZ {
    @Autowired
    private ICategoriaServiceIJGZ categoriaServiceIJGZ;
  
    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<CategoriaIJGZ> categorias = categoriaServiceIJGZ.buscarTodosPaginados(pageable);
        model.addAttribute("categorias", categorias);

        int totalPages = categorias.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "categoria/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("categoria", new CategoriaIJGZ());
        return "categoria/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("categoria") CategoriaIJGZ categoria, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("categoria", categoria);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "categoria/create";
        }

        categoriaServiceIJGZ.crearOEditar(categoria);
        attributes.addFlashAttribute("msg", "Categoria creada correctamente");
        return "redirect:/categorias";
    }

    @GetMapping("/details/{id}")
public String details(@PathVariable("id") Integer id, Model model) {
    CategoriaIJGZ categoria = categoriaServiceIJGZ.buscarPorId(id).get();
    model.addAttribute("categoria", categoria);
    return "categoria/details";
}


  
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        CategoriaIJGZ categoria = categoriaServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("categoria", categoria);
        return "categoria/edit";
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute("categoria") CategoriaIJGZ categoria, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("categoria", categoria);
            attributes.addFlashAttribute("error", "No se pudo modificar debido a un error.");
            return "categoria/edit";
        }

        categoriaServiceIJGZ.crearOEditar(categoria);
        attributes.addFlashAttribute("msg", "Categoria modificada correctamente");
        return "redirect:/categorias";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        CategoriaIJGZ categoria = categoriaServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("categoria", categoria);
        return "categoria/delete";
    }

    @PostMapping("/delete")
    public String delete(CategoriaIJGZ categoria, RedirectAttributes attributes){
        categoriaServiceIJGZ.eliminarPorId(categoria.getId());
        attributes.addFlashAttribute("msg", "Categoria eliminada correctamente");
        return "redirect:/categorias";
    }
}
