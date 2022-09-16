package com.wlopera.employments.controller;

//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wlopera.employments.model.Vacant;
import com.wlopera.employments.service.IVacantService;

@Controller
public class HomeController {

	@Autowired
	private IVacantService servicesvacant;

	@GetMapping("/")
	public String showHome(Model model) {
		List<Vacant> vacants = servicesvacant.getAll();
		model.addAttribute("vacants", vacants);
		
		return "home";
	}

	@GetMapping("/list")
	public String getListEmployments(Model model) {
		List<String> list = new LinkedList<>();

		list.add("Ingeniero de Sistemas");
		list.add("Auxiliar de Contabilidad");
		list.add("Vendedor");
		list.add("Arquitecto");

		model.addAttribute("employments", list);

		return "list";
	}

	@GetMapping("/detail")
	public String getVacantDetail(Model model) {
		Vacant vacant = new Vacant();

		vacant.setName("Ingeniero de comunicaciones");
		vacant.setDescription("Se solicita Ingeniero para dar soporte a Intranet");
		vacant.setDate(new Date());
		vacant.setSalary(9700.0);

		model.addAttribute("vacant", vacant);

		return "/detail";

	}

	@GetMapping("table")
	public String getTableData(Model model) {
		List<Vacant> data = servicesvacant.getAll();

		model.addAttribute("data", data);

		return "table";
	}

}
