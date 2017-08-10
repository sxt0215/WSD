/**
  * Copyright 2017 bejson.com 
  */
package com.jyph.wsdapp.common.bean;

/**
 * Auto-generated: 2017-08-04 19:14:44
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Geometry {

    private Location location;
    private String location_type;
    private Viewport viewport;
    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setLocation_type(String location_type) {
         this.location_type = location_type;
     }
     public String getLocation_type() {
         return location_type;
     }

    public void setViewport(Viewport viewport) {
         this.viewport = viewport;
     }
     public Viewport getViewport() {
         return viewport;
     }

}