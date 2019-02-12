package com.hymane.emmm.mvp;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/1
 * Description: MVP基础契约，分 M - V - P 三个接口
 * V 层可以在此创建内部类辅助使用，见{@link ViewImpl}
 */
public interface IBaseContract {
    /***
     * MVP - M 基础Model接口
     * 业务逻辑处理，具体业务数据处理，逻辑操作，网络请求等
     * 可交由Model层处理，具体操作可根据业务需要由子类进行扩展
     * <可选>
     */
    interface Model {
    }

    /***
     * MVP - V 基础View接口
     * 视图控制与操作，具体UI操作根据业务子类进行扩展
     */
    interface View {
        void showLoading();

        void hideLoading();

        void onFailed(int code, String msg);
    }

    /***
     * MVP - P 基础Presenter接口
     * 业务协调与处理，具体业务操作由子类进行扩展
     */
    interface Presenter {
        void unsubscribe();
    }

    /***
     * 子类可以创建默认实现View类实现Contract对应接口，
     * 方便业务页面选择性的实现需要实现的方法，而不是必须实现所有接口方法，
     * 具体业务由子类实现（内部类形式呈现）。
     * 默认 MVP - V 实现
     * <可选>
     */
    final class ViewImpl implements View {

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void onFailed(int code, String msg) {

        }
    }
}
