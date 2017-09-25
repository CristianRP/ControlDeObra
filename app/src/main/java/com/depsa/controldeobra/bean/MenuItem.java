package com.depsa.controldeobra.bean;

/**
 * Created by Cristian Ramírez on 11/07/17.
 * Copyright (c) 2017
 */
public class MenuItem {
    private String descripcion;
    private int resourceId;

    public MenuItem(String descripcion, int resourceId) {
        this.descripcion = descripcion;
        this.resourceId = resourceId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
