package model;

import java.util.Date;

public class Notification {
    private Document document;
    private Date dateModifed;
    private Editor editor;

    public Notification(Document document, Date dateModifed, Editor editor)
    {
        this.dateModifed = dateModifed;
        this.document = document;
        this.editor =editor;
    }

    public Document getDocument() {
        return document;
    }

    public Date getDateModifed() {
        return dateModifed;
    }

    public Editor getEditor() {
        return editor;
    }
}
