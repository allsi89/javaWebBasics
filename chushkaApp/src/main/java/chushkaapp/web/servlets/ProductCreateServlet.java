package chushkaapp.web.servlets;

import chushkaapp.domain.models.service.ProductServiceModel;
import chushkaapp.service.ProductService;
import chushkaapp.util.HtmlReader;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {

    private static final String CREATE_PRODUCT_HTML_FILE_PATH =
            "D:\\javaWebBasics\\chushkaApp\\src\\main\\resources\\views\\create-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader
                .readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH);
        resp.getWriter().write(htmlFileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        ProductServiceModel productServiceModel = null;
        try{
           productServiceModel = this.productService.getProductServiceModelByName(name);
        } catch (NoResultException e){
            productServiceModel = new ProductServiceModel();
            productServiceModel.setName(req.getParameter("name"));
            productServiceModel.setDescription(req.getParameter("description"));
            productServiceModel.setType(req.getParameter("type"));
            this.productService.saveProduct(productServiceModel);
        }
        resp.sendRedirect("/products/all");
    }


}
