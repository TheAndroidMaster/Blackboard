package james.blackboard.data.content;

public class FolderContentData extends ContentData {

    public String action;

    public FolderContentData(String title, String description, String action) {
        super(title, description);
        this.action = action;
    }

}
