package com.aframe.activity.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aframe.R;
import com.aframe.adapter.RxJavaAdapter;
import com.aframelib.util.log.L;
import com.aframelib.util.toast.T;
import com.aframelib.view.decoration.LinearLayoutItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zc on 2017/10/11.
 */

public class RxJavaActivity extends AppCompatActivity{
    private static final String[] METHODS = {"Create", "Just", "From Iterable", "From Array", "Defer", "Interval", "Repeat"};
    private RecyclerView rvShow;
    private RxJavaAdapter rvAdapter;
    private ArrayList<String> mItems;
    private Consumer<String> commonConsumer;
    private Observer<String> commonObserver;
    private static final int CODE_CREATE = 0;
    private static final int CODE_JUST = 1;
    private static final int CODE_FROM_ITERABLE = 2;
    private static final int CODE_FROM_ARRAY = 3;
    private static final int CODE_DEFER = 4;
    private static final int CODE_INTERVAL = 5;
    private static final int CODE_REPEAT = 6;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initDatas();
        initViews();
    }

    private void initDatas(){
        mItems = new ArrayList<>(Arrays.asList(METHODS));
    }

    private class RxJavaItemCallback implements RxJavaAdapter.ItemCallback {
        @Override
        public void onItemClick(int position) {
            mItems.set(position, "RxJava用例："+ METHODS[position] + "方式");
            rvAdapter.notifyItemChanged(position);
            switch (position){
                case CODE_CREATE:
                    usageCreate();
                    break;
                case CODE_JUST:
                    usageJust();
                    break;
                case CODE_FROM_ITERABLE:
                    usageFromIterable();
                    break;
                case CODE_FROM_ARRAY:
                    usageArray();
                    break;
                case CODE_DEFER:
                    usageDefer();
                    break;
                case CODE_INTERVAL:
                    usageInterval(3);
                    break;
                case CODE_REPEAT:
                    usageRepeat();
                    break;
            }
        }
    }

    /**
     * 最基本的用法：create方式
     */
    private void usageCreate(){
        Observable<ArrayList<String>> observable = Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<String>> emitter) throws Exception {
                emitter.onNext(mItems);
                emitter.onComplete();
            }
        });
        Observer<ArrayList<String>> observer = new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                L.e("");
            }

            @Override
            public void onNext(@NonNull ArrayList<String> mList) {
                StringBuilder mBuilder = new StringBuilder();
                for(int i=0;i<mList.size();i++){
                    mBuilder.append(mList.get(i)).append(" --> ");
                }
                mBuilder.delete(mBuilder.length()-5, mBuilder.length());
                L.e(mBuilder.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                L.e("");
            }

            @Override
            public void onComplete() {
                L.e("");
            }
        };
        observable.subscribe(observer);
    }

    /**
     * just用法
     * 填充内容，依次onNext()
     * 如果只是接受内容，不考虑结束标志，可以直接用Consumer
     * 如果需要考虑结束标志，可以考虑使用原始的observer
     */
    private void usageJust(){
        Observable<String> observable = Observable.just("北方有佳人 绝世而独立", "一顾倾人城 再顾倾人国", "宁不知倾城与倾国 佳人难再得");
//        observable.subscribe(getConsumer());
        observable.subscribe(getObserver());
    }

    /**
     * fromIterable用法
     * 填充集合Collection，Collection接口是Iterable接口的子接口
     */
    private void usageFromIterable(){
        Observable<String> observable = Observable.fromIterable(mItems);
//        observable.subscribe(getConsumer());
        observable.subscribe(getObserver());
    }

    /**
     * fromArray用法
     * 标准参数放 数组
     */
    private void usageArray(){
        Observable<String> observable = Observable.fromArray(METHODS);
//        // TODO: 2017/10/11 这个网址是RxJava的一些简单使用方式
//        // http://www.jianshu.com/p/d149043d103a
//        observable.subscribe(getConsumer());
        observable.subscribe(getObserver());
    }

    /**
     * defer用法
     */
    private void usageDefer(){
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                Thread.sleep(5000);
                return Observable.just("今日乐 不可忘", "乐未央 为乐常苦迟", "岁月逝 忽若飞", "何为自苦 使我心悲");
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                L.e("");
            }

            @Override
            public void onNext(@NonNull String s) {
                L.e(false, "--------------------------------------------------------------------");
                L.e(false, s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                L.e("");
            }

            @Override
            public void onComplete() {
                L.e("");
            }
        });
    }

    /**
     * interval用法
     * 参数 时间间隔方法的个数
     */
    private void usageInterval(int paramsCount){
        if(paramsCount == 2)
            usageInterval2Params();
        else if(paramsCount == 3)
            usageInterval3Params();
    }

    /**
     * repeat用法
     * 内容重复多少次，再依次发送
     */
    private void usageRepeat(){
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just("醉日", "昨日", "追日").repeat(3);
            }
        });

        observable.subscribe(getObserver());
    }

    private Observer<String> getObserver(){
        if(commonObserver == null){
            commonObserver = new Observer<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    L.e("");
                }

                @Override
                public void onNext(@NonNull String s) {
                    L.e(s);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    L.e("");
                }

                @Override
                public void onComplete() {
                    L.e("");
                }
            };
        }

        return commonObserver;
    }

    private Consumer<String> getConsumer(){
        if(commonConsumer == null){
            commonConsumer = new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    L.e(s);
                }
            };
        }

        return commonConsumer;
    }

    private void initViews(){
        rvShow = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShow.setLayoutManager(mLayoutManager);

        rvAdapter = new RxJavaAdapter(this, mItems);
        rvAdapter.setmItemCallback(new RxJavaItemCallback());
        rvShow.setAdapter(rvAdapter);

        LinearLayoutItemDecoration mItemDecoration2 = new LinearLayoutItemDecoration(this, LinearLayoutItemDecoration.VERTICAL_LIST, R.drawable.shape_rv, false);
        rvShow.addItemDecoration(mItemDecoration2);
        rvShow.setItemAnimator(new DefaultItemAnimator());
    }

    private void usageInterval2Params(){
        Observable<Long> observable = Observable.interval(2000, TimeUnit.MILLISECONDS);
        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                L.e("");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                L.e("aLong = " + aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                L.e("");
            }

            @Override
            public void onComplete() {
                L.e("");
            }
        });
    }

    private void usageInterval3Params(){
        Observable<Long> observable = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS, new Scheduler() {
            @Override
            public Worker createWorker() {
                return new Scheduler.Worker() {
                    boolean dispose = false;
                    @Override
                    public Disposable schedule(@NonNull final Runnable run, long delay, @NonNull TimeUnit unit) {
                        L.lineI();
                        if(isDisposed()){
                            L.e(false, "--- isDisposed() ----");
                            return null;
                        }

                        L.e(false, "--- !isDisposed() ----");
                        return Observable.timer(delay, unit).subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                L.e(false, "Thread.name = "+ Thread.currentThread().getName());
                                if(!RxJavaActivity.this.isDestroyed()){
                                    run.run();
                                }
                            }
                        });
                    }

                    @Override
                    public void dispose() {
                        dispose = true;
                    }

                    @Override
                    public boolean isDisposed() {
                        return dispose;
                    }
                };
            }
        });

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                        L.e("");
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        L.e(false, "Thread.name = "+ Thread.currentThread().getName() + " ; aLong = " + aLong);
                        T.showToast(RxJavaActivity.this, "aLong = " + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.e("");
                    }

                    @Override
                    public void onComplete() {
                        L.e("");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(disposables != null){
            disposables.clear();
        }
    }
}




























