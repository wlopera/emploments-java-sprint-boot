package com.wlopera.employments.service;

import java.util.List;

import com.wlopera.employments.model.Vacant;

public interface IVacantService {
	List<Vacant> getAll();
	Vacant getVacantById(Integer idVacant);
	void save(Vacant vacant);
}
