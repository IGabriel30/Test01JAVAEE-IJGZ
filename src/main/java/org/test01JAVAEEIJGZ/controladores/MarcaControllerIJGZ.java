package org.test01JAVAEEIJGZ.controladores;

import java.util.stream.Collectors;
import org.test01JAVAEEIJGZ.modelos.MarcaIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.IMarcaServiceIJGZ;
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
@RequestMapping("/marcas")
public class MarcaControllerIJGZ {
    @Autowired
    private IMarcaServiceIJGZ marcaServiceIJGZ;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<MarcaIJGZ> marcas = marcaServiceIJGZ.buscarTodosPaginados(pageable);
        model.addAttribute("marcas", marcas);

        int totalPages = marcas.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "marca/index";
    }

    @GetMapping("/create")
    public String create(MarcaIJGZ marca){
        return "marca/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("marca") MarcaIJGZ marca, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(marca);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "marca/create";
        }

        marcaServiceIJGZ.crearOEditar(marca);
        attributes.addFlashAttribute("msg", "Marca creada correctamente");
        return "redirect:/marcas";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Optional<MarcaIJGZ> marcaOpt = marcaServiceIJGZ.buscarPorId(id);
        if (marcaOpt.isPresent()) {
            model.addAttribute("marca", marcaOpt.get());
            return "marca/details";
        } else {
            return "redirect:/marcas";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Optional<MarcaIJGZ> marcaOpt = marcaServiceIJGZ.buscarPorId(id);
        if (marcaOpt.isPresent()) {
            model.addAttribute("marca", marcaOpt.get());
            return "marca/edit";
        } else {
            return "redirect:/marcas";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Optional<MarcaIJGZ> marcaOpt = marcaServiceIJGZ.buscarPorId(id);
        if (marcaOpt.isPresent()) {
            model.addAttribute("marca", marcaOpt.get());
            return "marca/delete";
        } else {
            return "redirect:/marcas";
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("marca") MarcaIJGZ marca, RedirectAttributes attributes){
        marcaServiceIJGZ.eliminarPorId(marca.getId());
        attributes.addFlashAttribute("msg", "Marca eliminada correctamente");
        return "redirect:/marcas";
    }
}
