package com.hhit.bamboomvp.home.presenter;

import com.hhit.bamboolibrary.intermediate.ServiceManager;
import com.hhit.bamboomvp.home.contract.HomeContract;
import com.hhit.bamboomvp.home.model.HomeModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xiaopeng on 2017/8/14.
 */
public class HomePresenterTest {

    @Mock
    HomeContract.View mView;

    HomeModel mModel;

    @Mock
    ServiceManager mServiceManager;

    HomePresenter mHomePresenter;

    @Before
    public void setUp() throws Exception {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        mModel = new HomeModel(mServiceManager);

        mHomePresenter= new HomePresenter(mModel, mView);
    }

    @Test
    public void requestUsers() throws Exception {

        mHomePresenter.requestUsers(true);

        verify(mView).showLoading();
        verify(mView).hideLoading();

    }

}