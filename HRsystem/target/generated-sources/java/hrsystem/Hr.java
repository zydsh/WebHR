package hrsystem;


import hrsystem.hr.HrUI;
import hrsystem.hr.HrUI_Leave;
import hrsystem.hr.main.Bonus;
import hrsystem.hr.main.BonusSet;
import hrsystem.hr.main.Employee;
import hrsystem.hr.main.EmployeeSet;
import hrsystem.hr.main.Employee_Leave;
import hrsystem.hr.main.Employee_LeaveSet;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.Grade;
import hrsystem.hr.main.GradeSet;
import hrsystem.hr.main.Leave;
import hrsystem.hr.main.LeaveSet;
import hrsystem.hr.main.Overtime;
import hrsystem.hr.main.OvertimeSet;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.Payment;
import hrsystem.hr.main.PaymentSet;
import hrsystem.hr.main.impl.BonusImpl;
import hrsystem.hr.main.impl.BonusSetImpl;
import hrsystem.hr.main.impl.EmployeeImpl;
import hrsystem.hr.main.impl.EmployeeSetImpl;
import hrsystem.hr.main.impl.Employee_LeaveImpl;
import hrsystem.hr.main.impl.Employee_LeaveSetImpl;
import hrsystem.hr.main.impl.Employee_PayImpl;
import hrsystem.hr.main.impl.Employee_PaySetImpl;
import hrsystem.hr.main.impl.GradeImpl;
import hrsystem.hr.main.impl.GradeSetImpl;
import hrsystem.hr.main.impl.LeaveImpl;
import hrsystem.hr.main.impl.LeaveSetImpl;
import hrsystem.hr.main.impl.OvertimeImpl;
import hrsystem.hr.main.impl.OvertimeSetImpl;
import hrsystem.hr.main.impl.PayImpl;
import hrsystem.hr.main.impl.PaySetImpl;
import hrsystem.hr.main.impl.PaymentImpl;
import hrsystem.hr.main.impl.PaymentSetImpl;

import io.ciera.runtime.summit.application.IApplication;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.classes.IRelationshipSet;
import io.ciera.runtime.summit.classes.Relationship;
import io.ciera.runtime.summit.classes.RelationshipSet;
import io.ciera.runtime.summit.components.Component;
import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.EmptyInstanceException;
import io.ciera.runtime.summit.exceptions.ModelIntegrityException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.util.LOG;
import io.ciera.runtime.summit.util.TIM;
import io.ciera.runtime.summit.util.impl.LOGImpl;
import io.ciera.runtime.summit.util.impl.TIMImpl;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;


public class Hr extends Component<Hr> {

    private Map<String, Class<?>> classDirectory;

    public Hr(IApplication app, IRunContext runContext, int populationId) {
        super(app, runContext, populationId);
        Bonus_extent = new BonusSetImpl();
        Employee_extent = new EmployeeSetImpl();
        Employee_Leave_extent = new Employee_LeaveSetImpl();
        Employee_Pay_extent = new Employee_PaySetImpl();
        Grade_extent = new GradeSetImpl();
        Leave_extent = new LeaveSetImpl();
        Overtime_extent = new OvertimeSetImpl();
        Pay_extent = new PaySetImpl();
        Payment_extent = new PaymentSetImpl();
        R1_Employee_Leave_Employee_extent = new RelationshipSet();
        R1_Employee_Leave_Leave_extent = new RelationshipSet();
        R2_Employee_Pay_earned_Employee_extent = new RelationshipSet();
        R2_Employee_Pay_recieves_Pay_extent = new RelationshipSet();
        R3_Payment_Employee_Pay_extent = new RelationshipSet();
        R4_Bonus_is_a_Pay_extent = new RelationshipSet();
        R4_Grade_is_a_Pay_extent = new RelationshipSet();
        R4_Overtime_is_a_Pay_extent = new RelationshipSet();
        TIM = null;
        LOG = null;
        classDirectory = new TreeMap<>();
        classDirectory.put("Bonus", BonusImpl.class);
        classDirectory.put("EM", EmployeeImpl.class);
        classDirectory.put("Employee_Leave", Employee_LeaveImpl.class);
        classDirectory.put("Employee_Pay", Employee_PayImpl.class);
        classDirectory.put("Grade", GradeImpl.class);
        classDirectory.put("Leave", LeaveImpl.class);
        classDirectory.put("Overtime", OvertimeImpl.class);
        classDirectory.put("Pay", PayImpl.class);
        classDirectory.put("Payment", PaymentImpl.class);
    }

    // domain functions


    // relates and unrelates
    public void relate_R1_Employee_Leave_Employee( Employee_Leave form, Employee part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R1_Employee_Leave_Employee_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.addR1_Employee_Leave(form);
            form.setR1_Employee(part);
            form.setNational_ID( part.getNational_ID() );
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R1_Employee_Leave_Employee( Employee_Leave form, Employee part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R1_Employee_Leave_Employee_extent.remove( R1_Employee_Leave_Employee_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.removeR1_Employee_Leave(form);
            form.setR1_Employee(EmployeeImpl.EMPTY_EMPLOYEE);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R1_Employee_Leave_Leave( Employee_Leave form, Leave part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R1_Employee_Leave_Leave_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.addR1_Employee_Leave(form);
            form.setR1_Leave(part);
            form.setLeave_ID( part.getLeave_ID() );
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R1_Employee_Leave_Leave( Employee_Leave form, Leave part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R1_Employee_Leave_Leave_extent.remove( R1_Employee_Leave_Leave_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.removeR1_Employee_Leave(form);
            form.setR1_Leave(LeaveImpl.EMPTY_LEAVE);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R2_Employee_Pay_earned_Employee( Employee_Pay form, Employee part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R2_Employee_Pay_earned_Employee_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.addR2_recieves_Employee_Pay(form);
            form.setR2_earned_Employee(part);
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R2_Employee_Pay_earned_Employee( Employee_Pay form, Employee part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R2_Employee_Pay_earned_Employee_extent.remove( R2_Employee_Pay_earned_Employee_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.removeR2_recieves_Employee_Pay(form);
            form.setR2_earned_Employee(EmployeeImpl.EMPTY_EMPLOYEE);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R2_Employee_Pay_recieves_Pay( Employee_Pay form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R2_Employee_Pay_recieves_Pay_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.addR2_earned_Employee_Pay(form);
            form.setR2_recieves_Pay(part);
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R2_Employee_Pay_recieves_Pay( Employee_Pay form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R2_Employee_Pay_recieves_Pay_extent.remove( R2_Employee_Pay_recieves_Pay_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.removeR2_earned_Employee_Pay(form);
            form.setR2_recieves_Pay(PayImpl.EMPTY_PAY);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R3_Payment_Employee_Pay( Payment form, Employee_Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R3_Payment_Employee_Pay_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.addR3_Payment(form);
            form.setR3_Employee_Pay(part);
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R3_Payment_Employee_Pay( Payment form, Employee_Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R3_Payment_Employee_Pay_extent.remove( R3_Payment_Employee_Pay_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.removeR3_Payment(form);
            form.setR3_Employee_Pay(Employee_PayImpl.EMPTY_EMPLOYEE_PAY);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R4_Bonus_is_a_Pay( Bonus form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R4_Bonus_is_a_Pay_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.setR4_is_a_Bonus(form);
            form.setR4_is_a_Pay(part);
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R4_Bonus_is_a_Pay( Bonus form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R4_Bonus_is_a_Pay_extent.remove( R4_Bonus_is_a_Pay_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.setR4_is_a_Bonus(BonusImpl.EMPTY_BONUS);
            form.setR4_is_a_Pay(PayImpl.EMPTY_PAY);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R4_Grade_is_a_Pay( Grade form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R4_Grade_is_a_Pay_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.setR4_is_a_Grade(form);
            form.setR4_is_a_Pay(part);
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R4_Grade_is_a_Pay( Grade form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R4_Grade_is_a_Pay_extent.remove( R4_Grade_is_a_Pay_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.setR4_is_a_Grade(GradeImpl.EMPTY_GRADE);
            form.setR4_is_a_Pay(PayImpl.EMPTY_PAY);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }
    public void relate_R4_Overtime_is_a_Pay( Overtime form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot relate empty instances." );
        // TODO cardinality check
        if ( R4_Overtime_is_a_Pay_extent.add( new Relationship( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.setR4_is_a_Overtime(form);
            form.setR4_is_a_Pay(part);
        }
        else throw new ModelIntegrityException( "Instances could not be related." );
    }

    public void unrelate_R4_Overtime_is_a_Pay( Overtime form, Pay part ) throws XtumlException {
        if ( null == form || null == part ) throw new BadArgumentException( "Null instances passed." );
        if ( form.isEmpty() || part.isEmpty() ) throw new EmptyInstanceException( "Cannot unrelate empty instances." );
        if ( R4_Overtime_is_a_Pay_extent.remove( R4_Overtime_is_a_Pay_extent.get( form.getInstanceId(), part.getInstanceId() ) ) ) {
            part.setR4_is_a_Overtime(OvertimeImpl.EMPTY_OVERTIME);
            form.setR4_is_a_Pay(PayImpl.EMPTY_PAY);
        }
        else throw new ModelIntegrityException( "Instances could not be unrelated." );
    }


    // instance selections
    private BonusSet Bonus_extent;
    public BonusSet Bonus_instances() {
        return Bonus_extent;
    }
    private Employee_LeaveSet Employee_Leave_extent;
    public Employee_LeaveSet Employee_Leave_instances() {
        return Employee_Leave_extent;
    }
    private Employee_PaySet Employee_Pay_extent;
    public Employee_PaySet Employee_Pay_instances() {
        return Employee_Pay_extent;
    }
    private EmployeeSet Employee_extent;
    public EmployeeSet Employee_instances() {
        return Employee_extent;
    }
    private GradeSet Grade_extent;
    public GradeSet Grade_instances() {
        return Grade_extent;
    }
    private LeaveSet Leave_extent;
    public LeaveSet Leave_instances() {
        return Leave_extent;
    }
    private OvertimeSet Overtime_extent;
    public OvertimeSet Overtime_instances() {
        return Overtime_extent;
    }
    private PaySet Pay_extent;
    public PaySet Pay_instances() {
        return Pay_extent;
    }
    private PaymentSet Payment_extent;
    public PaymentSet Payment_instances() {
        return Payment_extent;
    }


    // relationship selections
    private IRelationshipSet R1_Employee_Leave_Employee_extent;
    public IRelationshipSet R1_Employee_Leave_Employees() throws XtumlException {
        return R1_Employee_Leave_Employee_extent;
    }
    private IRelationshipSet R1_Employee_Leave_Leave_extent;
    public IRelationshipSet R1_Employee_Leave_Leaves() throws XtumlException {
        return R1_Employee_Leave_Leave_extent;
    }
    private IRelationshipSet R2_Employee_Pay_earned_Employee_extent;
    public IRelationshipSet R2_Employee_Pay_earned_Employees() throws XtumlException {
        return R2_Employee_Pay_earned_Employee_extent;
    }
    private IRelationshipSet R2_Employee_Pay_recieves_Pay_extent;
    public IRelationshipSet R2_Employee_Pay_recieves_Pays() throws XtumlException {
        return R2_Employee_Pay_recieves_Pay_extent;
    }
    private IRelationshipSet R3_Payment_Employee_Pay_extent;
    public IRelationshipSet R3_Payment_Employee_Pays() throws XtumlException {
        return R3_Payment_Employee_Pay_extent;
    }
    private IRelationshipSet R4_Bonus_is_a_Pay_extent;
    public IRelationshipSet R4_Bonus_is_a_Pays() throws XtumlException {
        return R4_Bonus_is_a_Pay_extent;
    }
    private IRelationshipSet R4_Grade_is_a_Pay_extent;
    public IRelationshipSet R4_Grade_is_a_Pays() throws XtumlException {
        return R4_Grade_is_a_Pay_extent;
    }
    private IRelationshipSet R4_Overtime_is_a_Pay_extent;
    public IRelationshipSet R4_Overtime_is_a_Pays() throws XtumlException {
        return R4_Overtime_is_a_Pay_extent;
    }


    // ports
    private HrUI HrUI;
    public HrUI UI() {
        if ( null == HrUI ) HrUI = new HrUI( this, null );
        return HrUI;
    }
    private HrUI_Leave HrUI_Leave;
    public HrUI_Leave UI_Leave() {
        if ( null == HrUI_Leave ) HrUI_Leave = new HrUI_Leave( this, null );
        return HrUI_Leave;
    }


    // utilities
    private TIM TIM;
    public TIM TIM() {
        if ( null == TIM ) TIM = new TIMImpl<>( this );
        return TIM;
    }
    private LOG LOG;
    public LOG LOG() {
        if ( null == LOG ) LOG = new LOGImpl<>( this );
        return LOG;
    }


    // component initialization function
    @Override
    public void initialize() throws XtumlException {

    }

    @Override
    public String getVersion() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("HrProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version", "Unknown");
    }
    @Override
    public String getVersionDate() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("HrProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version_date", "Unknown");
    }

    @Override
    public boolean addInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot add empty instance to population." );
        if ( instance instanceof Bonus ) return Bonus_extent.add( (Bonus)instance );
        else if ( instance instanceof Employee ) return Employee_extent.add( (Employee)instance );
        else if ( instance instanceof Employee_Leave ) return Employee_Leave_extent.add( (Employee_Leave)instance );
        else if ( instance instanceof Employee_Pay ) return Employee_Pay_extent.add( (Employee_Pay)instance );
        else if ( instance instanceof Grade ) return Grade_extent.add( (Grade)instance );
        else if ( instance instanceof Leave ) return Leave_extent.add( (Leave)instance );
        else if ( instance instanceof Overtime ) return Overtime_extent.add( (Overtime)instance );
        else if ( instance instanceof Pay ) return Pay_extent.add( (Pay)instance );
        else if ( instance instanceof Payment ) return Payment_extent.add( (Payment)instance );
        return false;
    }

    @Override
    public boolean removeInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot remove empty instance from population." );
        if ( instance instanceof Bonus ) return Bonus_extent.remove( (Bonus)instance );
        else if ( instance instanceof Employee ) return Employee_extent.remove( (Employee)instance );
        else if ( instance instanceof Employee_Leave ) return Employee_Leave_extent.remove( (Employee_Leave)instance );
        else if ( instance instanceof Employee_Pay ) return Employee_Pay_extent.remove( (Employee_Pay)instance );
        else if ( instance instanceof Grade ) return Grade_extent.remove( (Grade)instance );
        else if ( instance instanceof Leave ) return Leave_extent.remove( (Leave)instance );
        else if ( instance instanceof Overtime ) return Overtime_extent.remove( (Overtime)instance );
        else if ( instance instanceof Pay ) return Pay_extent.remove( (Pay)instance );
        else if ( instance instanceof Payment ) return Payment_extent.remove( (Payment)instance );
        return false;
    }

    @Override
    public Hr context() {
        return this;
    }

    @Override
    public Class<?> getClassByKeyLetters(String keyLetters) {
        return classDirectory.get(keyLetters);
    }

}
