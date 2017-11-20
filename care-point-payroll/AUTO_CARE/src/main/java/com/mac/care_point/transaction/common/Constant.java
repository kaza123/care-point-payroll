/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.transaction.common;

/**
 *
 * @author Kavish Manjitha
 */
public interface Constant {

    //job items
    public static final String SERVICE_CHARGERS = "SERVICE_CHARGERS";

    //common status
    public static final String PENDING_STATUS = "PENDING";
    public static final String APPROVE_STATUS = "APPROVED";
    public static final String CONFIRM_STATUS = "CONFIRMED";
    public static final String COMPLITED_STATUS = "COMPLITED";
    public static final String FINISHE_STATUS = "FINISHED";
    public static final String DLETED_STATUS = "DELETED";
    public static final String ON_GOING = "ON_GOING";

    public static final String CHECK_STATUS = "CHECK";
    public static final String NOT_CHECK_STATUS = "NOT_CHECK";

    //payment types
    public static final String CASH_PAYMENT = "CASH_PAYMENT";
    public static final String CHEQUE_PAYMENT = "CHEQUE_PAYMENT";
    public static final String CARD_PAYMENT = "CARD_PAYMENT";

    //invoice payment
    public static final String INVOICE_CREATE = "INVOICE";
    public static final String INVOICE_PAYMENT = "INVOICE_PAYMENT";
    public static final String CUSTOMER_PAYMENT = "CUSTOMER_PAYMENT";
    public static final String PAYMENT_VOUCHER = "PAYMENT_VOUCHER";
    public static final String GRN_PAYMENT = "GRN_PAYMENT";
    public static final String SUPPLIER_PAYMENT = "SUPPLIER_PAYMENT";

    // payment voucher advance paynemt and balance payment
//    public static final String INVOICE_CREATE = "INVOICE";
    public static final String PAYMENT = "PAYMENT";
    public static final String OVER_PAYMENT = "OVER_PAYMENT";
    public static final String ADVANCE = "ADVANCE";

    //t_payment information table - form names
    public static final String INVOICE_FORM = "INVOICE_FORM";
    public static final String GRN_APPROVE_FORM = "GRN_APPROVE_FORM";
    public static final String DIRECT_GRN_FORM = "DIRECT_GRN_FORM";
    public static final String STOCK_FORM = "STOCK_FORM";
    public static final String PAYMENT_VOUCHER_FORM = "PAYMENT_VOUCHER_FORM";
    public static final String PAYMENT_FORM = "PAYMENT_FORM";
    public static final String ADVANCE_FORM = "ADVANCE_FORM";
    public static final String ITEMSALE_FORM = "ITEMSALE_FORM";

//  store name
    public static final String MAIN_STOCK = "MAIN_STOCK";
    public static final String FRONT_STOCK = "FRONT_STOCK";
    public static final String BULK_STOCK = "BULK_STOCK";

    //TStock transfer type
    public static final String INTERNAL_TRANSFER = "INTERNAL_TRANSFER";
    public static final String EXTERNAL_TRANSFER = "EXTERNAL_TRANSFER";

    //TStock Transfer Form
    public static final String BRANCH_TRANSFER_OUT = "BRANCH_TRANSFER_OUT";
    public static final String BRANCH_TRANSFER_IN = "BRANCH_TRANSFER_IN";
    public static final String INTERNAL_TRANSFER_IN = "INTERNAL_TRANSFER_IN";
    public static final String INTERNAL_TRANSFER_OUT = "INTERNAL_TRANSFER_OUT";

    //Btanch Type
    public static final String MAIN_BRANCH = "MAIN_BRANCH";
    public static final String OTHER_BRANCH = "OTHER_BRANCH";

    public static final String BAY_ISSUE = "BAY_ISSUE";

    //sms message details
    public static final String INVOICE_MESSAGE = "INVOICE_MESSAGE";
    public static final String ESTIMATE_MESSAGE = "ESTIMATE_MESSAGE";
    public static final String CUSTOMER_SATISFACTION_MESSAGE = "CUSTOMER_SATISFACTION_MESSAGE";
    
    //bay type
    public static final String VEHICLE_WAITING_BAY = "VEHICLE_WAITING_BAY";
    public static final String NORMAL_BAY = "NORMAL_BAY";
    public static final String EMPLOYEE_WAITING_BAY = "EMPLOYEE_WAITING_BAY";
}
