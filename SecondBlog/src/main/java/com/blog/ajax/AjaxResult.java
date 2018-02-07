package com.blog.ajax;

public class AjaxResult {
	

	private String ErrorCode=ResultCode.SUCCESS.getCode();
	
	private String message=ActionConstants.DEFAULT_SUCCESS_RETURNMSG;
	
	private Object data=null;

	public AjaxResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	//获取正确结果模板
	public static AjaxResult getOK(String message,Object obj){
		AjaxResult result=new AjaxResult();
		result.setMessage(message);
		result.setData(obj);
		return result;
	}
	
	public static AjaxResult getOK(Object obj){
		AjaxResult result=new AjaxResult();
		result.setMessage(ActionConstants.DEFAULT_SUCCESS_RETURNMSG);
		result.setData(obj);
		return result;
	}
	
	public static AjaxResult getOk(){
		return getOK(ActionConstants.DEFAULT_SUCCESS_RETURNMSG);
	}
	
	public static AjaxResult getError(ResultCode errorCode,String message,Object obj){
		AjaxResult result=new AjaxResult();
		result.setErrorCode(errorCode.getCode());
		result.setMessage(message);
		result.setData(obj);
		return result;
	}
	
	public static AjaxResult getError(ResultCode resultCode){
		return getError(resultCode,ActionConstants.DEFAULT_FAILED_DETURNMSG,null);
	}
	
	 @Override
	    public String toString() {
	        return "AjaxResult{" +
	                "ErrorCode='" + ErrorCode + '\'' +
	                ", Message='" + message + '\'' +
	                ", Data=" + data +
	                '}';
	    }
}
