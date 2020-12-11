package com.microfocus.interApp.services;

import javax.persistence.Entity;

public interface  DataService <T extends Entity>  {

    String insertToDb(T entity);
}
