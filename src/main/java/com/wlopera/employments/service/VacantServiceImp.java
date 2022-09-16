package com.wlopera.employments.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wlopera.employments.model.Vacant;

@Service
public class VacantServiceImp implements IVacantService {
	private List<Vacant> vacants;

	public VacantServiceImp() {
		vacants = getVacants();
	}

	@Override
	public List<Vacant> getAll() {
		return vacants;
	}

	/**
	 * Metodo que retorna una lista de vacantes
	 * 
	 * @return
	 */
	private List<Vacant> getVacants() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacant> vacants = new LinkedList<>();
		Vacant vacant = null;

		try {
			vacant = new Vacant();
			vacant.setId(1);
			vacant.setName("Ingeniero Civil");
			vacant.setDescription("Solicitamos Ing. Civil para diseñar puente peatonal.");
			vacant.setDate(sdf.parse("08-02-2022"));
			vacant.setSalary(14000.0);
			vacant.setOutstanding(1);
			vacant.setImage("logo1.png");
			vacant.setStatus("Aprobada");
			vacants.add(vacant);

			vacant = new Vacant();
			vacant.setId(2);
			vacant.setName("Contador Público");
			vacant.setDescription("Emprea Importadora solicta contador con 5 años de experiencia titulado.");
			vacant.setDate(sdf.parse("09-02-2022"));
			vacant.setSalary(12000.0);
			vacant.setOutstanding(0);
			vacant.setImage("logo3.png");
			vacant.setStatus("Creada");
			vacants.add(vacant);

			vacant = new Vacant();
			vacant.setId(3);
			vacant.setName("Ingeniero Eléctricista");
			vacant.setDescription(
					"Emprea Internacional solicita Ing. Eléctricta para mantenimiento de una instalación eléctrica.");
			vacant.setDate(sdf.parse("08-02-2019"));
			vacant.setSalary(10500.0);
			vacant.setOutstanding(0);
			vacant.setStatus("Aprobada");
			vacants.add(vacant);

			vacant = new Vacant();
			vacant.setId(4);
			vacant.setName("Diseñador Gráfico");
			vacant.setDescription("Necesitamos Diseñador Gráfico titulado para un diseño publicitario de una empresa");
			vacant.setDate(sdf.parse("11-02-2019"));
			vacant.setSalary(7500.0);
			vacant.setOutstanding(1);
			vacant.setImage("logo4.png");
			vacant.setStatus("Eliminada");
			vacants.add(vacant);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vacants;
	}

	@Override
	public Vacant getVacantById(Integer idVacant) {
		List<Vacant> vacants = getVacants();

		Vacant vacant = null;

		for (Vacant data : vacants) {
			if (data.getId() == idVacant) {
				vacant = data;
				break;
			}
		}

		return vacant;
	}

	@Override
	public void save(Vacant vacant) {
		vacants.add(vacant);
	}

	@Override
	public void delete(Integer id) {
		for (Vacant vacant : vacants) {
			if (vacant.getId() == id) {
				vacants.remove(vacant);
				break;
			}
		}
	}

}
