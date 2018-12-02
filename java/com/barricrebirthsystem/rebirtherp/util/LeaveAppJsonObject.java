/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;

import com.barricrebirthsystem.rebirtherp.entities.LeaveApplication;
import com.barricrebirthsystem.rebirtherp.entities.LeaveApprovals;
import com.barricrebirthsystem.rebirtherp.entities.LeaveCategory;

/**
 *
 * @author Barima
 */
public class LeaveAppJsonObject {
   private LeaveApprovals leaveApp;
    private Integer daysTaken;
    private Integer daysRemain;
    private boolean shouldApprove;

    public LeaveApprovals getLeaveApp() {
        return leaveApp;
    }

    public void setLeaveApp(LeaveApprovals leaveApp) {
        this.leaveApp = leaveApp;
    }

    public Integer getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(Integer daysTaken) {
        this.daysTaken = daysTaken;
    }

    public Integer getDaysRemain() {
        return daysRemain;
    }

    public void setDaysRemain(Integer daysRemain) {
        this.daysRemain = daysRemain;
    }

    public boolean isShouldApprove() {
        return shouldApprove;
    }

    public void setShouldApprove(boolean shouldApprove) {
        this.shouldApprove = shouldApprove;
    }
    
    
}
