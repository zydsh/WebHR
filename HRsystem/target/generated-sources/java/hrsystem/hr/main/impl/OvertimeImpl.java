package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Overtime;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.impl.PayImpl;

import io.ciera.runtime.instanceloading.InstanceCreatedDelta;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IInstanceIdentifier;
import io.ciera.runtime.summit.classes.InstanceIdentifier;
import io.ciera.runtime.summit.classes.ModelInstance;
import io.ciera.runtime.summit.exceptions.InstancePopulationException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.types.IWhere;
import io.ciera.runtime.summit.types.IXtumlType;
import io.ciera.runtime.summit.types.UniqueId;


public class OvertimeImpl extends ModelInstance<Overtime,Hr> implements Overtime {

    public static final String KEY_LETTERS = "Overtime";
    public static final Overtime EMPTY_OVERTIME = new EmptyOvertime();

    private Hr context;

    // constructors
    private OvertimeImpl( Hr context ) {
        this.context = context;
        R4_is_a_Pay_inst = PayImpl.EMPTY_PAY;
    }

    private OvertimeImpl( Hr context, UniqueId instanceId ) {
        super(instanceId);
        this.context = context;
        R4_is_a_Pay_inst = PayImpl.EMPTY_PAY;
    }

    public static Overtime create( Hr context ) throws XtumlException {
        Overtime newOvertime = new OvertimeImpl( context );
        if ( context.addInstance( newOvertime ) ) {
            newOvertime.getRunContext().addChange(new InstanceCreatedDelta(newOvertime, KEY_LETTERS));
            return newOvertime;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Overtime create( Hr context, UniqueId instanceId ) throws XtumlException {
        Overtime newOvertime = new OvertimeImpl( context, instanceId );
        if ( context.addInstance( newOvertime ) ) {
            return newOvertime;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }



    // attributes


    // instance identifiers

    // operations


    // static operations


    // events


    // selections
    private Pay R4_is_a_Pay_inst;
    @Override
    public void setR4_is_a_Pay( Pay inst ) {
        R4_is_a_Pay_inst = inst;
    }
    @Override
    public Pay R4_is_a_Pay() throws XtumlException {
        return R4_is_a_Pay_inst;
    }


    @Override
    public IRunContext getRunContext() {
        return context().getRunContext();
    }

    @Override
    public Hr context() {
        return context;
    }

    @Override
    public String getKeyLetters() {
        return KEY_LETTERS;
    }

    @Override
    public Overtime self() {
        return this;
    }

    @Override
    public Overtime oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_OVERTIME;
    }

}

class EmptyOvertime extends ModelInstance<Overtime,Hr> implements Overtime {

    // attributes


    // operations


    // selections
    @Override
    public Pay R4_is_a_Pay() {
        return PayImpl.EMPTY_PAY;
    }


    @Override
    public String getKeyLetters() {
        return OvertimeImpl.KEY_LETTERS;
    }

    @Override
    public Overtime self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Overtime oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return OvertimeImpl.EMPTY_OVERTIME;
    }

}
