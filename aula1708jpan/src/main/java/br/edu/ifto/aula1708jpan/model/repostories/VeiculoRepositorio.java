package br.edu.ifto.aula1708jpan.model.repostories;

import br.edu.ifto.aula1708jpan.model.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class VeiculoRepositorio{

        @PersistenceContext
        private EntityManager em ;

        public void save(Veiculo veiculo){
            em.persist(veiculo);
        }

        public Veiculo veiculoFind (Long id){
            return em.find(Veiculo.class, id);
        }

        public List<Veiculo> veiculos(){
            Query query = em.createQuery("from Veiculo");
            return query.getResultList();
        }

        public void remove(Long id){
            Veiculo v = em.find(Veiculo.class, id);
            em.remove(v);
        }

        public void update(Veiculo veiculo){
            em.merge(veiculo);
        }
}
