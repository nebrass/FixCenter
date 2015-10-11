package com.targa.dev.fixcenter.business.boundary;

import com.targa.dev.fixcenter.business.entity.Customer;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Nebrass Lamouchi <lnibrass at gmail.com>
 */
@Path("customers")
public class CustomersResource {

    private static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    CustomerManager cm;

    @POST
    public Response save(@Valid Customer customer, @Context UriInfo info) {
        Customer saved = this.cm.save(customer);
        long id = saved.getId();
        URI build = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(build).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customer> findAll() {
        return this.cm.all();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        if (cm.findById(id) != null) {
            this.cm.delete(id);
            return Response.ok().build();
        } else {
            return Response.notModified().status(404).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") long id, JsonObject update) throws ParseException {
        Customer customer = cm.findById(id);
        if (customer != null) {

            customer.setName(update.getString("name"));
            customer.setFamilyName(update.getString("familyName"));
            customer.setBirthDate(format.parse(update.getString("birthDate")));
            customer.setDescription(update.getString("description"));
            
            cm.save(customer);
            return Response.ok().build();
        } else {
            return Response.notModified().status(404).build();
        }
    }

}
