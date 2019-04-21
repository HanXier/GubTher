package com.baidu.geeknews.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.geeknews.R;
import com.baidu.geeknews.base.BaseActivity;
import com.baidu.geeknews.presenter.LoginP;
import com.baidu.geeknews.util.ToastUtil;
import com.baidu.geeknews.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginView, LoginP> implements LoginView {
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_psd)
    EditText mEtPsd;

    @Override
    protected LoginP initPresenter() {
        return new LoginP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn})
    public void click(View v) {
        mPresenter.login();
    }

    @Override
    public void setData(String data) {
        mBtn.setText(data);
    }

    @Override
    public String getUserName() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return mEtPsd.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShort(msg);
    }
}
