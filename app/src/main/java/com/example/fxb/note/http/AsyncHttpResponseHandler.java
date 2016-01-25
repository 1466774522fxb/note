package com.example.fxb.note.http;

import android.content.Context;

import com.example.fxb.note.R;
import com.example.fxb.note.utils.Constant;
import com.example.fxb.note.utils.Log;
import com.example.fxb.note.utils.ToastUtil;
import com.example.fxb.note.utils.Utils;
/*import com.gammainfo.cycares.R;
import com.gammainfo.cycares.utils.Constant;
import com.gammainfo.cycares.utils.Log;
import com.gammainfo.cycares.utils.ToastUtil;
import com.gammainfo.cycares.utils.Utils;*/
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public final class AsyncHttpResponseHandler extends
		com.loopj.android.http.JsonHttpResponseHandler {
	private JsonHttpResponseHandler mAsyncHttpResponseHandler;
	private Context mContext;
	private boolean mIsSilent;// 静默获取数据，失败不做任何提示

	public AsyncHttpResponseHandler(Context context,
			JsonHttpResponseHandler asyncHttpResponseHandler) {
		this(context, asyncHttpResponseHandler, false);
	}

	public AsyncHttpResponseHandler(Context context,
			JsonHttpResponseHandler asyncHttpResponseHandler, boolean silent) {
		mContext = context;
		mAsyncHttpResponseHandler = asyncHttpResponseHandler;
		mIsSilent = silent;
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			String responseString, Throwable throwable) {
		if (!mIsSilent) {
			if (mContext != null) {
				if (Utils.isNetworkConnected(mContext)) {
					ToastUtil.make(mContext).show(
							R.string.common_toast_connectionnodata);
				} else {
					ToastUtil.make(mContext).show(
							R.string.common_toast_connectionfailed);
				}
				Log.i("http response", responseString);
				JSONObject errorJsonObject = new JSONObject();
				try {
					errorJsonObject.put("code", "-123789");
					errorJsonObject.put("msg", responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (mAsyncHttpResponseHandler != null) {
					mAsyncHttpResponseHandler.onFailure(statusCode, headers,
							throwable, errorJsonObject);
				} else {
					onFailure(statusCode, headers, throwable, errorJsonObject);
				}
			}
		} else {
			super.onFailure(statusCode, headers, responseString, throwable);
		}
	}

	@Override
	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONObject errorResponse) {

		Log.i("http result", throwable.toString());

		if (!mIsSilent) {
			if (mContext != null) {
				if (Utils.isNetworkConnected(mContext)) {
					ToastUtil.make(mContext).show(
							R.string.common_toast_connectionnodata);
				} else {
					ToastUtil.make(mContext).show(
							R.string.common_toast_connectionfailed);
				}
				if (mAsyncHttpResponseHandler != null) {
					mAsyncHttpResponseHandler.onFailure(statusCode, headers,
							throwable, errorResponse);
				} else {
					super.onFailure(statusCode, headers, throwable,
							errorResponse);
				}
			}
		} else {
			super.onFailure(statusCode, headers, throwable, errorResponse);
		}
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		Log.i("http result", response.toString());
		try {
			// 补充完整系统级参数
			switch (response.getInt("code")) {
			case Constant.CODE_TIMEOUT:
			case Constant.CODE_DATA_ERROR:
			case Constant.CODE_DB_ERROR:
			case Constant.CODE_SERVICE_ERROR:
			case Constant.CODE_USER_PERMISSIONS:
			case Constant.CODE_SERVICE_UNAVAILABLE:
			case Constant.CODE_MISSING_METHOD:
			case Constant.CODE_ASIGN_UNVALID:
			case Constant.CODE_MISSING_API_VERSION:
			case Constant.CODE_API_VERSION_ERROR:
			case Constant.CODE_TOKEN_EXPIRES:
			case Constant.CODE_DATA_CONFLICT:
				if (!mIsSilent) {
					if (mContext != null) {
						ToastUtil.make(mContext).show(
								R.string.common_toast_error);
					}
				}
				break;
			case Constant.CODE_RELOGIN:
				// re-login
				if (mContext != null) {
					// mContext.sendOrderedBroadcast(new Intent(
					// UserUtil.ACTION_RELOGIN), null);
				}
				break;
			case Constant.CODE_USER_LOCKED:
			case Constant.CODE_SUCCESS:
			case Constant.CODE_FAILURE:
			default:
				if (mContext != null) {
					if (mAsyncHttpResponseHandler == null) {
						super.onSuccess(statusCode, headers, response);
					} else {
						mAsyncHttpResponseHandler.onSuccess(statusCode,
								headers, response);
					}
				}
				break;
			}
		} catch (JSONException e) {
			super.onSuccess(statusCode, headers, response);
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish() {
		if (mContext != null) {
			if (mAsyncHttpResponseHandler == null) {
				super.onFinish();
			} else {
				mAsyncHttpResponseHandler.onFinish();
			}
		}
	}

	@Override
	public void onStart() {
		if (mContext != null) {
			if (mAsyncHttpResponseHandler == null) {
				super.onStart();
			} else {
				mAsyncHttpResponseHandler.onStart();
			}
		}
	}
}
