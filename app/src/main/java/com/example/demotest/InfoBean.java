package com.example.demotest;

import java.util.List;

class Data
{
    private String id;
    private String name;
    private String picSmall;
    private String picBig;
    private String description;
    private String learner;

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPicSmall()
    {
        return picSmall;
    }

    public String getPicBig()
    {
        return picBig;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLearner()
    {
        return learner;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPicSmall(String picSmall)
    {
        this.picSmall = picSmall;
    }

    public void setPicBig(String picBig)
    {
        this.picBig = picBig;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setLearner(String learner)
    {
        this.learner = learner;
    }

    @Override
    public String toString()
    {
        return "Data{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", picSmall='" + picSmall + '\'' + ", picBig='" + picBig + '\'' + ", description='" + description + '\'' + ", learner='" + learner + '\'' + '}';
    }
}
public class InfoBean
{
    private String status;
    private List<Data> data;
    private String msg;

    public String getStatus()
    {
        return status;
    }

    public List<Data> getData()
    {
        return data;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setData(List<Data> data)
    {
        this.data = data;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "InfoBean{" + "status='" + status + '\'' + ", data=" + data + ", msg='" + msg + '\'' + '}';
    }
}
