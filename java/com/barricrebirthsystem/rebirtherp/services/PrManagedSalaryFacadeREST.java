/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Department;
import com.barricrebirthsystem.rebirtherp.entities.DeptEmployee;
import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApplication;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals;
import com.barricrebirthsystem.rebirtherp.entities.PrAdvanceSalary;
import com.barricrebirthsystem.rebirtherp.entities.PrManagedSalary;
import com.barricrebirthsystem.rebirtherp.entities.PrSalaryAllowance;
import com.barricrebirthsystem.rebirtherp.entities.PrSalaryDeduction;
import com.barricrebirthsystem.rebirtherp.entities.PrSalarytemplate;
import com.barricrebirthsystem.rebirtherp.util.SalaryPayrollObject;
import com.barricrebirthsystem.rebirtherp.util.TemData;
import com.barricrebirthsystem.rebirtherp.util.UtilHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Barima
 */
@Stateless
@Path("prmanagedsalary")
public class PrManagedSalaryFacadeREST extends AbstractFacade<PrManagedSalary> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PrManagedSalaryFacadeREST() {
        super(PrManagedSalary.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(List<TemData> entity) {
        System.err.println("DATA: " + entity);

        Iterator<TemData> iter = entity.iterator();
        while (iter.hasNext()) {
            TemData e = iter.next();
            try {
                PrManagedSalary pr = (PrManagedSalary) em.createNamedQuery("PrManagedSalary.findByEmpId").setParameter("id", em.find(Employee.class, e.getEmployeeId()))
                        .getSingleResult();
                int id = pr.getId();
                pr.setTemplateId(em.find(PrSalarytemplate.class, e.getTemplateId()));
                super.edit(pr);
            } catch (Exception ex) {
                //Well data doesnt exist yet, so no worries, nonsense exception thrown, instead of just empty data, JAVA Shmmmmh
                //Nothing concern me , i will type my logic here
                PrManagedSalary pr = new PrManagedSalary();
                pr.setEmpid(em.find(Employee.class, e.getEmployeeId()));
                pr.setTemplateId(em.find(PrSalarytemplate.class, e.getTemplateId()));
                super.create(pr);

            }

        }

        // entity.forEach(e ->{
        return Response.status(Response.Status.OK).entity(UtilHelper.publishSuccessMessage()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PrManagedSalary entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PrManagedSalary find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<PrManagedSalary> findAll() {
        return super.findAll();
    }

    /**
     * This will find by department, after getting department object, it will get the employee id
     * from the table and find by employee id, it then create a new list and add the employee filter to 
     * then return the new list, additionally ; it will find advance salary by the month and the employee.
     * IF found, it will deduct the amount from the current month. before returning the object
     * @param id
     * @param mont
     * @return 
     */
    @GET
    @Path("bydepartmentid/{id}/{mont}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<SalaryPayrollObject> findByDepartmentId(@PathParam("id") Integer id,@PathParam("mont") String mont) {
        String y = mont.substring(0, 4);
        String mo = mont.substring(5);
        
        System.out.println("M "+mo+" YEAR:"+y);
        try {
            List<DeptEmployee> de = em.createNamedQuery("DeptEmployee.findByDeptId").
                    setParameter("id", new Department(id)).getResultList();

            List<SalaryPayrollObject> sal = new ArrayList<>();

            for(DeptEmployee emp: de){

                int empId = emp.getEmployeeID().getId();
                try {
                    PrManagedSalary m = em.createNamedQuery("PrManagedSalary.findByEmpId", PrManagedSalary.class).
                            setParameter("id", em.find(Employee.class, empId)).getSingleResult();
                    //Please use stored procedure in the future, for optimization. Please Please Please!!!!!!
              float advance =  getAdvancePay(empId, y,  mo);
                    System.out.println("ADVANCE "+advance);
               
            float net =   m.getTemplateId().getBasicSalary().floatValue()+
                    m.getTemplateId().getTotalAllowance().floatValue()+ m.getTemplateId().getTotalDeductions().floatValue();
            
                   System.err.println("NET "+net);
                   
                   //add leave allowance if available
                   double leaveAllowance = this.leaveAllowance(empId, y, mo);
                   float allowance = (float)((leaveAllowance/100)*m.getTemplateId().getBasicSalary().doubleValue());
                   
           float newBasic = net-advance+allowance;
           
                 System.err.println(" NEW BASIC "+newBasic);
                 //PrSalarytemplate temp = m.getTemplateId();


                SalaryPayrollObject obj = new SalaryPayrollObject();    
                obj.setEmpId(m.getEmpid().getId());
                obj.setName(m.getEmpid().getFirstname() +" "+m.getEmpid().getLastname());
                obj.setStaffId(m.getEmpid().getStaffid());
                obj.setDepartment(emp.getDeptID().getName());
                obj.setMonth(mont);
                obj.setSalaryGrade(m.getTemplateId().getSalaryGrade());
                obj.setBasicSalary(m.getTemplateId().getBasicSalary().floatValue());
                obj.setTotalDeduction(m.getTemplateId().getTotalDeductions().floatValue());
                obj.setTotalAllowance(m.getTemplateId().getTotalAllowance().floatValue());
                obj.setNetSalary(newBasic);
                
                    
//MANUPULATE THIS AT THE FRONT END BEFORE PRESENTATION
                    sal.add(obj);
                } catch (Exception e) {
                }

            }
            return sal;
            
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());

        }

        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PrManagedSalary> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private Float getAdvancePay(int empId, String y, String m){
    
           String sql ="SELECT * FROM pr_advance_salary where empid = ? AND year(deducted_month)= ? AND month(deducted_month)=? AND ispaid ='NO'";
                 List<PrAdvanceSalary> adv =   em.createNativeQuery(sql,PrAdvanceSalary.class)
                         .setParameter(1, empId).setParameter(2,Integer.parseInt(y)).setParameter(3, Integer.parseInt(m)).getResultList();
                float money = 0.0f;
                
                System.out.println("OBJECT: "+adv.toString());
                 for(PrAdvanceSalary pr: adv){
                     float ff = pr.getAmount().floatValue();
                     System.err.println("Raw Amount: "+ff);
                     money  = money+ff;
                
                 }
                    System.err.println("F "+money);
    return money;
    }
    
    
    /**
     * 
     * @param id Employee ID
     * @param mont month
     * @return 
     */
   @GET
    @Path("byemployeeid/{id}/{mont}")
    @Produces({MediaType.APPLICATION_JSON})
    public SalaryPayrollObject findByEmployee(@PathParam("id") Integer id,@PathParam("mont") String mont) {
      String y = mont.substring(0, 4);
        String mo = mont.substring(5);
     PrManagedSalary m = em.createNamedQuery("PrManagedSalary.findByEmpId", PrManagedSalary.class).
                            setParameter("id", em.find(Employee.class, id)).getSingleResult();
              float advance =  getAdvancePay(id, y,  mo);
            float net =   m.getTemplateId().getBasicSalary().floatValue()+
                    m.getTemplateId().getTotalAllowance().floatValue()+ m.getTemplateId().getTotalDeductions().floatValue();
            
           float newBasic = net-advance;
               
//                 
                DeptEmployee dp = em.createNamedQuery("DeptEmployee.findByEmpid", DeptEmployee.class)
                        .setParameter("id", em.find(Employee.class, id)).getSingleResult();
                SalaryPayrollObject obj = new SalaryPayrollObject();
                obj.setEmpId(dp.getEmployeeID().getId());
                obj.setName(m.getEmpid().getFirstname() +" "+m.getEmpid().getLastname());
                obj.setStaffId(m.getEmpid().getStaffid());
                obj.setDepartment(dp.getDeptID().getName());
                obj.setMonth(mont);
                obj.setSalaryGrade(m.getTemplateId().getSalaryGrade());
                obj.setBasicSalary(m.getTemplateId().getBasicSalary().floatValue());
                obj.setTotalDeduction(m.getTemplateId().getTotalDeductions().floatValue());
                obj.setTotalAllowance(m.getTemplateId().getTotalAllowance().floatValue());
                obj.setNetSalary(newBasic);
                
                List<PrSalaryAllowance> allowances = em.createNamedQuery("PrSalaryAllowance.findByTemplate", PrSalaryAllowance.class)
                        .setParameter("tempid", m.getTemplateId()).getResultList();
                obj.setAllowances(allowances);
                List<PrSalaryDeduction> deductions = em.createNamedQuery("PrSalaryDeduction.findByTemp", PrSalaryDeduction.class)
                        .setParameter("temp", m.getTemplateId()).getResultList();
                obj.setDeductions(deductions);
                
                 String sql ="SELECT * FROM pr_advance_salary where empid = ? AND year(deducted_month)= ? AND month(deducted_month)=? AND ispaid ='NO'";
                 List<PrAdvanceSalary> adv =   em.createNativeQuery(sql,PrAdvanceSalary.class)
                         .setParameter(1, id).setParameter(2,Integer.parseInt(y)).setParameter(3, Integer.parseInt(mo)).getResultList();
             obj.setAdvances(adv);
             
             return obj;
                
    }
    
 private double leaveAllowance(int empId, String y, String m){
    
           String sql ="SELECT * FROM leave_application where emp_id = ? AND year(start_date)= ? AND month(start_date)=?";
                 List<LeaveApplication> adv =   em.createNativeQuery(sql,LeaveApplication.class)
                         .setParameter(1, empId).setParameter(2,Integer.parseInt(y)).setParameter(3, Integer.parseInt(m)).getResultList();
             //   float money = 0.0f;
                double percentage = 0.0;
                //check if the leave is approved
                if(adv ==null) return percentage;
               
                System.out.println("OBJECT: "+adv.toString());
                 for(LeaveApplication pr: adv){
              LeaveApprovals leave=  em.createNamedQuery("LeaveApprovals.findByLeaveId", LeaveApprovals.class)
                        .setParameter("leaveid",new LeaveApplication(pr.getId()) ).getSingleResult();
              if(leave.getApproval1Status().equalsIgnoreCase("APPROVED") && 
                      leave.getApproval2Status().equalsIgnoreCase("APPROVED") &&
                      leave.getApproval3Status().equalsIgnoreCase("APPROVED")){
              //if all approvals are ok then get the percentage if is available
              percentage += pr.getLeavecat().getAllowanceInSalPercentage();
              }
                 }
                    
    return percentage;
    }
    
}
