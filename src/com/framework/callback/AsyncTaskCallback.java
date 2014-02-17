package com.framework.callback;

import com.framework.base.BaseAsyncTask;
import com.framework.base.BaseResponse;

/**
 * @author samkirton
 */
public interface AsyncTaskCallback {
	public void asyncResponse(BaseAsyncTask asyncTask, BaseResponse baseResponse);
}
