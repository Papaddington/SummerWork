package com.zucc.circle.summerwork.Contants;

/**
 * Created by 圆圈 on 2017-07-02.
 */

public class ContantUri {
    public static final String NO_HTTP_URI = "http://api.nohttp.net";
    public static final String URL = "http://192.168.1.10:8080/PersonalManagement/";
    public static final String LOGIN_URL = URL + "User_login.action";
    public static final String REGIST_URL = URL + "User_register.action";
    public static final String ADD_PERSON_PLAN_URL = URL + "Schedule_addSchedule.action";
    public static final String LOADPERSONPLAN_URL = URL + "Schedule_loadPersonalSchedule.action";

    public static final String FINISHPERSONPLAN_URL = URL + "Schedule_finishSchedule";
    public static final String LOADLOG_URL = URL + "Log_loadLog.action";
    public static final String ADDLOG_URL = URL + "Log_addLog.action";
    public static final String DELETELOG_URL = URL + "Log_deleteLog.action";

    public static final String LOAD_PERSON_INCOME_TYPE_URL = URL + "Income_loadIncomeType.action";
    public static final String LOAD_PERSON_INCOME_URL = URL + "Income_loadIncome.action";
    public static final String ADD_PERSON_INCOME_URL = URL + "Income_addIncome.action";
    public static final String LOAD_PERSON_EXPEND_TYPE_URL = URL + "Expend_loadExpendType.action";
    public static final String LOAD_PERSON_EXPEND_URL = URL + "Expend_loadExpend.action";
    public static final String ADD_PERSON_EXPEND_URL = URL + "Expend_addExpend.action";
    public static final String GET_EARNING_URL = URL + "Income_earnings.action";
}
