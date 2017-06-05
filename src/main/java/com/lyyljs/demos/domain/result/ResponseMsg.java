package com.lyyljs.demos.domain.result;

public enum ResponseMsg {
	SUCCESS("000000", "操作成功"),
	FAILED("999999","操作失败"),
	
	LoginRequired("000100", "未登陆！"),
	InadequatePermissions("000101", "权限不足"),
	LoginNameOrPassWordError("000200", "用户名或者密码错误！"),
	;
	
	
	private ResponseMsg(String code, String msg){
		this.code = code;
        this.msg = msg;
	}
	private String code;
    private String msg;
    
    public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
