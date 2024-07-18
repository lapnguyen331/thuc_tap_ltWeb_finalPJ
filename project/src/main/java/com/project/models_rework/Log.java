package com.project.models_rework;

public class Log {
    private int id;
    private int level;
    private String ip;

    public Log() {
    }

    public Log(int id, int level, String ip, String national, String previous, String current) {
        this.id = id;
        this.level = level;
        this.ip = ip;
        this.national = national;
        this.previous = previous;
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    private String national;
    private String previous;
    private String current;


}
