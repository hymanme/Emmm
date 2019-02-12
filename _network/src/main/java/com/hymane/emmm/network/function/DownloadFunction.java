package com.hymane.emmm.network.function;

import com.hymane.emmm.core.utils.FileUtils;

import java.io.File;
import java.net.UnknownHostException;

import androidx.annotation.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2019/2/12
 * Description:
 */
public class DownloadFunction implements Function<Response<ResponseBody>, String> {
    private String fileName;

    public DownloadFunction(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String apply(@NonNull Response<ResponseBody> response) throws Exception {
        if (response.isSuccessful() && response.body() != null) {
            File file = new File(fileName);
            if (FileUtils.inputStreamToFile(response.body().byteStream(), file.getAbsolutePath())) {//保存图片成功
                return file.getAbsolutePath();//返回一个File对象
            }
            throw new RuntimeException();
        } else {
            throw new UnknownHostException();
        }
    }
}
