package model;

import java.util.Date;

public class Document {
    private Integer id;
    private Date date;
    private ClassObject classObject;
    public Document(Integer id, Date date, ClassObject classObject)
    {
        this.id=id;
        this.date=date;
        this.classObject=classObject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }
}
