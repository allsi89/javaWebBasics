package chushkaapp.web.servlets;

import chushkaapp.domain.models.view.AllProductsView;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/products/all")
public class ProductAllServlet extends HttpServlet {

    private static final String All_PRODUCTS_HTML_FILE_PATH =
            "D:\\javaWebBasics\\chushkaApp\\src\\main\\resources\\views\\products-all.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public ProductAllServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.readHtmlFile(All_PRODUCTS_HTML_FILE_PATH)
                .replace("{{listProducts}}", this.formatListProducts());
        resp.getWriter().write(htmlFileContent);
    }

    private String formatListProducts(){
        List<AllProductsView> allProductsViews = this.productService.findAllProducts()
                .stream()
                .map(psm-> this.modelMapper.map(psm, AllProductsView.class))
                .collect(Collectors.toList());

        StringBuilder listProducts = new StringBuilder();
        allProductsViews.forEach(p->{
            listProducts.append(
                    String.format("<li><a href=\"/products/details?name=%s\">%s</li>", p.getName(), p.getName()))
                    .append(System.lineSeparator());
        });

        return listProducts.toString().trim();
    }

}
