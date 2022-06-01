package br.com.fiap.progamer.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.progamer.dao.VisitanteDao;
import br.com.fiap.progamer.model.Visitante;
import br.com.fiap.progamer.service.UploadService;

@Named
@RequestScoped
public class VisitanteBean {
	
	private Visitante visitante = new Visitante();
	
	@Inject
	private VisitanteDao dao;
	
	private UploadedFile image;
	
	public String save() {
		System.out.println(visitante);
		
		String path = UploadService.write(image, "visitante");
		visitante.setImagePath(path);
		
		dao.create(visitante);
		
		FacesMessage facesMessage = new FacesMessage("Visitante cadastrado com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
		return "visitantes";
	}
	
	public List<Visitante> list() {
		return dao.listAll();
	}

	public Visitante getVisitante() {
		return visitante;
	}

	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}
}
