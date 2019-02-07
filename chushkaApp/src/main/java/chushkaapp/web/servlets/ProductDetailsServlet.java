package chushkaapp.web.servlets;

import chushkaapp.domain.models.service.ProductServiceModel;
import chushkaapp.service.ProductService;
import chushkaapp.util.HtmlReader;
import chushkaapp.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    public static final String PRODUCT_DETAILS_HTML_FILE_PATH =
            "D:\\javaWebBasics\\chushkaApp\\src\\main\\resources\\views\\details-product.html";


    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final HtmlReader htmlReader;

    @Inject
    public ProductDetailsServlet(ProductService productService, ModelMapper modelMapper, HtmlReader htmlReader) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getQueryString().split("=")[1];
        ProductServiceModel productServiceModel = this.productService.getProductServiceModelByName(productName);
        String htmlContent = this.htmlReader.readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH);
        if (productServiceModel != null) {
            htmlContent = htmlContent
                    .replace("{{name}}", productServiceModel.getName())
                    .replace("{{description}}", productServiceModel.getDescription())
                    .replace("{{type}}", productServiceModel.getType().toUpperCase());

        }
        resp.getWriter().println(htmlContent);
    }


}
