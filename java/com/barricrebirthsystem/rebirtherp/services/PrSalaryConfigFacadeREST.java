/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.services;

import com.barricrebirthsystem.rebirtherp.entities.Employee;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApplication;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals;
import com.barricrebirthsystem.rebirtherp.entities.PrAdvanceSalary;
import com.barricrebirthsystem.rebirtherp.entities.PrSalaryConfig;
import com.barricrebirthsystem.rebirtherp.util.PaySlipJsonObject;
import com.barricrebirthsystem.rebirtherp.util.SalaryPayrollObject;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.ArrayList;
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

/**
 *
 * @author Barima
 */
@Stateless
@Path("prsalaryconfig")
public class PrSalaryConfigFacadeREST extends AbstractFacade<PrSalaryConfig> {

    @PersistenceContext(unitName = "com.barricrebirthsystem_RebirthERP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private Integer basic;
    private Integer housing;
    private Integer transport;
    private Integer utility;
    private Integer meal;
    private Integer Allowance;
    private Integer NHF = 0;
    private Integer pension = 0;
    private Integer NHIS = 0;
    private Integer GLA = 0;
    //Deduction
    private Double loan = 0.0;
    private Double carLoan = 0.0;
    private Double cooperative = 0.0;
    private Double miscDeduction = 0.0;

    public PrSalaryConfigFacadeREST() {
        super(PrSalaryConfig.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(PrSalaryConfig entity) {
        super.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(PrSalaryConfig entity) {
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
    public PrSalaryConfig find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<PrSalaryConfig> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PrSalaryConfig> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("bymonth/{mont}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PaySlipJsonObject> findByMonth(@PathParam("mont") String mont) {
        String y = mont.substring(0, 4);
        String mo = mont.substring(5);
        List<PaySlipJsonObject> salaryPaySlip = new ArrayList<>();
        int id=0;
        if (this.activateSettings()) {
            for (Employee employee : this.findAllEmployee()) {
                id++;
                
                float gross = 0.0f;
             //  Optional<String> gender = Optional.of("MALE");
                try{
                    gross = employee.getGrossSalary().floatValue();
                }catch(Exception e){
                gross = 0.0f;
                }
                // Float gross = Optional.of(employee.getGrossSalary().floatValue()).orElse(Float.valueOf(0));
                
                PaySlipJsonObject payslip = new PaySlipJsonObject();
                payslip.setAccountNo(employee.getAccountNo());
                payslip.setBankName(employee.getBankName());
                float glaValue = 0.0f;
                
                try{
                    payslip.setGLA(fmrN(employee.getGla().doubleValue()));
                    payslip.setGLA(fmrN(employee.getGla().doubleValue()));
                    glaValue =employee.getGla().floatValue();
                }catch(Exception e){
                payslip.setGLA(fmrN(0.0f));
                payslip.setGLA(fmrN(0.0f));
                }
                payslip.setPayGross(fmrN(gross));
                payslip.setStaff(employee.getFirstname() + " " + employee.getLastname());
                payslip.setId(id);

                float b = (this.basic.floatValue() / 100) * gross;
                payslip.setBasic(fmrN(b));

                float h = (this.housing.floatValue() / 100) * gross;
                payslip.setHousing(fmrN(h));

                float t = (this.transport.floatValue() / 100) * gross;
                payslip.setTransport(fmrN(t));
                float u = (this.utility.floatValue() / 100) * gross;
                payslip.setUtility(fmrN(u));
                float m = (this.meal.floatValue() / 100) * gross;
                payslip.setMeal(fmrN(m));
                float ftotal = b + h + t + u + m;
                payslip.setTotalGross(fmrN(ftotal));
                float pAll = ((this.Allowance.floatValue() / 100) * gross) + 200000;
                payslip.setPersonalAllowance(fmrN(pAll));

                float nhf = (employee.getNhf()) ? ((2.5f / 100) * b) : 0.0f;

                payslip.setNHF(fmrN(nhf));
                float pen = 0.0f;
                if (employee.getPension()) {
                    pen = (this.pension.floatValue() / 100) * (b + h + t);
                }

                payslip.setPension(fmrN(pen));

                float nhis = (this.NHIS.floatValue() / 100) * (b);
                if (employee.getNhis()) {
                    nhis = (this.NHIS.floatValue() / 100) * (b);
                }
                payslip.setNHIS(fmrN(nhis));
                float cra = pAll + nhf + pen + nhis + glaValue;
                payslip.setCRA(fmrN(cra));
                float taxable = gross - cra;
                payslip.setTaxableIncome(fmrN(taxable));
                //TAX Calculation
//                float f300 = 0.07f * 300000;
//                payslip.setFirst300(fmrN(f300));
//                payslip.setTheFirst300(fmrN(300000));
//                float f300B = taxable - 300000;
//                payslip.setThe300Balance(fmrN(f300B));
                
                float f300=0.0f;
                float f300B =0.0f;
                if(taxable>300000){
                     f300 = 0.07f * 300000;
                payslip.setFirst300(fmrN(f300));
                payslip.setTheFirst300(fmrN(300000));
                 f300B = taxable - 300000;
                payslip.setThe300Balance(fmrN(f300B));
                }

                float n300B = 0.0f;
                float n300 = 0.0f;
                if (f300B > 300000) {
                    n300 = 0.11f * 300000;
                    n300B = f300B - 300000;
                    payslip.setNext300(fmrN(n300));
                    payslip.setTheNext300(fmrN(300000));
                    payslip.setTheNext300Balance(fmrN(n300B));
                } else {
                   // payslip.setNext300("0.00");
                    //payslip.setTheNext300("0.00");
                    //payslip.setTheNext300Balance("0.00");
                }
                //Next 500
                float n500B = 0.0f;
                float n500 = 0.0f;
                if (n300B > 500000) {
                    n500 = 0.15f * 500000;
                    n500B = n300B - 500000;
                    payslip.setNext500(fmrN(n500));
                    payslip.setTheNext500(fmrN(500000));
                    payslip.setTheNext500Balance(fmrN(n500B));
                } else {
                   // payslip.setNext500("0.00");
                    //payslip.setTheNext500(fmrN(0.00));
                    //payslip.setTheNext500Balance(fmrN(0.00));
                }
                /**
                 * Second next 500 at 19%
                 */
                float sn500B = 0.0f;
                float sn500 = 0.0f;
                if (n500B > 500000) {
                    sn500 = 0.21f * 500000;
                    sn500B = n500B - 500000;
                    payslip.setSecondNext500(fmrN(sn500));
                    payslip.setTheSecondNext500(fmrN(500000));
                    payslip.setTheSecondNext500Balance(fmrN(sn500B));
                } else {
                    //payslip.setSecondNext500(fmrN(0.00));
                   // payslip.setTheSecondNext500(fmrN(0.00));
                    //payslip.setTheSecondNext500Balance(fmrN(0.00));
                }

                /**
                 * Second next 1600000 at 21%
                 */
                float n1600000MB = 0.0f;
                float n1600000 = 0.0f;
                if (sn500B > 1600000) {
                    n1600000 = 0.21f * 1600000;
                    n1600000MB = sn500B - 1600000;
                    payslip.setNext1600000M(fmrN(n1600000));
                    payslip.setTheNext1600000M(fmrN(1600000));
                    payslip.setTheNext1600000MBalance(fmrN(n1600000MB));
                } else {
                    payslip.setNext1600000M(fmrN(0.00));
                    payslip.setTheNext1600000M(fmrN(0.00));
                    payslip.setTheNext1600000MBalance(fmrN(0.00));
                }

                /**
                 * above 3200 000
                 */
                float above3200000M = 0.0f;
                if (n1600000MB > 3200000) {
                    above3200000M = 0.24f * n1600000MB;
                    payslip.setAbove3200000M(fmrN(above3200000M));
                }
                /**
                 * set taxable income
                 */
                float taxableIncome = f300 + n300 + n500 + sn500 + n1600000 + above3200000M;
                payslip.setTotalTaxPayble(fmrN(taxableIncome));
                float mG =gross/12f;
                payslip.setMonthlyGross(fmrN(mG));
                float mTaxP = taxableIncome/12;
                payslip.setMonthlyTaxPayble(fmrN(mTaxP));
                float mPen = pen/12f;
                payslip.setMonthlyPension(fmrN(mPen));
                float mNHF = nhf/12f;
                payslip.setMonthlyNHS(fmrN(mNHF));
                float mNHIS = nhis/12f;
                payslip.setMonthlyNHIS(fmrN(mNHIS));
                
                Double netPay =0.0;
                 if (this.activateDeductions(employee.getId(), y, mo)) {
                     netPay = mG-getLoan()-getCooperative()-getCarLoan()-getMiscDeduction();
                     payslip.setCarLoan(fmrN(getCarLoan()));
                     payslip.setMonthlyMiscDeduction(fmrN(getMiscDeduction()));
                     payslip.setMonthlyLoan(fmrN(getLoan()));
                     payslip.setMonthlyCooperative(fmrN(getCooperative()));
                 }else{
                       netPay = mG-getLoan()-getCooperative()-getCarLoan()-getMiscDeduction();
                     payslip.setCarLoan(fmrN(getCarLoan()));
                     payslip.setMonthlyMiscDeduction(fmrN(getMiscDeduction()));
                     payslip.setMonthlyLoan(fmrN(getLoan()));
                     payslip.setMonthlyCooperative(fmrN(getCooperative()));
                 }
                 
                 netPay = netPay-mNHF-mNHIS-mPen-mTaxP;
                 //leave application
               double leave =  this.leaveAllowance(employee.getId(), y, mo);
               float leaveA = (float)(leave/100f)*mG;
                 payslip.setNetPay(fmrN(netPay));
                 payslip.setLeaveAllowance(fmrN(leaveA));
                salaryPaySlip.add(payslip);
            }
        }

        return salaryPaySlip;
    }

    String fmrN(double dv) {
        return String.format("%,.2f", dv);
    }

    List<Employee> findAllEmployee() {
        return this.em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    boolean activateSettings() {
        for (PrSalaryConfig sal : this.findAll()) {
            try {
                switch (sal.getName().toLowerCase()) {
                    case "basic":
                        this.setBasic(sal.getPercentage());
                        break;
                    case "housing":
                        this.setHousing(sal.getPercentage());
                        break;
                    case "transport":
                        this.setTransport(sal.getPercentage());
                        break;
                    case "utility":
                        this.setUtility(sal.getPercentage());
                        break;
                    case "meal":
                        this.setMeal(sal.getPercentage());
                        break;
                    case "allowance":
                        this.setAllowance(sal.getPercentage());
                        break;
                    case "nhf":
                        this.setNHF(sal.getPercentage());
                        break;
                    case "pension":
                        this.setPension(sal.getPercentage());
                        break;
                    case "nhis":
                        this.setNHIS(sal.getPercentage());
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error in settings " + e);
                return false;
            }
        }
        return true;
    }

    private boolean activateDeductions(int empId, String y, String m) {
        try {
            String sql = "SELECT * FROM pr_advance_salary where empid = ? AND year(deducted_month)= ? AND month(deducted_month)=? AND ispaid ='NO'";
            List<PrAdvanceSalary> adv = em.createNativeQuery(sql, PrAdvanceSalary.class)
                    .setParameter(1, empId).setParameter(2, Integer.parseInt(y)).setParameter(3, Integer.parseInt(m)).getResultList();

            if (!adv.isEmpty()) {
                for (PrAdvanceSalary sal : adv) {
                    switch (sal.getName().toLowerCase()) {
                        case "loan":
                            this.setLoan(sal.getAmount().doubleValue());
                            break;
                        case "carloan":
                            this.setCarLoan(sal.getAmount().doubleValue());
                            break;
                        case "cooperative":
                            this.setCooperative(sal.getAmount().doubleValue());
                            break;
                        case "miscdeduction":
                            this.setMiscDeduction(sal.getAmount().doubleValue());
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in deductions " + e);
            return false;
        }

        return true;
    }

    private double leaveAllowance(int empId, String y, String m) {

        String sql = "SELECT * FROM leave_application where emp_id = ? AND year(start_date)= ? AND month(start_date)=?";
        List<LeaveApplication> adv = em.createNativeQuery(sql, LeaveApplication.class)
                .setParameter(1, empId).setParameter(2, Integer.parseInt(y)).setParameter(3, Integer.parseInt(m)).getResultList();
        //   float money = 0.0f;
        double percentage = 0.0;
        //check if the leave is approved
        if (adv == null) {
            return percentage;
        }

        System.out.println("OBJECT: " + adv.toString());
        for (LeaveApplication pr : adv) {
            LeaveApprovals leave = em.createNamedQuery("LeaveApprovals.findByLeaveId", LeaveApprovals.class)
                    .setParameter("leaveId", new LeaveApplication(pr.getId())).getSingleResult();
            if (leave.getApproval1Status().equalsIgnoreCase("APPROVED")
                    && leave.getApproval2Status().equalsIgnoreCase("APPROVED")
                    && leave.getApproval3Status().equalsIgnoreCase("APPROVED")) {
                //if all approvals are ok then get the percentage if is available
                percentage += pr.getLeavecat().getAllowanceInSalPercentage();
            }
        }

        return percentage;
    }

    public Integer getBasic() {
        return basic;
    }

    public void setBasic(Integer basic) {
        this.basic = basic;
    }

    public Integer getHousing() {
        return housing;
    }

    public void setHousing(Integer housing) {
        this.housing = housing;
    }

    public Integer getTransport() {
        return transport;
    }

    public void setTransport(Integer transport) {
        this.transport = transport;
    }

    public Integer getUtility() {
        return utility;
    }

    public void setUtility(Integer utility) {
        this.utility = utility;
    }

    public Integer getMeal() {
        return meal;
    }

    public void setMeal(Integer meal) {
        this.meal = meal;
    }

    public Integer getAllowance() {
        return Allowance;
    }

    public void setAllowance(Integer Allowance) {
        this.Allowance = Allowance;
    }

    public Integer getNHF() {
        return NHF;
    }

    public void setNHF(Integer NHF) {
        this.NHF = NHF;
    }

    public Integer getPension() {
        return pension;
    }

    public void setPension(Integer pension) {
        this.pension = pension;
    }

    public Integer getNHIS() {
        return NHIS;
    }

    public void setNHIS(Integer NHIS) {
        this.NHIS = NHIS;
    }

    public Integer getGLA() {
        return GLA;
    }

    public void setGLA(Integer GLA) {
        this.GLA = GLA;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getCarLoan() {
        return carLoan;
    }

    public void setCarLoan(Double carLoan) {
        this.carLoan = carLoan;
    }

    public Double getCooperative() {
        return cooperative;
    }

    public void setCooperative(Double cooperative) {
        this.cooperative = cooperative;
    }

    public Double getMiscDeduction() {
        return miscDeduction;
    }

    public void setMiscDeduction(Double miscDeduction) {
        this.miscDeduction = miscDeduction;
    }

}
