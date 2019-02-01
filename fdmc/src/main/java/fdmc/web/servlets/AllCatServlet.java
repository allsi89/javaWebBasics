package fdmc.web.servlets;

import fdmc.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//configured in web.xml
public class AllCatServlet extends HttpServlet {
    private static final String ALL_CATS_HTML_FILE_PATH = "D:\\javaWebBasics\\fdmc\\src\\main\\resources\\views\\all-cats.html";
    private static final String NO_CATS_HTML_FILE_PATH = "D:\\javaWebBasics\\fdmc\\src\\main\\resources\\views\\no-cats.html";

    private final HtmlReader htmlReader;

    @Inject
    public AllCatServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String htmlFileContent;
        if (req.getSession().getAttribute("cats") == null){
            htmlFileContent = this.htmlReader.readHtmlFile(NO_CATS_HTML_FILE_PATH);
        }else {
            List<Cat> cats = new ArrayList<>(((Map<String, Cat>) req.getSession().getAttribute("cats"))
                    .values());
            htmlFileContent = this.htmlReader.readHtmlFile(ALL_CATS_HTML_FILE_PATH);
            StringBuilder catList = new StringBuilder();
            for (Cat cat : cats) {
                catList.append(String.format("<p><a href=\"/cats/profile?catName=%s\">%s</a><p/>", cat.getName(), cat.getName()));
            }
            htmlFileContent = htmlFileContent.replace("{{allCats}}", catList.toString());
        }
        res.getWriter().println(htmlFileContent);
    }
}
