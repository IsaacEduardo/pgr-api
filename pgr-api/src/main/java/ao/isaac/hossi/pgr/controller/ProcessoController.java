package ao.isaac.hossi.pgr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ao.isaac.hossi.pgr.model.AreaProcesso;
import ao.isaac.hossi.pgr.model.Processo;
import ao.isaac.hossi.pgr.repository.IArguidoRepository;
import ao.isaac.hossi.pgr.service.IArguidoService;
import ao.isaac.hossi.pgr.service.FuncionarioService;
import ao.isaac.hossi.pgr.service.ProcessoService;
import ao.isaac.hossi.pgr.util.SkuUtility;

@Controller
@RequestMapping(value = "processo")
public class ProcessoController {

	@Autowired
	private IArguidoRepository arguidoRepository;
	@Autowired
	private IArguidoService arguidoService;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private ProcessoService processoService;

	// tela
	@RequestMapping(value = "/novo")
	public ModelAndView novo(Processo processo) {
		ModelAndView mv = new ModelAndView("pages/processoAdd");
		mv.addObject("areaProcessos", AreaProcesso.values());
		mv.addObject("instrutores", funcionarioService.findAll());
		mv.addObject("arguidos", arguidoService.findAll());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvaProcessoPost(@Valid Processo processo, BindingResult result, HttpServletRequest request,
			final MultipartFile file, RedirectAttributes attributes, Model model) throws IOException {

		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());

		if (result.hasErrors()) {
			return novo(processo);
		}

		// adicionando validacao antes de salvar
		if (result.hasErrors()) {
			// ModelAndView model = new ModelAndView("pages/arguidoCad");
			// mostrar validacao
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : result.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());// vem das anotacoes @NotEmp e outras
			}

		}

		if (file.getSize() > 0) {/* cadastrando um curriculo */
			processo.setAnexo(file.getBytes());
			processo.setTipoFileAnexo(file.getContentType());
			processo.setNomeFileAnexo(file.getOriginalFilename());

		} else {
			if (processo.getId() != null && processo.getId() > 0) {// editando

				Processo processoTemp = processoService.findById(processo.getId()).get();

				processo.setAnexo(processoTemp.getAnexo());
				processo.setTipoFileAnexo(processoTemp.getTipoFileAnexo());
				processo.setNomeFileAnexo(processoTemp.getNomeFileAnexo());
			}
		}

		try {
			// passando o sku randomica
			String skuRandom = SkuUtility.randomSku();
			processo.setSku(skuRandom);
			processoService.save(processo);
		} catch (Exception e) {
			result.rejectValue("sku", e.getMessage(), e.getMessage());
			return novo(processo);
		}

		attributes.addFlashAttribute("mensagem", "Processo cadastrado com sucesso!");
		return new ModelAndView("redirect:/processo/novo");

	}

	// Edição de doadores
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Processo processo) {
		ModelAndView mv = novo(processo);
		mv.addObject(processo);
		return mv;
	}

	// remover
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") long id) {
		processoService.removeOne(id);
		return "redirect:/processo/lista";
	}

	
	
	@RequestMapping("/processoInfo")
	public String processoInfo(@RequestParam("id") Long id, Model model) {
		Processo processo = processoService.get(id);
		model.addAttribute("processo", processo);

		return "pages/processoInfo";
	}

	@RequestMapping("/updateProcesso")
	public String updateArguido(@RequestParam("id") Long id, Model model) {
		Processo processo = processoService.get(id);
		model.addAttribute("processo", processo);

		model.addAttribute("areaProcessos", AreaProcesso.values());
		model.addAttribute("instrutores", funcionarioService.findAll());
		model.addAttribute("arguidos", arguidoRepository.findAll());

		return "pages/updateProcesso";
	}

	@RequestMapping(value = "/updateProcesso", method = RequestMethod.POST)
	public String updateArguidoPost(@ModelAttribute("processo") Processo processo, HttpServletRequest request,
			final MultipartFile file) throws IOException {

		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());

		if (file.getSize() > 0) {/* cadastrando um curriculo */
			processo.setAnexo(file.getBytes());
			processo.setTipoFileAnexo(file.getContentType());
			processo.setNomeFileAnexo(file.getOriginalFilename());

		} else {
			if (processo.getId() != null && processo.getId() > 0) {// editando

				Processo processoTemp = processoService.findById(processo.getId()).get();

				processo.setAnexo(processoTemp.getAnexo());
				processo.setTipoFileAnexo(processoTemp.getTipoFileAnexo());
				processo.setNomeFileAnexo(processoTemp.getNomeFileAnexo());
			}
		}

		processoService.save(processo);
		return "redirect:/processo/processoInfo?id=" + processo.getId();
	}

	@RequestMapping("/lista")
	public String viewHomePage(Model model) {
		// String keyword = "reebok";
		String keyword = null;
		/*
		 * if (keyword != null) { return listByPage(model, 1, "numeroProcesso", "asc",
		 * keyword); }
		 */
		return listByPage(model, 1, "numeroProcesso", "asc", keyword);

	}

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {

		Page<Processo> page = processoService.listAll(currentPage, sortField, sortDir, keyword);

		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		// int currentPage = page.previousPageable().getPageNumber();

		List<Processo> processoList = page.getContent();

		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("processoList", processoList); // next bc of thymeleaf we make the index.html

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

		return "/pages/processoLista";
	}

	// baixar anexo

	@GetMapping("**/baixarAnexo/{idProcesso}")
	public void baixarCurriculo(@PathVariable("idProcesso") Long idProcesso, HttpServletResponse response)
			throws IOException {
		Processo processo = processoService.findById(idProcesso).get();
		if (processo.getAnexo() != null) {
			response.setContentLength(processo.getAnexo().length);
			response.setContentType(processo.getTipoFileAnexo());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", processo.getNomeFileAnexo());
			response.setHeader(headerKey, headerValue);
			response.getOutputStream().write(processo.getAnexo());
		}

	}

}
