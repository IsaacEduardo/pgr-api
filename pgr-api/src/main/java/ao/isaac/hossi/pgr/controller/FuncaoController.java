package ao.isaac.hossi.pgr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ao.isaac.hossi.pgr.model.Funcao;
import ao.isaac.hossi.pgr.repository.FuncaoRepository;

@Controller
public class FuncaoController {
	@Autowired
	private FuncaoRepository funcaoRepository;

	@PostMapping("/addFuncao")
	public ModelAndView addUser(@Valid Funcao funcao, BindingResult result, Model model) {

		funcaoRepository.save(funcao);
		model.addAttribute("users", funcaoRepository.findAll());
		// ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");
		// return modelAndView;
		return new ModelAndView("redirect:/form/funcionario");
	}
}
