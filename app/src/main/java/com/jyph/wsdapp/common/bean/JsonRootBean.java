/**
  * Copyright 2017 bejson.com 
  */
package com.jyph.wsdapp.common.bean;
import java.util.List;

/**
 * Auto-generated: 2017-08-04 19:14:44
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private List<Results> results;
    private String status;
    public void setResults(List<Results> results) {
         this.results = results;
     }
     public List<Results> getResults() {
         return results;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

}