package com.wlopera.employments.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wlopera.employments.model.Vacant;
import com.wlopera.employments.service.IVacantService;
import com.wlopera.employments.util.Utilities;

@Controller
@RequestMapping("/vacant")
public class VacantController {

	@Autowired
	private IVacantService vacantService;

	@ModelAttribute("vacant")
	public Vacant prepareModel() {
		return new Vacant();
	}

	@GetMapping("/create")
	public String create(Model model) {
		return "vacant/formVacant";
	}

	@PostMapping("/save")
	public String save(Vacant vacant, BindingResult result, @RequestParam("fileImage") MultipartFile multipart) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "vacant/formVacant";
		}
		if(!multipart.isEmpty()) {
			String path = "c:/temp/empleos/img-vacantes/";
			String filename = Utilities.saveFile(multipart, path);
			if (filename != null) {
				vacant.setImage(filename);
			}
		}
		System.out.println("Vacante: " + vacant);
		vacantService.save(vacant);
		return "vacant/listVacant";
	}

	@GetMapping("/view/{id}")
	public String getDetails(@PathVariable("id") int idVacant, Model model) {

		Vacant vacant = vacantService.getVacantById(idVacant);
		System.out.println("Vacante: " + vacant);
		model.addAttribute("vacant", vacant);

		return "detail";
	}

	@GetMapping("/delete")
	public String deleteVacant(@RequestParam("id") int idVacant, Model model) {
		System.out.println("Borrando vacante con id: " + idVacant);
		model.addAttribute("id", idVacant);

		return "message";

	}

	@InitBinder
	public void initBinder(WebDataBinder webdataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webdataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
