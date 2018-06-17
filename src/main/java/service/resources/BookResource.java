package service.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import models.BookModel;

@Path("book")
@Singleton
public class BookResource {
    private final ArrayList<BookModel> bookArrayList = new ArrayList<>();
    private BookModel bookModel;
    ArrayList<String> authors = new ArrayList<>();

    public BookResource() {
        authors.add("test");
        bookArrayList.add(new BookModel("Test", "Horror", 1924, authors, "Stefan", "123141415"));
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBookArrayList(){
        GenericEntity<ArrayList<BookModel>> arrayListGenericEntityBook = new GenericEntity<ArrayList<BookModel>>(bookArrayList){};
        return Response.ok(arrayListGenericEntityBook).build();
    }

}
