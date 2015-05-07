package br.senai.sc.facadeImp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import br.senai.sc.Util.JpaUtil;
import br.senai.sc.facade.AlunoFacade;
import br.senai.sc.model.Aluno;
import br.senai.sc.model.Unidade_Curricular;
import br.senai.sc.model.Usuario;

@Stateless
public class Aluno_FacadeImp implements AlunoFacade {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void deletar(Aluno aluno) {
		// a dele��o ser� l�gica
	}

	@Override
	public Aluno find(int entityID) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Aluno.class));
		return em.createQuery(cq).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listaRetornaAlunoID(Integer id) {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Aluno.class)).where(em.getCriteriaBuilder().equal(cq.from(Aluno.class).get("id"),id));
		return em.createQuery(cq).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Aluno validaExistenciaAluno(Integer id){
		return (Aluno) em.createQuery("SELECT a FROM Usuario u JOIN u.aluno a WHERE u.id = :id").setParameter("id", id).getSingleResult();
		
	}

	@Override
	public void salvar(Aluno aluno) {
		try {
		em.merge(aluno);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
