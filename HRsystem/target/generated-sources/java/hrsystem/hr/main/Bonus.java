package hrsystem.hr.main;


import hrsystem.Hr;
import hrsystem.hr.main.Pay;

import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Bonus extends IModelInstance<Bonus,Hr> {

    // attributes


    // operations


    // selections
    default public void setR4_is_a_Pay( Pay inst ) {}
    public Pay R4_is_a_Pay() throws XtumlException;


}
