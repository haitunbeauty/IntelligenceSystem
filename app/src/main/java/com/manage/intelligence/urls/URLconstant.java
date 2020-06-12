package com.manage.intelligence.urls;

public class URLconstant {

    public static final String BASE_URL = "http://39.100.40.204:8088/APPService.asmx/";


    //1.登录接口
//    public static final String LOGIN_URL = BASE_URL+"Login";
    public static final String LOGIN_URL = "http://39.100.40.204:8088/WebService1.asmx?op=HelloWorld";

    //2.获得所有组织信息
    public static final String GET_ORG_INFO_URL = BASE_URL+"GetOrgInfo";

    //3.获得部门信息
    public static final String GET_DEPT_INFO_URL = BASE_URL+"GetDeptInfo";

    //4.模具入库
    public static final String GET_MOULD_IN_URL = BASE_URL+"MouldIn";

    //5.模具出库
    public static final String GET_MOULD_OUT_URL = BASE_URL+"MouldOut";

    //6.在库模具信息
    public static final String GET_WH_MOULD_INFO_URL = BASE_URL+"GetWHMouldInfo";

    //7.上模
    public static final String GET_MOULD_ON_URL = BASE_URL+"MouldOn";

    //8.下模
    public static final String GET_MOULD_OFF_URL = BASE_URL+"MouldOff";

    //9.获得上模信息by设备
    public static final String GET_MOULD_ON_INFO_BY_EQP_CODE_URL = BASE_URL+"GetMouldOnInfoByEqpCode";

    //10.报警
    public static final String GET_ALARM_URL = BASE_URL+"Alarm";

    //11.获得报警信息
    public static final String GET_ALARM_INFO_URL = BASE_URL+"GetAlarmInfo";

    //12.报警接单
    public static final String GET_ALARM_ACCEPT_URL = BASE_URL+"AlarmAccept";

    //13.报警派工
    public static final String GET_ALARM_ASSIGN_URL = BASE_URL+"AlarmAssign";

    //14.报警撤单 撤销派工或接收的报警单
    public static final String GET_ALARM_ASSIGN_ACCEPT_CANCEL_URL = BASE_URL+"AlarmAssignAcceptCancel";

    //15.维修
    public static final String GET_REPAIR_URL = BASE_URL+"Repair";

    //16.更换维修人
    public static final String GET_REPAIR_EMP_CHANGE_URL = BASE_URL+"RepairEmpChange";

    //17.保养
    public static final String GET_REPAIR_MAINTAIN_URL = BASE_URL+"RepairMaintain";

    //18.换件
    public static final String GET_REPAIR_CHANGE_PARTS_URL = BASE_URL+"RepairChangeParts";

    //19.报警审核
    public static final String GET_ALARM_CHECK_URL = BASE_URL+"AlarmCheck";

    //20.获得故障描述
    public static final String GET_ERR_DESC_URL = BASE_URL+"GetErrDesc";

    //21.获得维修人员信息
    public static final String GET_REPAIR_EMP_INFO_URL = BASE_URL+"GetRepairEmpInfo";


}
