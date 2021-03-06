package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Artwork;



public class ArtworkHelper {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("GalleryGoehring");
		
		public void insertArtwork(Artwork toAdd) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(toAdd);
			em.getTransaction().commit();
			em.close();
		}
		
		public List<Artwork> showAllArtwork() {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			TypedQuery<Artwork> allResults = em.createQuery("select a from Artwork a", Artwork.class);
			List<Artwork> allArtwork = allResults.getResultList();
			em.close();
			return allArtwork;
		}
		
		public void deleteArtwork(Artwork toDelete) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Artwork> typedQuery = em.createQuery("select a from Artwork a where a.title = :selectedTitle and a.artist = :selectedArtist", Artwork.class);
			typedQuery.setParameter("selectedTitle", toDelete.getTitle());
			typedQuery.setParameter("selectedArtist", toDelete.getArtist());
			typedQuery.setMaxResults(1);
			Artwork result = typedQuery.getSingleResult();
			em.remove(result);
			em.getTransaction().commit();
			em.close();
			
		}

		public Artwork searchForArtworkById(int idToEdit) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			Artwork foundArtwork = em.find(Artwork.class, idToEdit);
			em.close();
			return foundArtwork;
		}

		public void updateArtwork(Artwork toEdit) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.merge(toEdit);
			em.getTransaction().commit();
			em.close();
			
		}
}
