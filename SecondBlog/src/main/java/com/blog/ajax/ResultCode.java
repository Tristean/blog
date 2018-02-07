package com.blog.ajax;

public enum ResultCode {
	 /**
     * �ɹ�. ErrorCode : 0
     */
    SUCCESS("0","�ɹ�"),
    /**
     * δ֪�쳣. ErrorCode : 01
     */
    UnknownException("01","δ֪�쳣"),
    /**
     * ϵͳ�쳣. ErrorCode : 02
     */
    SystemException("02","ϵͳ�쳣"),
    /**
     * ҵ�����. ErrorCode : 03
     */
    MyException("03","ҵ�����"),
    /**
     * ��ʾ������. ErrorCode : 04
     */
    InfoException("04", "��ʾ������"),
    /**
     * ���ݿ�����쳣. ErrorCode : 020001
     */
    DBException("020001","���ݿ�����쳣"),
    /**
     * ������֤����. ErrorCode : 040001
     */
    ParamException("040001","������֤����");

    private String _code;
    private String _msg;

    private ResultCode(String code, String msg){
        _code = code;
        _msg = msg;
    }

    public String getCode(){
        return _code;
    }
    public String getMsg(){
        return _msg;
    }

    public static ResultCode getByCode(String code){
        for(ResultCode ec : ResultCode.values()){
            if(ec.getCode().equals(code)){
                return ec;
            }
        }

        return null;
    }
}
