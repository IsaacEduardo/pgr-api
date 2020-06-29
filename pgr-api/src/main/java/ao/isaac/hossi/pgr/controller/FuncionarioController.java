package ao.isaac.hossi.pgr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ao.isaac.hossi.pgr.model.Categoria;
import ao.isaac.hossi.pgr.model.EstadoCivil;
import ao.isaac.hossi.pgr.model.Funcao;
import ao.isaac.hossi.pgr.model.Funcionario;
import ao.isaac.hossi.pgr.repository.CategoriaRepository;
import ao.isaac.hossi.pgr.repository.FuncaoRepository;
import ao.isaac.hossi.pgr.repository.FuncionarioRepository;

@Controller
@RequestMapping(value = "form")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private FuncaoRepository funcaoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	// abri o formulario
	@RequestMapping("/funcionario")
	public ModelAndView form(Funcionario funcionario) {

		// enviando objeto para tela
		ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");
		modelAndView.addObject("estadoCivils", EstadoCivil.values());
		modelAndView.addObject("funcoes", funcaoRepository.findAll());
		modelAndView.addObject("categorias", categoriaRepository.findAll());

		return modelAndView;
	}

	@GetMapping("/signup")
	public String showSignUpForm(Funcionario funcionario) {
		return "pages/funcionarioCad";
	}

	@PostMapping("/addFuncionario")
	public ModelAndView addFuncionario(@ModelAttribute("funcionario") @Valid Funcionario funcionario,
			BindingResult result, Model model) {

		/*
		 * if (result.hasErrors()) { return "funcionario"; }
		 */
		if (result.hasErrors()) {
			return form(funcionario);
		}
		model.addAttribute("funcionario", new Funcionario());

		funcionarioRepository.save(funcionario);
		model.addAttribute("users", funcionarioRepository.findAll());
		// ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");
		// return modelAndView;
		return new ModelAndView("redirect:/form/funcionario");
	}

	//Formulario de adição
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid funcionario Id:" + id));

		model.addAttribute("funcionario", funcionario);
		return "update-user";
	}
//atualizar
	@PostMapping("/update/{id}")
	public String updateFuncionario(@PathVariable("id") long id, @Valid Funcionario funcionario, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			funcionario.setId(id);
			return "update-user";
		}

		funcionarioRepository.save(funcionario);
		model.addAttribute("funcionarios", funcionarioRepository.findAll());
		return "index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Funcionario Id:" + id));
		funcionarioRepository.delete(funcionario);
		model.addAttribute("funcionarios", funcionarioRepository.findAll());
		return "index";
	}

	// AAD CATEGORIAS
	@PostMapping("/addCategoria")
	public ModelAndView addCategoria(@Valid Categoria categoria, BindingResult result, Model model) {

		// model.addAttribute("categoria", new Categoria());

		categoriaRepository.save(categoria);
		model.addAttribute("categorias", categoriaRepository.findAll());
		// ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");
		// return modelAndView;
		return new ModelAndView("redirect:/form/funcionario");
	}
	
	//ADD Funcao
	
	@PostMapping("/addFuncao")
	public ModelAndView addFuncao(@Valid Funcao funcao, BindingResult result, Model model) {
		funcaoRepository.save(funcao);
		model.addAttribute("users", funcaoRepository.findAll());
		// ModelAndView modelAndView = new ModelAndView("pages/funcionarioCad");
		// return modelAndView;
		return new ModelAndView("redirect:/form/funcionario");
	}

}
