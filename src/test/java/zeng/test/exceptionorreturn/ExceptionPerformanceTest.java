package zeng.test.exceptionorreturn;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class ExceptionPerformanceTest {

    public static int count = 100000;

    private static long throwTestTime = 0;


    @Test
    public void testThrow() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {


            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpGet = new HttpPost("http://localhost:8080/users/createOrElseThrow?name=foo");

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (httpClient != null) {
                        httpClient.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        long stop = System.currentTimeMillis();

        throwTestTime = stop - start;
        System.out.println("test throw 共耗时：" + (stop - start) + " ms");
    }
}
