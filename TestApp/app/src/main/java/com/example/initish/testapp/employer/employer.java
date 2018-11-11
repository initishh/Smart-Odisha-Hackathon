package com.example.initish.testapp.employer;

public class employer {

    String comp_add,comp_mail,comp_name,comp_spec,comp_time,comp_con,comp_id,emp_id;

    public employer() {
    }

    public String getComp_add() {
        return comp_add;
    }

    public void setComp_add(String comp_add) {
        this.comp_add = comp_add;
    }

    public String getComp_mail() {
        return comp_mail;
    }

    public void setComp_mail(String comp_mail) {
        this.comp_mail = comp_mail;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getComp_spec() {
        return comp_spec;
    }

    public void setComp_spec(String comp_spec) {
        this.comp_spec = comp_spec;
    }

    public String getComp_time() {
        return comp_time;
    }

    public void setComp_time(String comp_time) {
        this.comp_time = comp_time;
    }

    public String getComp_con() {
        return comp_con;
    }

    public void setComp_con(String comp_con) {
        this.comp_con = comp_con;
    }

    public String getComp_id() {
        return comp_id;
    }

    public void setComp_id(String comp_id) {
        this.comp_id = comp_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public employer(String comp_add, String comp_mail, String comp_name, String comp_spec, String comp_time, String comp_con, String comp_id, String emp_id) {

        this.comp_add = comp_add;
        this.comp_mail = comp_mail;
        this.comp_name = comp_name;
        this.comp_spec = comp_spec;
        this.comp_time = comp_time;
        this.comp_con = comp_con;
        this.comp_id = comp_id;
        this.emp_id = emp_id;
    }
}
