package com.elcom.service.interview;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.elcom.business.manager.CompanyManager;
import com.elcom.data.interview.entity.Company;
import com.elcom.model.dto.CompanyUpsertDTO;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.service.BasedService;

@Path("/v1.0/company")
public class CompanyService extends BasedService {

    public CompanyService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    /**
     * Create company
     *
     * @param CompanyUpsertDTO
     * @return ResponseData
     * @author anhdv
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CompanyUpsertDTO req) throws Exception {

        //return this.authorize("standard", () -> {
        try (CompanyManager manager = new CompanyManager()) {

            return ok(manager.insert(req));
        }
        //});
    }

    /**
     * Update company
     *
     * @param CompanyUpsertDTO
     * @return ResponseData
     * @author anhdv
     */
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(CompanyUpsertDTO req) throws Exception {

        return this.authorize("standard", () -> {

            try (CompanyManager manager = new CompanyManager()) {

                return ok(manager.update(req));
            }
        });
    }

    /**
     * Find all company
     *
     * @param name, email, status, page, rows_per_page
     * @return ResponseData
     * @author anhdv
     */
    @GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("status") Integer status
			, @QueryParam("page") int page, @QueryParam("rows_per_page") int rowsPerPage) throws Exception {
		
		return this.authorize("standard", () -> {
			
			try (CompanyManager manager = new CompanyManager()) {
				
				ResponseDataPaging res = manager.findAll(name, email, status, page, rowsPerPage);
				
				if( res.getStatus() == Status.OK.getStatusCode() )
					return okPagingWithNull(res);
				else
					return noContent(res);
			}
		});
	}

    /**
     * Find details company
     *
     * @param id
     * @return ResponseData
     * @author anhdv
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDetails(@PathParam("id") Long id) throws Exception {

        return this.authorize("standard", () -> {

            try (CompanyManager manager = new CompanyManager()) {

                ResponseData res = manager.findDetails(id);

                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
    
    /**
     * Load danh sach cty dang gui bai test cho ung vien
     * @param userTo
     * @return
     * @throws Exception 
     */
    @GET
    @Path("/loadFromLetter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadFromLetter(@QueryParam("id") Long userTo) throws Exception {
        return this.authorize("standard", () -> {
            try (CompanyManager manager = new CompanyManager()) {
                ResponseData res = manager.loadFromLetter(userTo);
                if (res.getStatus() == Status.OK.getStatusCode()) {
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
    
    /**
     * Update company
     * @param req
     * @return
     * @throws Exception 
     */
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Company req) throws Exception {
        return this.authorize("standard", () -> {
            try (CompanyManager manager = new CompanyManager()) {
                ResponseData res = manager.update(req);
                if(res.getStatus() == Status.OK.getStatusCode()){
                    return ok(res);
                } else {
                    return noContent(res);
                }
            }
        });
    }
}
