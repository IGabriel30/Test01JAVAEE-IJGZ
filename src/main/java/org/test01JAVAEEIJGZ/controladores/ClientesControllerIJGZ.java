package org.test01JAVAEEIJGZ.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.test01JAVAEEIJGZ.modelos.ClienteIJGZ;
import org.test01JAVAEEIJGZ.servicios.interfaces.IClienteServiceIJGZ;

@Controller
@RequestMapping("clientes")
public class ClientesControllerIJGZ {
    @Autowired
    private IClienteServiceIJGZ clienteServiceIJGZ;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<ClienteIJGZ> cliente = clienteServiceIJGZ.buscarTodosPaginados(pageable);
        model.addAttribute("clientes", cliente);

        int totalPages = cliente.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "cliente/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("cliente", new ClienteIJGZ());
        return "cliente/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("cliente") ClienteIJGZ cliente, BindingResult result, Model model,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("cliente", cliente);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "cliente/create";
        }

        clienteServiceIJGZ.crearOEditar(cliente);
        attributes.addFlashAttribute("msg", "Cliente creado correctamente.");
        return "redirect:/clientes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        ClienteIJGZ cliente = clienteServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("cliente", cliente);
        return "cliente/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        ClienteIJGZ cliente = clienteServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("cliente", cliente);
        return "cliente/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("cliente") ClienteIJGZ cliente, BindingResult result, Model model,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("cliente", cliente);
            attributes.addFlashAttribute("error", "No se pudo modificar debido a un error.");
            return "cliente/edit";
        }

        clienteServiceIJGZ.crearOEditar(cliente);
        attributes.addFlashAttribute("msg", "Cliente  modificado correctamente");
        return "redirect:/clientes";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        ClienteIJGZ cliente = clienteServiceIJGZ.buscarPorId(id).get();
        model.addAttribute("cliente", cliente);
        return "cliente/delete";
    }

    @PostMapping("/delete")
    public String delete(ClienteIJGZ cliente, RedirectAttributes attributes) {
        clienteServiceIJGZ.eliminarPorId(cliente.getId());
        attributes.addFlashAttribute("msg", "Cliente  eliminado correctamente");
        return "redirect:/clientes";
    }
}
