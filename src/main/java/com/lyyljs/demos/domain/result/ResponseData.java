package com.lyyljs.demos.domain.result;

public class ResponseData extends Response {
	private static final long serialVersionUID = 1L;
	
	private Object data;
	
	public ResponseData(Object data) {
        this.data = data;
    }
	
	public ResponseData(ResponseMsg respMsg) {
  	  	super(respMsg);
	}
	
	public ResponseData(String code, String msg) {
        super(code, msg);
    }

    public ResponseData(String code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }

    public ResponseData(ResponseMsg respMsg, Object data) {
        super(respMsg);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "ResponseData{" +
                "data=" + data +
                "} " + super.toString();
    }
}
