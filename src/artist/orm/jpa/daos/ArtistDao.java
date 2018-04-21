package artist.orm.jpa.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import artist.orm.jpa.entities.Artist;

public class ArtistDao {
	
	private static final String UNIT = "Artist360";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIT);

	public int createArtist(Artist artist) {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(artist);
		em.flush();

		em.getTransaction().commit();
		em.close();
		return artist.getId();

	}
	
	public int deleteArtist() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		int deletedCount = em.createQuery("DELETE FROM Artist").executeUpdate();
		em.getTransaction().commit();
		em.close();
		
		return deletedCount;
	}
	
	public void deleteArtistByName(String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Artist A WHERE A.name =:name");
		query.setParameter("name", name);
		
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();	
	}
	
	public Artist findArtistById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Artist artist = em.find(Artist.class, id);
		
		em.getTransaction().commit();
		em.close();
		return artist;
	}
	
	public void renameArtist(int id, String newName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Artist artist = em.find(Artist.class, id);
		
		artist.setName(newName);
		em.getTransaction().commit();
	}
	
	public List<Artist> findAllArtists() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("SELECT a FROM Artist a");
		@SuppressWarnings("unchecked")
		List<Artist> artists = (List<Artist>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return artists;
	}
	
	public void displayAllArtists() {
		List<Artist>artists = findAllArtists();
		for(Artist a:artists) {
			System.out.println(a.getName());
		}
	}
	
	public void test() {
		
		// 1. Remove all actors
		deleteArtist();
		
		// 2. Create actor
		Artist artist = new Artist(1, "Riya", "Riya@gmail.com", "Riya123", "Riya", null, null, 0);
		createArtist(artist);
		
		artist = new Artist(2, "Viha", "Viha@gmail.com", "Viha123", "Viha", null, null, 0);
		createArtist(artist);
		
		artist = new Artist(2, "Ruhanaa", "Ruha@gmail.com", "Ruhanaa123", "Ruhanaa", null, null, 0);
		createArtist(artist);
		
		// 4. Retrieve all actors and print to console
		displayAllArtists();
		
	}

}
