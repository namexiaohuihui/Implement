package common.tool.informationException;

import common.tool.SystemOut;

/**
 * 外部使用:
 * 1.在方法名要抛出
 * 2.或者在catch中定义这个错误类然后抛出。。。
 *
 *
 * Created by ${XiaoHuiHui} on 2017/8/8 on 9:41.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ErrorException extends Exception {

    //构造详细消息为 null 的新异常。
    public ErrorException() {
    }

    // 构造带指定详细消息的新异常。
    public ErrorException(String message) {
        super(message);
    }

    //构造带指定详细消息和原因的新异常。
    public ErrorException(String message, Throwable cause) {
        super("代码错误的地方:" + message, cause);
    }

    /**
     * 定义构造方法
     *
     * @param claszz 发生错误的类名
     * @param method 发生错误的方法名
     * @param cause  发生错误的类型
     */
    public ErrorException(String claszz, String method, Throwable cause) {
        SystemOut.getStringOut("发生错误的类:" + claszz, "错误的方法:" + method);
        cause.printStackTrace();
    }

    //根据指定的原因和 (cause==null ? null : cause.toString()) 的详细消息构造新异常（它通常包含 cause 的类和详细消息）。
    public ErrorException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message 错误信息，包括自定义错误的包名以及信息，和报错的代码行
     * @param cause 错误类型
     * @param enableSuppression 未知待考究
     * @param writableStackTrace 为false不打印出哪行代码出现了错误
     */
    public ErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("代码错误的地方:" + message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorException(String message, Throwable cause, boolean writableStackTrace) {
        super("代码错误的地方:" + message, cause, false, writableStackTrace);
    }



}
