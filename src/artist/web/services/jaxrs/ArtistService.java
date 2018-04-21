package artist.web.services.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import artist.orm.jpa.daos.ArtistDao;
import artist.orm.jpa.entities.Artist;

@Path("actor")
public class ArtistService {
	      
   ArtistDao adao = new ArtistDao();
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public List<Artist> getArtists() {
	   List<Artist>artists = new ArrayList<Artist>();
	   artists = adao.findAllArtists();
	   return artists;  
	}
   
   @Path("{aid}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public Artist getArtistById(@PathParam("aid") int aid) {
	
	   Artist artist = adao.findArtistById(aid);
	   return artist;  
	}
   	
}