package com.Sampleproject.LogiPage.Common;

import lombok.Data;

@Data
public class APIResponse {
	
	private int status;
	
	private Object data  ;
	
	private Object error;
	
}
