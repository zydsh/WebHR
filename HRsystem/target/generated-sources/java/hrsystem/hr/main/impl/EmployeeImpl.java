package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Employee;
import hrsystem.hr.main.Employee_Leave;
import hrsystem.hr.main.Employee_LeaveSet;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.impl.EmployeeImpl;
import hrsystem.hr.main.impl.Employee_LeaveSetImpl;
import hrsystem.hr.main.impl.Employee_PaySetImpl;

import io.ciera.runtime.instanceloading.AttributeChangedDelta;
import io.ciera.runtime.instanceloading.InstanceCreatedDelta;
import io.ciera.runtime.summit.application.ActionHome;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IInstanceIdentifier;
import io.ciera.runtime.summit.classes.InstanceIdentifier;
import io.ciera.runtime.summit.classes.ModelInstance;
import io.ciera.runtime.summit.exceptions.EmptyInstanceException;
import io.ciera.runtime.summit.exceptions.InstancePopulationException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.statemachine.Event;
import io.ciera.runtime.summit.statemachine.IEvent;
import io.ciera.runtime.summit.types.Date;
import io.ciera.runtime.summit.types.IWhere;
import io.ciera.runtime.summit.types.IXtumlType;
import io.ciera.runtime.summit.types.StringUtil;
import io.ciera.runtime.summit.types.UniqueId;


public class EmployeeImpl extends ModelInstance<Employee,Hr> implements Employee {

    public static final String KEY_LETTERS = "EM";
    public static final Employee EMPTY_EMPLOYEE = new EmptyEmployee();

    private Hr context;

    // constructors
    private EmployeeImpl( Hr context ) {
        this.context = context;
        m_FName = "";
        m_National_ID = 0;
        m_LName = "";
        m_Start_Date = new Date();
        R1_Employee_Leave_set = new Employee_LeaveSetImpl();
        R2_recieves_Employee_Pay_set = new Employee_PaySetImpl();
        statemachine = new EmployeeStateMachine(this, context());
    }

    private EmployeeImpl( Hr context, UniqueId instanceId, String m_FName, int m_National_ID, String m_LName, Date m_Start_Date, int initialState ) {
        super(instanceId);
        this.context = context;
        this.m_FName = m_FName;
        this.m_National_ID = m_National_ID;
        this.m_LName = m_LName;
        this.m_Start_Date = m_Start_Date;
        R1_Employee_Leave_set = new Employee_LeaveSetImpl();
        R2_recieves_Employee_Pay_set = new Employee_PaySetImpl();
        statemachine = new EmployeeStateMachine(this, context(), initialState);
    }

    public static Employee create( Hr context ) throws XtumlException {
        Employee newEmployee = new EmployeeImpl( context );
        if ( context.addInstance( newEmployee ) ) {
            newEmployee.getRunContext().addChange(new InstanceCreatedDelta(newEmployee, KEY_LETTERS));
            return newEmployee;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Employee create( Hr context, UniqueId instanceId, String m_FName, int m_National_ID, String m_LName, Date m_Start_Date, int initialState ) throws XtumlException {
        Employee newEmployee = new EmployeeImpl( context, instanceId, m_FName, m_National_ID, m_LName, m_Start_Date, initialState );
        if ( context.addInstance( newEmployee ) ) {
            return newEmployee;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    private final EmployeeStateMachine statemachine;
    
    @Override
    public void accept(IEvent event) throws XtumlException {
        statemachine.transition(event);
    }

    @Override
    public int getCurrentState() {
        return statemachine.getCurrentState();
    }


    // attributes
    private String m_FName;
    @Override
    public String getFName() throws XtumlException {
        checkLiving();
                return m_FName;
    }
    @Override
    public void setFName( String m_FName ) throws XtumlException {
        checkLiving();
        if ( StringUtil.inequality( m_FName, this.m_FName ) ) {
            final String oldValue = this.m_FName;
            this.m_FName = m_FName;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_FName", oldValue, this.m_FName));
        }
    }
    private int m_National_ID;
    @Override
    public int getNational_ID() throws XtumlException {
        checkLiving();
                return m_National_ID;
    }
    @Override
    public void setNational_ID( int m_National_ID ) throws XtumlException {
        checkLiving();
        if ( m_National_ID != this.m_National_ID ) {
            final int oldValue = this.m_National_ID;
            this.m_National_ID = m_National_ID;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_National_ID", oldValue, this.m_National_ID));
            if ( !R1_Employee_Leave().isEmpty() ) R1_Employee_Leave().setNational_ID( m_National_ID );
        }
    }
    private String m_LName;
    @Override
    public String getLName() throws XtumlException {
        checkLiving();
                return m_LName;
    }
    @Override
    public void setLName( String m_LName ) throws XtumlException {
        checkLiving();
        if ( StringUtil.inequality( m_LName, this.m_LName ) ) {
            final String oldValue = this.m_LName;
            this.m_LName = m_LName;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_LName", oldValue, this.m_LName));
        }
    }
    private Date m_Start_Date;
    @Override
    public Date getStart_Date() throws XtumlException {
        checkLiving();
                return m_Start_Date;
    }
    @Override
    public void setStart_Date( Date m_Start_Date ) throws XtumlException {
        checkLiving();
        if ( m_Start_Date.inequality( this.m_Start_Date ) ) {
            final Date oldValue = this.m_Start_Date;
            this.m_Start_Date = m_Start_Date;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_Start_Date", oldValue, this.m_Start_Date));
        }
    }


    // instance identifiers
    @Override
    public IInstanceIdentifier getId1() {
        try {
            return new InstanceIdentifier(getNational_ID());
        }
        catch ( XtumlException e ) {
            getRunContext().getLog().error(e);
            System.exit(1);
            return null;
        }
    }

    // operations


    // static operations
    public static class CLASS extends ActionHome<Hr> {

        public CLASS( Hr context ) {
            super( context );
        }

        public void createEmployee( final String p_FName,  final String p_LName,  final int p_National_ID ) throws XtumlException {
            Employee employee = EmployeeImpl.create( context() );
            employee.setFName( p_FName );
            employee.setLName( p_LName );
            employee.setNational_ID( p_National_ID );
        }



    }


    // events
    public static class commencedRcvd extends Event {
        public commencedRcvd(IRunContext runContext, int populationId) {
            super(runContext, populationId);
        }
        @Override
        public int getId() {
            return 3;
        }
        @Override
        public String getClassName() {
            return "Employee";
        }
    }
    public static class employeeUpdateRcvd extends Event {
        public employeeUpdateRcvd(IRunContext runContext, int populationId,  final String p_FName,  final String p_LName ) {
            super(runContext, populationId, new Object[]{p_FName,  p_LName});
        }
        @Override
        public int getId() {
            return 2;
        }
        @Override
        public String getClassName() {
            return "Employee";
        }
    }
    public static class requestLeave extends Event {
        public requestLeave(IRunContext runContext, int populationId,  final String p_Starting,  final String p_Ending,  final int p_National_ID,  final String p_Name ) {
            super(runContext, populationId, new Object[]{p_Starting,  p_Ending,  p_National_ID,  p_Name});
        }
        @Override
        public int getId() {
            return 1;
        }
        @Override
        public String getClassName() {
            return "Employee";
        }
    }
    public static class returnFromLeave extends Event {
        public returnFromLeave(IRunContext runContext, int populationId) {
            super(runContext, populationId);
        }
        @Override
        public int getId() {
            return 0;
        }
        @Override
        public String getClassName() {
            return "Employee";
        }
    }


    // selections
    private Employee_LeaveSet R1_Employee_Leave_set;
    @Override
    public void addR1_Employee_Leave( Employee_Leave inst ) {
        R1_Employee_Leave_set.add(inst);
    }
    @Override
    public void removeR1_Employee_Leave( Employee_Leave inst ) {
        R1_Employee_Leave_set.remove(inst);
    }
    @Override
    public Employee_LeaveSet R1_Employee_Leave() throws XtumlException {
        return R1_Employee_Leave_set;
    }
    private Employee_PaySet R2_recieves_Employee_Pay_set;
    @Override
    public void addR2_recieves_Employee_Pay( Employee_Pay inst ) {
        R2_recieves_Employee_Pay_set.add(inst);
    }
    @Override
    public void removeR2_recieves_Employee_Pay( Employee_Pay inst ) {
        R2_recieves_Employee_Pay_set.remove(inst);
    }
    @Override
    public Employee_PaySet R2_recieves_Employee_Pay() throws XtumlException {
        return R2_recieves_Employee_Pay_set;
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
    public Employee self() {
        return this;
    }

    @Override
    public Employee oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_EMPLOYEE;
    }

}

class EmptyEmployee extends ModelInstance<Employee,Hr> implements Employee {

    // attributes
    public String getFName() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }
    public void setFName( String m_FName ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }
    public int getNational_ID() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }
    public void setNational_ID( int m_National_ID ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }
    public String getLName() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }
    public void setLName( String m_LName ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }
    public Date getStart_Date() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }
    public void setStart_Date( Date m_Start_Date ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }


    // operations


    // selections
    @Override
    public Employee_LeaveSet R1_Employee_Leave() {
        return (new Employee_LeaveSetImpl());
    }
    @Override
    public Employee_PaySet R2_recieves_Employee_Pay() {
        return (new Employee_PaySetImpl());
    }


    @Override
    public String getKeyLetters() {
        return EmployeeImpl.KEY_LETTERS;
    }

    @Override
    public Employee self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Employee oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return EmployeeImpl.EMPTY_EMPLOYEE;
    }

}
