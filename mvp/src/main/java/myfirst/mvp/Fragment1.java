package myfirst.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.List;

import myfirst.mvp.prestener.MyPresenter;
import myfirst.mvp.view.MyView;


public class Fragment1 extends Fragment implements MyView{



    private RecyclerView rec;
    private String path="http://ic.snssdk.com/2/article/v25/stream/?category=news_hot&count=20&min_behot_time=1455521166&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_nme=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    private MyPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fragment1,null);
        rec = view.findViewById(R.id.fr1_rec);
        presenter = new MyPresenter(this);
        presenter.getData(path);
        return view;
    }

    @Override
    public void success(final Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                ShouyeBean bean=gson.fromJson(object.toString(),ShouyeBean.class);
                List<ShouyeBean.DataBean> list=bean.getData();
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                rec.setLayoutManager(linearLayoutManager);
                FragmentAdapter adapter=new FragmentAdapter(getActivity(),list);
                rec.setAdapter(adapter);
                System.out.println("================"+bean.getData().size());

            }
        });
    }

    @Override
    public void onfailed() {

    }
}
