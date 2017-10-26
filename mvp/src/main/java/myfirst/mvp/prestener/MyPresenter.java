package myfirst.mvp.prestener;

import myfirst.mvp.model.Fragment1ModelListener;
import myfirst.mvp.model.MyModel;
import myfirst.mvp.view.MyView;

/**
 * Created by Bare on 2017/10/26.
 */

public class MyPresenter {

    private MyView fragment1View;
    private MyModel fragment1Model;

    public MyPresenter(MyView fragment1View) {
        this.fragment1View = fragment1View;
        this.fragment1Model = new MyModel();
    }

    public void getData(String path){
        fragment1Model.getData(path, new Fragment1ModelListener() {
            @Override
            public void success(Object object) {
                fragment1View.success(object);
            }
            @Override
            public void onfailed() {
                fragment1View.onfailed();
            }
        });
    }

}
