package exam.domain.models.view;

public class DocumentViewModel {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        if (title.length() < 12) {
            return title + "...";
        }
        return title.substring(0, 12) + "...";
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
