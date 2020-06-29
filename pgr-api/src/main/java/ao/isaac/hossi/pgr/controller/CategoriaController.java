package ao.isaac.hossi.pgr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ao.isaac.hossi.pgr.model.Categoria;
import ao.isaac.hossi.pgr.repository.CategoriaRepository;


public class CategoriaController {
	/*
	@Autowired
	private CategoriaRepository categoriaRepository;

	// abri o formulario
	@RequestMapping("/categoria")
	public ModelAndView categoria(Categoria categoria) {
		// enviando objeto para tela
		ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");

		return modelAndView;
	}

	@PostMapping("/addCategoria")
	public ModelAndView addCategoria(@ModelAttribute("categoria") @Valid Categoria categoria, BindingResult result, Model model) {

		model.addAttribute("categoria", new Categoria());

		categoriaRepository.save(categoria);
		model.addAttribute("categorias", categoriaRepository.findAll());
		// ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");
		// return modelAndView;
		return new ModelAndView("redirect:/form/funcionario");
	}
	*/
}
